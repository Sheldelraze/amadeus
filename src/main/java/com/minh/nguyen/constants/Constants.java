package com.minh.nguyen.constants;

import com.minh.nguyen.dto.AuthorityDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 07/01/2018
 * Purpose: store system constants, may need to create more files in the future if needed
 * Warning: only add constant if you feel needed, however DO NOT change order of constants which are presented before
 * For instance, current number of authorities is 25, so if you want to add another authority, start with AUTH_XXX = 26
 */
public class Constants {
    public static final Integer MAX_UPLOAD_SIZE = 50;
    public static final Integer MAX_FILENAME_LENGTH_SIZE = 100;

    public static final String DEFAULT_AVATAR = "/assets/images/users/1.jpg";

    public static final Integer UPLOAD_FILE_PENDING_FLAG = 0;
    public static final Integer UPLOAD_FILE_SUCCESS_FLAG = 1;
    public static final Integer UPLOAD_FILE_FAIL_FLAG = -1;

    public static final Integer SUBMISSION_FAIL_PENALTY = 10;

    public static final Integer MESSAGE_MAX_LENGTH = 200;
    public static final Integer MAX_MESSAGE_PER_FETCH = 15;
    public static final Integer MAX_USER_PER_SEARCH = 8;
    public static final String TOPIC_TEXT = "topic";
    public static final String LEADERBOARD_TOPIC_TEXT = "leaderboardTopic";
    public static final String PUBLIC_TOPIC = "public";
    public static final String STATUS_TOPIC = "status";
    public static final String CONTEST_TOPIC = "contest/";
    public static final String COURSE_TOPIC = "course/";
    public static final String COURSE_ANNOUNCEMENT_TOPIC = "courseAnnouncement/";
    public static final String CONTEST_ANNOUNCEMENT_TOPIC = "contestAnnouncement/";
    public static final String MESSAGE_NOTIFICATION_TOPIC = "inbox/";
    public static final String NOTIFICATION_TOPIC = "notification/";
    public static final String DEFAULT_TOPIC = "NOT_CHOSEN";

    public static final String WEB_SOCKET_PREFIX = "/message/topic.";

    public static final Integer MESSAGE_NOT_READ_FLAG = 0;
    public static final Integer MESSAGE_READ_FLAG = 1;

    public static final Integer UPDATE_CONTEST_DELAY = 15; //update 15 minutes after contest finish

    public static final String CURRENT_LOGIN_USER_ID = "currentUserId";
    public static final String CURRENT_LOGIN_USER_HANDLE = "currentUserHandle";
    public static final String CURRENT_LOGIN_USER_AVATAR = "currentUserAvatar";
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
    public static final String PROBLEM_LOCATION_PREFIX = "E:\\storage\\problem\\";
    public static final String SUBMISSION_LOCATION_PREFIX = "E:\\storage\\submission\\";
    public static final String MATERIAL_LOCATION_PREFIX = "E:\\storage\\material\\";
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
    public static final String MSG_FILE_TOO_LARGE_ERR = "msg015";
    public static final String MSG_TEXT_NOT_VALID = "msg013";
    public static final String MSG_SYSTEM_ERR = "msg005";
    public static final String MSG_UPDATE_ERR = "msg010";
    public static final String MSG_INSERT_ERR = "msg012";
    public static final String MSG_COMPILE_ERR = "msg011";
    public static final String MSG_SESSION_TIMEOUT = "msg016";
    public static final String MSG_UPLOAD_ERR = "msg017";
    public static final String MSG_UPLOAD_FILE_TOO_BIG_ERR = "msg018";
    public static final String MSG_FILE_NAME_TOO_LONG_ERR = "msg019";
    public static final String MSG_FILENAME_EXISTED_ERR = "msg020";
    public static final String MSG_APPLY_SUCCESS = "msg021";
    public static final String MSG_APPLY_PENDING_ERR = "msg022";
    public static final String MSG_ALREADY_APPLIED_ERR = "msg023";
    public static final String MSG_APPLICATION_ACCEPTED_SUCCESS = "msg024";
    public static final String MSG_APPLICATION_DECLINED_SUCCESS = "msg025";
    public static final String MSG_CONTEST_APPLY_SUCCESS = "msg026";
    public static final String MSG_ALREADY_IN_CONTEST_ERR = "msg027";
    public static final String MSG_HANDLE_EXISTED_ERR = "msg028";

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

    public static final Integer ROLE_ADMIN_ID = 1;
    public static final Integer ROLE_LECTURER_ID = 2;
    public static final Integer ROLE_STUDENT_ID = 3;
    public static final Integer ROLE_SUPERVISOR_ID = 4;

    public static final String ROLE_ADMIN_TEXT = "Quản trị viên";
    public static final String ROLE_LECTURER_TEXT = "Giảng viên";
    public static final String ROLE_STUDENT_TEXT = "Sinh viên";
    public static final String ROLE_SUPERVISOR_TEXT = "Lãnh đạo";

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
    public static final Integer AUTH_PARTICIPATE_COURSE_ID = 14;
    public static final Integer AUTH_VIEW_COURSE_ID = 15;
    public static final Integer AUTH_EDIT_COURSE_ID = 16;
    public static final Integer AUTH_VIEW_ALL_COURSE_ID = 17;
    public static final Integer AUTH_CREATE_COURSE_ID = 18;
    public static final Integer AUTH_UPLOAD_MATERIAL_ID = 19;
    public static final Integer AUTH_VIEW_ALL_MATERIAL_ID = 20;
    public static final Integer AUTH_EDIT_AUTHORITY_ID = 21;

    public static final String AUTH_CREATE_USER_TEXT = "CAN_CREATE_USER";
    public static final String AUTH_EDIT_AUTHORITY_TEXT = "CAN_EDIT_AUTHORITY";
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
    public static final String AUTH_PARTICIPATE_COURSE_TEXT = "CAN_PARTICIPATE_COURSE";
    public static final String AUTH_VIEW_COURSE_TEXT = "CAN_VIEW_COURSE";
    public static final String AUTH_EDIT_COURSE_TEXT = "CAN_EDIT_COURSE";
    public static final String AUTH_VIEW_ALL_COURSE_TEXT = "CAN_VIEW_ALL_COURSE";
    public static final String AUTH_CREATE_COURSE_TEXT = "CAN_CREATE_COURSE";
    public static final String AUTH_UPLOAD_MATERIAL_TEXT = "CAN_UPLOAD_MATERIAL";
    public static final String AUTH_VIEW_ALL_MATERIAL_TEXT = "CAN_VIEW_ALL_MATERIAL";

    public static final Integer SHOW_SUBMIT_ALL = 1;
    public static final Integer SHOW_SUBMIT_SOLVED = 2;
    public static final Integer SHOW_SUBMIT_PERSONAL = 3;

    public static final Integer SHOW_TEST_ALL = 1;
    public static final Integer SHOW_TEST_SOLVED = 2;
    public static final Integer SHOW_TEST_PERSONAL = 3;

    public static final Integer MAX_DESCRIPTION_LENGTH = 150;

    public static final Integer MAX_COMPILE_TIME = 10000; // in milliseconds

    public static final Integer CONSOLATION_POINT = 1;

    public static final Integer HIDDEN_FLAG = 1;
    public static final Integer NOT_HIDDEN_FLAG = 0;

    public static final Integer PUBLIC_FLAG = 1;
    public static final Integer NOT_PUBLIC_FLAG = 0;

    public static final Integer APPLICATION_STATUS_PENDING = 0;
    public static final Integer APPLICATION_STATUS_DENIED = -1;
    public static final Integer APPLICATION_STATUS_ACCEPTED = 1;

    public static final Integer NOTIFICATION_APPLICATION_PENDING_TYPE = 1;
    public static final Integer NOTIFICATION_APPLICATION_ACCEPTED_TYPE = 2;
    public static final Integer NOTIFICATION_APPLICATION_DENIED_TYPE = 3;
    public static final Integer NOTIFICATION_COURSE_KICKED_TYPE = 4;

    public static final String NOTIFICATION_APPLICATION_PENDING_CONTENT = " muốn được gia nhập khóa học ";
    public static final String NOTIFICATION_APPLICATION_ACCEPTED_CONTENT = "Bạn đã được chấp nhận tham gia khóa học  ";
    public static final String NOTIFICATION_APPLICATION_DENIED_CONTENT = "Bạn đã bị từ chối tham gia khóa học  ";
    public static final String NOTIFICATION_COURSE_KICKED_CONTENT = "Bạn đã bị loại khỏi khóa học  ";

    public static final Integer JUDGE_TYPE_ACM = 1;
    public static final Integer JUDGE_TYPE_IOI = 2;

    public static final List<AuthorityDTO> LST_DEFAULT_AUTHORITY = new ArrayList<>();

    static {
        LST_DEFAULT_AUTHORITY.add(new AuthorityDTO(AUTH_CREATE_CONTEST_ID,"Tạo cuộc thi"));
        LST_DEFAULT_AUTHORITY.add(new AuthorityDTO(AUTH_VIEW_ALL_CONTEST_ID,"Xem tất cả cuộc thi"));
        LST_DEFAULT_AUTHORITY.add(new AuthorityDTO(AUTH_CREATE_COURSE_ID,"Tạo khóa học"));
        LST_DEFAULT_AUTHORITY.add(new AuthorityDTO(AUTH_VIEW_ALL_COURSE_ID,"Xem tất cả khóa học"));
        LST_DEFAULT_AUTHORITY.add(new AuthorityDTO(AUTH_CREATE_USER_ID,"Tạo tài khoản mới"));
        LST_DEFAULT_AUTHORITY.add(new AuthorityDTO(AUTH_UPLOAD_MATERIAL_ID,"Upload tài liệu mới"));
        LST_DEFAULT_AUTHORITY.add(new AuthorityDTO(AUTH_VIEW_ALL_MATERIAL_ID,"Xem tất cả tài liệu"));
        LST_DEFAULT_AUTHORITY.add(new AuthorityDTO(AUTH_VIEW_ALL_SUBMISSION_ID,"Xem tất cả bài nộp"));
        LST_DEFAULT_AUTHORITY.add(new AuthorityDTO(AUTH_VIEW_ALL_PROBLEM_ID,"Xem tất cả bài tập"));
    }
}
