package com.minh.nguyen.aspect;

import com.minh.nguyen.entity.ContestEntity;
import com.minh.nguyen.entity.CourseEntity;
import com.minh.nguyen.entity.MaterialEntity;
import com.minh.nguyen.entity.ProblemEntity;
import com.minh.nguyen.exception.NoSuchPageException;
import com.minh.nguyen.mapper.ContestMapper;
import com.minh.nguyen.mapper.CourseMapper;
import com.minh.nguyen.mapper.MaterialMapper;
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
/**
 * @author Mr.Minh
 * @since 11/02/2018
 * Purpose: this class allows using AOP, mostly used to check authority and check null.
 * So I suggest you do some search (a.k.a google 'Spring boot Aspectj') before
 * changing anything here
 */
public class AdviceAspect {

    private static final Logger logger = LoggerFactory.getLogger(AdviceAspect.class);

    @Autowired
    private ProblemMapper problemMapper;

    @Autowired
    private ContestMapper contestMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private MaterialMapper materialMapper;

    @Pointcut("execution(public * com.minh.nguyen.controller.ProblemController.*(..))")
    public void problemController(){}

    @Pointcut("execution(public * com.minh.nguyen.controller.ContestController.*(..))")
    public void contestController(){}

    @Pointcut("execution(public * com.minh.nguyen.controller.CourseController.*(..))")
    public void courseController() {
    }

    @Pointcut("execution(public * com.minh.nguyen.controller.MaterialController.*(..))")
    public void materialController() {
    }

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

    @Before("courseController() && checkNotNullFirst()")
    public void ensureCourseNotNull(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Integer ctId = (Integer) args[0];
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(ctId);
        courseEntity = courseMapper.selectByPK(courseEntity);
        if (null == courseEntity) {
            throw new NoSuchPageException("Course not found!");
        }
    }

    @Before("materialController() && checkNotNullFirst()")
    public void ensureMaterialNotNull(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Integer ctId = (Integer) args[0];
        MaterialEntity materialEntity = new MaterialEntity();
        materialEntity.setId(ctId);
        materialEntity = materialMapper.selectByPK(materialEntity);
        if (null == materialEntity) {
            throw new NoSuchPageException("Material not found!");
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

    @Before("courseController() && checkNotNullThird()")
    public void ensureProblemInCourseNotNull(JoinPoint joinPoint) {
        ensureProblemInContestNotNull(joinPoint);
    }
}
