package com.minh.nguyen.util;

import com.google.common.util.concurrent.SimpleTimeLimiter;
import com.google.common.util.concurrent.TimeLimiter;
import com.google.common.util.concurrent.UncheckedTimeoutException;
import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.exception.CompileErrorException;
import com.minh.nguyen.util.Runner.Outcome;
import com.minh.nguyen.util.Runner.Params;
import com.minh.nguyen.util.Runner.ProcessRunner;
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

    public Outcome tryCompile(File file,String language) throws CompileErrorException{
        Params.Builder builder = new Params.Builder();
       // builder.setRedirectErrorFile(new File(Constants.TEST_ERROR_LOCATION));
        builder.setTimeLimit(3000);
        StringBuilder stringBuilder = new StringBuilder();
        String command = "";

        if (Constants.CPP_EXTENSION.equals(language)){
            stringBuilder.append(Constants.CPP_COMPILER);
            stringBuilder.append(" ");
            stringBuilder.append(file.getPath());
            stringBuilder.append(" ");
            stringBuilder.append(Constants.CPP_ARGS);
            command = stringBuilder.toString();
        }else if (Constants.JAVA_EXTENSION.equals(language)){
            stringBuilder.append(Constants.JAVA_COMPILER);
            stringBuilder.append(" ");
            stringBuilder.append(file.getPath());
        }
        Outcome outcome =  ProcessRunner.run(command,builder.newInstance());
        return outcome;
    }
    public void doCompile(final File file, final String language) throws CompileErrorException,UncheckedTimeoutException{
        TimeLimiter timeLimiter = new SimpleTimeLimiter();
        State state = new State();
        state.setFile(file);
        state.setLanguage(language);
        try {
            timeLimiter.callWithTimeout(state,3,TimeUnit.SECONDS,true);
            String errror = state.getOutcome().getError();
            if (null != errror && !Constants.BLANK.equals(errror)){
                throw new CompileErrorException("Compile errror!");
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
        String language;
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

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        @Override
        public Void call() throws Exception {
            outcome = tryCompile(file,language);
            return null;
        }
    }
    public void solve(){
        TimeLimiter timeLimiter = new SimpleTimeLimiter();
        String a = "132";

//        try {
//            timeLimiter.callWithTimeout(new Callable<Void>() {
//                @Override
//                public Void call() throws Exception {
//                   // compile();
//                    return null;
//                }
//            },1,TimeUnit.SECONDS,true);
//        } catch (TimeoutException | UncheckedTimeoutException e) {
//            System.out.print("haha");
//        } catch (Exception e){
//            e.printStackTrace();
//        }
    }
    public void run(){

    }
}
