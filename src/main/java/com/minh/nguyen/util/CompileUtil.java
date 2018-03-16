package com.minh.nguyen.util;

import com.google.common.util.concurrent.SimpleTimeLimiter;
import com.google.common.util.concurrent.TimeLimiter;
import com.google.common.util.concurrent.UncheckedTimeoutException;
import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.InputDTO;
import com.minh.nguyen.dto.LanguageDTO;
import com.minh.nguyen.dto.ProblemDTO;
import com.minh.nguyen.exception.CompileErrorException;
import com.minh.nguyen.util.Runner.Outcome;
import com.minh.nguyen.util.Runner.Params;
import com.minh.nguyen.util.Runner.ProcessRunner;

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
    public static Outcome tryRun(File file, String extension,
                                 File inputFile, int memoryLimit) throws CompileErrorException {
        Params.Builder builder = new Params.Builder();
        builder.setRedirectInputFile(inputFile);
        StringBuilder stringBuilder = new StringBuilder();
        String command;
        if (Constants.CPP_EXTENSION.equals(extension)) {
            stringBuilder.append(file.getPath());
            stringBuilder.append(".exe ");
        } else if (Constants.JAVA_EXTENSION.equals(extension)) {
            stringBuilder.append(Constants.JAVA_EXECUTOR)
            .append(" ")
            .append(file.getPath())
            .append(".class");
        }
        command = stringBuilder.toString();
        return ProcessRunner.run(command, builder.newInstance());
    }

    public static Outcome tryCompile(File file, String extension, String fileName) throws CompileErrorException {
        Params.Builder builder = new Params.Builder();
        builder.setTimeLimit(10000);
        StringBuilder stringBuilder = new StringBuilder();
        String command;
        if (Constants.CPP_EXTENSION.equals(extension)) {
            stringBuilder.append(Constants.CPP_COMPILER)
                    .append(" ")
                    .append(file.getPath())
                    .append(" -o ")
                    .append(file.getParent())
                    .append("\\")
                    .append(fileName)
                    .append(".exe ")
                    .append(Constants.CPP_ARGS);

        } else if (Constants.JAVA_EXTENSION.equals(extension)) {
            stringBuilder.append(Constants.JAVA_COMPILER);
            stringBuilder.append(" ");
            stringBuilder.append(file.getPath());
        }
        command = stringBuilder.toString();
        return ProcessRunner.run(command, builder.newInstance());
    }

    public static Outcome doRun(LanguageDTO languageDTO, ProblemDTO problemDTO, InputDTO inputDTO, int snId) {
        StringBuilder input = new StringBuilder()
                .append(Constants.PROBLEM_LOCATION_PREFIX)
                .append(problemDTO.getCode())
                .append("\\")
                .append("input-itId-")
                .append(inputDTO.getId())
                .append(".txt");
        StringBuilder source = new StringBuilder()
                .append(Constants.SUBMISSION_LOCATION_PREFIX)
                .append("submission-snId-")
                .append(snId)
                .append("-")
                .append(problemDTO.getCode());
        TimeLimiter timeLimiter = new SimpleTimeLimiter();
        File sourceFile = new File(source.toString());
        File inputFile = new File(input.toString());
        RunState runState = new RunState(sourceFile, languageDTO.getExtension(), inputFile,
                problemDTO.getMemoryLimit());
        try {
            timeLimiter.callWithTimeout(runState, problemDTO.getTimeLimit()
                    , TimeUnit.MILLISECONDS, true);
            return runState.getOutcome();
        } catch (TimeoutException | UncheckedTimeoutException e) {
            throw new UncheckedTimeoutException();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void doCompile(LanguageDTO languageDTO, ProblemDTO problemDTO, String location, String fileName) throws CompileErrorException, UncheckedTimeoutException {
        File file = FileUtil.createFileWithContent(problemDTO, languageDTO,
                location,
                fileName);
        TimeLimiter timeLimiter = new SimpleTimeLimiter();
        CompileState state = new CompileState();
        state.setFile(file);
        state.setFileName(fileName);
        state.setExtension(languageDTO.getExtension());
        try {
            timeLimiter.callWithTimeout(state, 5, TimeUnit.SECONDS, true);
            String err = state.getOutcome().getError();
            if (null != err && !Constants.BLANK.equals(err)) {
                err = StringUtil.trimLocation(err, languageDTO.getExtension());
                throw new CompileErrorException(err);
            }
        } catch (TimeoutException | UncheckedTimeoutException e) {
            throw new UncheckedTimeoutException();
        } catch (CompileErrorException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class RunState implements Callable<Void> {
        File file;
        String extension;
        File inputFile;
        Outcome outcome;
        int memoryLimit;
        int timeRun;

        public int getTimeRun() {
            return timeRun;
        }

        public RunState(File file, String extension, File inputFile, int memoryLimit) {
            this.file = file;
            this.extension = extension;
            this.inputFile = inputFile;
            this.memoryLimit = memoryLimit;
        }

        public void setTimeRun(int timeRun) {
            this.timeRun = timeRun;
        }

        public File getInputFile() {
            return inputFile;
        }

        public void setInputFile(File inputFile) {
            this.inputFile = inputFile;
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


        public Outcome getOutcome() {
            return outcome;
        }

        public void setOutcome(Outcome outcome) {
            this.outcome = outcome;
        }


        public int getMemoryLimit() {
            return memoryLimit;
        }

        public void setMemoryLimit(int memoryLimit) {
            this.memoryLimit = memoryLimit;
        }

        @Override
        public Void call() throws Exception {
            long startTime = System.nanoTime();
            outcome = tryRun(file, extension, inputFile,
                    memoryLimit);
            outcome.setTimeElapsed(System.nanoTime() - startTime);
            return null;
        }
    }

    public static class CompileState implements Callable<Void> {
        File file;
        String fileName;
        String extension;
        Outcome outcome = null;

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

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
            outcome = tryCompile(file, extension, fileName);
            return null;
        }
    }
}
