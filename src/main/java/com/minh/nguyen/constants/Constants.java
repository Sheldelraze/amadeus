package com.minh.nguyen.constants;

/**
 * @author Mr.Minh
 * @since 07/01/2018
 * Purpose: store system constants, may need to create more files in the future if needed
 * Warning: only add constant if you feel needed, however DO NOT change order of constants which are presented before
 * For instance, current number of authorities is 13, so if you want to add another authority, start with AUTH_XXX = 14
 */
public class Constants {
    public static final Integer SUBMISSION_FAIL_PENALTY = 10;

    public static final Integer MESSAGE_MAX_LENGTH = 200;
    public static final Integer MAX_RECENT_MESSAGE = 30;
    public static final String PUBLIC_TOPIC = "public";


    public static final String CURRENT_LOGIN_USER_ID = "currentUserId";
    public static final String CURRENT_LOGIN_USER_HANDLE = "currentUserHandle";
    public static final String CURRENT_LOGIN_USER_FULLNAME = "currentUserFullname";
    public static final String CURRENT_LOGIN_USER_ROLE_ID = "currentUserRoleId";
    public static final String CURRENT_LOGIN_USER_ROLE_NAME = "currentUserRoleName";
    public static final String CURRENT_LOGIN_USER_DEFAULT_AUTHORITIES = "currentUserAuthorities";

    public static final String CPP_ARGS = "-g -O2 -static -std=gnu++11";
    public static final String CPP_COMPILER = "g++";
    public static final String CPP_EXTENSION = "cpp";
    public static final String JAVA_COMPILER = "javac";
    public static final String JAVA_EXECUTOR = "java";
    public static final String JAVA_EXTENSION = "java";
    public static final String PROBLEM_LOCATION = "E:\\problem\\";
    public static final String SUBMISSION_LOCATION = "E:\\submission\\";
    public static final String BLANK = "";

    public static final String MSG_TOTAL_ERR = "msg000";
    public static final String MSG_REQUIRED_INPUT_ERR = "msg001";
    public static final String MSG_LENGTH_INPUT_ERR = "msg002";
    public static final String MSG_DATE_FORMAT_INPUT_ERR = "msg003";
    public static final String MSG_TIME_FORMAT_INPUT_ERR = "msg014";
    public static final String MSG_EMAIL_FORMAT_INPUT_ERR = "msg004";
    public static final String MSG_NUMBER_FORMAT_INPUT_ERR = "msg007";
    public static final String MSG_DUPLICATE_PROBLEM_ERR = "msg006";
    public static final String MSG_NUMBER_TOO_SMALL_ERR = "msg008";
    public static final String MSG_NUMBER_TOO_BIG_ERR = "msg009";
    public static final String MSG_TEXT_NOT_VALID = "msg013";
    public static final String MSG_SYSTEM_ERR = "msg005";
    public static final String MSG_UPDATE_ERR = "msg010";
    public static final String MSG_INSERT_ERR = "msg012";
    public static final String MSG_COMPILE_ERR = "msg011";
    public static final String MSG_FILE_TOO_LARGE_ERR = "msg015";
    public static final String MSG_SESSION_TIMEOUT = "msg016";

    public static final Integer STATUS_JUDGING = 0;
    public static final Integer STATUS_COMPILE_ERROR = 1;
    public static final Integer STATUS_RUNTIME_ERROR = 2;
    public static final Integer STATUS_TIME_LIMIT_EXCEEDED = 3;
    public static final Integer STATUS_MEMORY_LIMIT_EXCEEDED = 4;
    public static final Integer STATUS_WRONG_ANSWER = 5;
    public static final Integer STATUS_ACCEPTED = 6;

    public static final String VERDICT_COMPILING = "Đang biên dịch...";
    public static final String VERDICT_JUDGING = "Đang chấm test ";
    public static final String VERDICT_COMPILE_ERROR = "Lỗi biên dịch";
    public static final String VERDICT_RUNTIME_ERROR = "Lỗi runtime";
    public static final String VERDICT_TIME_LIMIT_EXCEEDED = "Lỗi quá thời gian chạy";
    public static final String VERDICT_MEMORY_LIMIT_EXCEEDED = "Lỗi quá bộ nhớ";
    public static final String VERDICT_WRONG_ANSWER = "Sai kết quả";
    public static final String VERDICT_ACCEPTED = "Đúng";

    public static final Integer STATUS_HIDDEN = 1;
    public static final Integer STATUS_SHOW = 0;

    public static final Integer ROLE_ADMIN = 1;
    public static final Integer ROLE_LECTURER = 2;
    public static final Integer ROLE_STUDENT = 3;
    public static final Integer ROLE_SUPERVISOR = 4;
    public static final Integer AUTH_CREATE_USER_ID = 1;
    public static final Integer AUTH_EDIT_USER_ID = 2;
    public static final Integer AUTH_CREATE_PROBLEM_ID = 3;
    public static final Integer AUTH_EDIT_PROBLEM_ID = 4;
    public static final Integer AUTH_VIEW_PROBLEM_ID = 5;
    public static final Integer AUTH_CREATE_CONTEST_ID = 6;
    public static final Integer AUTH_EDIT_CONTEST_ID = 7;
    public static final Integer AUTH_VIEW_CONTEST_ID = 8;
    public static final Integer AUTH_PARTICIPATE_CONTEST_ID = 9;
    public static final Integer AUTH_VIEW_ALL_PROBLEM_ID = 10;
    public static final Integer AUTH_VIEW_ALL_CONTEST_ID = 11;
    public static final Integer AUTH_SUBMIT_PROBLEM_ID = 12;
    public static final Integer AUTH_VIEW_ALL_SUBMISSION_ID = 13;

    public static final String AUTH_CREATE_PROBLEM_TEXT = "CAN_CREATE_PROBLEM";
    public static final String AUTH_VIEW_PROBLEM_TEXT = "CAN_VIEW_PROBLEM";
    public static final String AUTH_EDIT_PROBLEM_TEXT = "CAN_EDIT_PROBLEM";
    public static final String AUTH_VIEW_ALL_PROBLEM_TEXT = "CAN_VIEW_ALL_PROBLEM";
    public static final String AUTH_CREATE_CONTEST_TEXT = "CAN_CREATE_CONTEST";
    public static final String AUTH_EDIT_CONTEST_TEXT = "CAN_EDIT_CONTEST";
    public static final String AUTH_VIEW_CONTEST_TEXT = "CAN_VIEW_CONTEST";
    public static final String AUTH_VIEW_ALL_CONTEST_TEXT = "CAN_VIEW_ALL_CONTEST";
    public static final String AUTH_PARTICIPATE_CONTEST_TEXT = "CAN_PARTICIPATE_CONTEST";
    public static final String AUTH_VIEW_ALL_SUBMISSION_TEXT = "CAN_VIEW_ALL_SUBMISSION";

    public static final Integer SHOW_SUBMIT_ALL = 1;
    public static final Integer SHOW_SUBMIT_SOLVED = 2;
    public static final Integer SHOW_SUBMIT_PERSONAL = 3;

    public static final Integer SHOW_TEST_ALL = 1;
    public static final Integer SHOW_TEST_SOLVED = 1;
    public static final Integer SHOW_TEST_PERSONAL = 1;
}
