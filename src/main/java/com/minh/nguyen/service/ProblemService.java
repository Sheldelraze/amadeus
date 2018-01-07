package com.minh.nguyen.service;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.entity.ProblemEntity;
import com.minh.nguyen.exception.CompileErrorException;
import com.minh.nguyen.form.problem.ProblemSolutionForm;
import com.minh.nguyen.util.CompileUtil;
import com.minh.nguyen.util.ExceptionUtil;
import com.minh.nguyen.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author Mr.Minh
 * @since 01/01/2018
 * Purpose:
 */
@Service
public class ProblemService extends BaseService<ProblemEntity> {
    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private CompileUtil compileUtil;

    @Autowired
    private ExceptionUtil exceptionUtil;
    private static Logger logger = LoggerFactory.getLogger(ProblemService.class);
    public void tryCompile(ProblemSolutionForm problemSolutionForm) {
        File file = fileUtil.createFile(Constants.TEST_COMPILE_LOCATION, Constants.TEST_COMPILE_FILENAME, problemSolutionForm.getLanguage());
        fileUtil.writeToFile(problemSolutionForm.getSourceCode(), file);
        try {
            compileUtil.doCompile(file,problemSolutionForm.getLanguage());
        } catch (CompileErrorException e) {
            logger.warn(e.getMessage());
        }
    }
}