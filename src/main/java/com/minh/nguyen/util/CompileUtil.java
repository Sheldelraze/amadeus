package com.minh.nguyen.util;

import com.google.common.util.concurrent.SimpleTimeLimiter;
import com.google.common.util.concurrent.TimeLimiter;
import com.google.common.util.concurrent.UncheckedTimeoutException;
import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.ProblemDTO;
import com.minh.nguyen.entity.LanguageEntity;
import com.minh.nguyen.entity.SubmissionEntity;
import com.minh.nguyen.exception.CompileErrorException;
import com.minh.nguyen.util.Runner.Outcome;
import com.minh.nguyen.util.Runner.Params;
import com.minh.nguyen.util.Runner.ProcessRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Mr.Minh
 * @since 04/01/2018
 * Purpose:
 */
@Service
public class CompileUtil {

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private StringUtil stringUtil;
    public Outcome tryCompile(File file,String extension) throws CompileErrorException{
        Params.Builder builder = new Params.Builder();
       // builder.setRedirectErrorFile(new File(Constants.TEST_ERROR_LOCATION));
        builder.setTimeLimit(5000);
        StringBuilder stringBuilder = new StringBuilder();
        String command = "";

        if (Constants.CPP_EXTENSION.equals(extension)){
            stringBuilder.append(Constants.CPP_COMPILER);
            stringBuilder.append(" ");
            stringBuilder.append(file.getPath());
            stringBuilder.append(" ");
            stringBuilder.append(Constants.CPP_ARGS);
            command = stringBuilder.toString();
        }else if (Constants.JAVA_EXTENSION.equals(extension)){
            stringBuilder.append(Constants.JAVA_COMPILER);
            stringBuilder.append(" ");
            stringBuilder.append(file.getPath());
        }
        Outcome outcome =  ProcessRunner.run(command,builder.newInstance());
        return outcome;
    }
    public void judge(LanguageEntity languageEntity, ProblemDTO problemDTO) throws CompileErrorException {
        SubmissionEntity submissionEntity = new SubmissionEntity();
        submissionEntity.setLeId(languageEntity.getId());
        submissionEntity.setPmId(problemDTO.getId());
        File file = fileUtil.createFile(Constants.TEST_COMPILE_LOCATION,
                Constants.TEST_COMPILE_FILENAME,languageEntity.getExtension());
        fileUtil.writeToFile(problemDTO.getSourceCode(), file);
        try {
            doCompile(languageEntity,problemDTO);
        } catch (CompileErrorException | UncheckedTimeoutException e) {
            submissionEntity.setJudgeStatus(Constants.STATUS_COMPILE_ERROR);
            submissionEntity.setVerdict(Constants.VERDICT_COMPILE_ERROR);
            return;
        }
    }
    public File createFile(ProblemDTO problemDTO,LanguageEntity languageEntity,String location,String filename){
        File file = fileUtil.createFile(location,filename,languageEntity.getExtension());
        fileUtil.writeToFile(problemDTO.getSourceCode(), file);
        return file;
    }
    public void doCompile(LanguageEntity languageEntity,ProblemDTO problemDTO) throws CompileErrorException,UncheckedTimeoutException{
        File file = createFile(problemDTO,languageEntity,
                Constants.TEST_COMPILE_LOCATION,
                Constants.TEST_COMPILE_FILENAME);
        TimeLimiter timeLimiter = new SimpleTimeLimiter();
        State state = new State();
        state.setFile(file);
        state.setExtension(languageEntity.getExtension());
        try {
            timeLimiter.callWithTimeout(state,5,TimeUnit.SECONDS,true);
            String err = state.getOutcome().getError();
            if (null != err && !Constants.BLANK.equals(err)){
                err = stringUtil.trimLocation(err,languageEntity.getExtension());
                throw new CompileErrorException(err);
            }
        } catch (TimeoutException | UncheckedTimeoutException e) {
            throw new UncheckedTimeoutException();
        } catch(CompileErrorException e){
            throw e;
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public class State implements Callable<Void> {
        File file;
        String extension;
        Outcome outcome = null;

        public Outcome getOutcome() {
            return outcome;
        }

        public void setOutcome(Outcome outcome) {
            this.outcome = outcome;
        }

        public File getFile() {
            return file;
        }

        public void setFile(File file) {
            this.file = file;
        }

        public String getExtension() {
            return extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }

        @Override
        public Void call() throws Exception {
            outcome = tryCompile(file,extension);
            return null;
        }
    }
}
