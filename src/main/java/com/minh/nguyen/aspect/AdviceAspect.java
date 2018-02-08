package com.minh.nguyen.aspect;

import com.minh.nguyen.entity.ContestEntity;
import com.minh.nguyen.entity.ProblemEntity;
import com.minh.nguyen.exception.NoSuchPageException;
import com.minh.nguyen.mapper.ContestMapper;
import com.minh.nguyen.mapper.ProblemMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AdviceAspect {

    private static final Logger logger = LoggerFactory.getLogger(AdviceAspect.class);

    @Autowired
    private ProblemMapper problemMapper;

    @Autowired
    private ContestMapper contestMapper;

    @Pointcut("execution(public * com.minh.nguyen.controller.ProblemController.*(..))")
    public void problemController(){}

    @Pointcut("execution(public * com.minh.nguyen.controller.ContestController.*(..))")
    public void contestController(){}

    @Pointcut("@annotation(com.minh.nguyen.validator.annotation.CheckNotNullFirst)")
    public void checkNotNullFirst(){}

    @Pointcut("@annotation(com.minh.nguyen.validator.annotation.CheckNotNullThird)")
    public void checkNotNullThird(){}

    @Before("problemController() && checkNotNullFirst()")
    public void ensureProblemNotNull(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Integer pmId = (Integer) args[0];
        ProblemEntity problemEntity = new ProblemEntity();
        problemEntity.setId(pmId);
        problemEntity = problemMapper.selectByPK(problemEntity);
        if (null == problemEntity){
            throw new NoSuchPageException("Problem not found!");
        }
    }

    @Before("contestController() && checkNotNullFirst()")
    public void ensureContestNotNull(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Integer ctId = (Integer) args[0];
        ContestEntity contestEntity = new ContestEntity();
        contestEntity.setId(ctId);
        contestEntity = contestMapper.selectByPK(contestEntity);
        if (null == contestEntity){
            throw new NoSuchPageException("Contest not found!");
        }
    }

    @Before("contestController() && checkNotNullThird()")
    public void ensureProblemInContestNotNull(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Integer pmId = (Integer) args[1];
        ProblemEntity problemEntity = new ProblemEntity();
        problemEntity.setId(pmId);
        problemEntity = problemMapper.selectByPK(problemEntity);
        if (null == problemEntity){
            throw new NoSuchPageException("Problem not found!");
        }
    }

}
