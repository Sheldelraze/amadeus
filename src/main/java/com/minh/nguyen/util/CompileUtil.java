package com.minh.nguyen.util;

import com.google.common.util.concurrent.SimpleTimeLimiter;
import com.google.common.util.concurrent.TimeLimiter;
import com.google.common.util.concurrent.UncheckedTimeoutException;
import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.InputDTO;
import com.minh.nguyen.dto.LanguageDTO;
import com.minh.nguyen.dto.ProblemDTO;
import com.minh.nguyen.entity.LanguageEntity;
import com.minh.nguyen.entity.SnSDlEntity;
import com.minh.nguyen.entity.SubmissionEntity;
import com.minh.nguyen.entity.SubmitDetailEntity;
import com.minh.nguyen.exception.CompileErrorException;
import com.minh.nguyen.mapper.SnSDlMapper;
import com.minh.nguyen.mapper.SubmissionMapper;
import com.minh.nguyen.mapper.SubmitDetailMapper;
import com.minh.nguyen.util.Runner.Outcome;
import com.minh.nguyen.util.Runner.Params;
import com.minh.nguyen.util.Runner.ProcessRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Mr.Minh
 * @since 04/01/2018
 * Purpose:
 */
public class CompileUtil {
    
    public static Outcome tryCompile(File file,String extension) throws CompileErrorException{
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

        }else if (Constants.JAVA_EXTENSION.equals(extension)){
            stringBuilder.append(Constants.JAVA_COMPILER);
            stringBuilder.append(" ");
            stringBuilder.append(file.getPath());
        }
        command = stringBuilder.toString();
        Outcome outcome =  ProcessRunner.run(command,builder.newInstance());
        return outcome;
    }
    public static File createFile(ProblemDTO problemDTO,LanguageDTO languageDTO,String location,String filename){
        File file = FileUtil.createFile(location,filename,languageDTO.getExtension());
        FileUtil.writeToFile(problemDTO.getSourceCode(), file);
        return file;
    }
    public static void doCompile(LanguageDTO languageDTO, ProblemDTO problemDTO) throws CompileErrorException,UncheckedTimeoutException{
        File file = createFile(problemDTO,languageDTO,
                Constants.TEST_COMPILE_LOCATION,
                Constants.TEST_COMPILE_FILENAME);
        TimeLimiter timeLimiter = new SimpleTimeLimiter();
        CompileState state = new CompileState();
        state.setFile(file);
        state.setExtension(languageDTO.getExtension());
        try {
            timeLimiter.callWithTimeout(state,5,TimeUnit.SECONDS,true);
            String err = state.getOutcome().getError();
            if (null != err && !Constants.BLANK.equals(err)){
                err = StringUtil.trimLocation(err,languageDTO.getExtension());
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
    public static class CompileState implements Callable<Void> {
        File file;
        String extension;
        Outcome outcome = null;

        public Outcome getOutcome() {
            return outcome;
        }

        public  void setOutcome(Outcome outcome) {
            this.outcome = outcome;
        }

        public File getFile() {
            return file;
        }

        public  void setFile(File file) {
            this.file = file;
        }

        public String getExtension() {
            return extension;
        }

        public  void setExtension(String extension) {
            this.extension = extension;
        }

        @Override
        public Void call() throws Exception {
            outcome = tryCompile(file,extension);
            return null;
        }
    }
}
