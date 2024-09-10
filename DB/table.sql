CREATE TABLE IF NOT EXISTS `tbl_category`
(
    `category_code`    INT NOT NULL AUTO_INCREMENT
        COMMENT '분류 코드',
    `category_name`    VARCHAR(100) NOT NULL COMMENT '분류명',
    PRIMARY KEY ( `category_code` )
) COMMENT = '분류';


CREATE TABLE IF NOT EXISTS `tbl_member`
(
    `member_code`    INT NOT NULL AUTO_INCREMENT
        COMMENT '맴버 코드',
    `member_id`    VARCHAR(255) NOT NULL COMMENT '맴버 아이디',
    `member_password`    VARCHAR(255) NOT NULL COMMENT '맴버 비밀번호',
    `member_name`    VARCHAR(50) NOT NULL COMMENT '맴버 이름',
    `member_email`    VARCHAR(100) COMMENT '맴버 이메일',
    `member_role`    VARCHAR(100) NOT NULL COMMENT '맴버 역할',
    `refresh_token`    VARCHAR(255) COMMENT '리프레시 토큰',
    PRIMARY KEY ( `member_code` )
) COMMENT = '맴버';


CREATE TABLE IF NOT EXISTS `tbl_page`
(
    `page_code`    INT NOT NULL AUTO_INCREMENT
        COMMENT '페이지 코드',
    `page_title`    VARCHAR(100) NOT NULL COMMENT '제목',
    `page_text`    TEXT COMMENT '내용',
    `page_create_dt`    DATETIME NOT NULL COMMENT '작성일시',
    `page_update_dt`    DATETIME COMMENT '수정일시',
    `is_deleted`    INT DEFAULT 0 NOT NULL COMMENT '삭제여부',
    `page_delete_dt`    DATETIME COMMENT '삭제일시',
    `pjt_code`    INT NOT NULL COMMENT '소속 프로젝트 코드',
    `category_code`    INT NOT NULL COMMENT '분류 코드',
    PRIMARY KEY ( `page_code` )
) COMMENT = '페이지';


CREATE TABLE IF NOT EXISTS `tbl_project`
(
    `pjt_code`    INT NOT NULL AUTO_INCREMENT
        COMMENT '프로젝트 코드',
    `pjt_name`    VARCHAR(100) NOT NULL COMMENT '프로젝트 이름',
    `pjt_create_dt`    DATETIME NOT NULL COMMENT '생성일시',
    `pjt_update_dt`    DATETIME COMMENT '수정일시',
    `is_deleted`    INT DEFAULT 0 NOT NULL COMMENT '삭제여부',
    `pjt_delete_dt`    DATETIME COMMENT '삭제일시',
    `member_code`    INT NOT NULL COMMENT '소유자 코드',
    PRIMARY KEY ( `pjt_code` )
) COMMENT = '프로젝트';


CREATE TABLE IF NOT EXISTS `tbl_project_category`
(
    `pjt_code`    INT NOT NULL COMMENT '프로젝트 코드',
    `category_code`    INT NOT NULL COMMENT '분류 코드'
) COMMENT = '프로젝트-분류목록';
