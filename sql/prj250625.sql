CREATE DATABASE prj250625;
USE prj250625;
CREATE TABLE board
(
    id         INT AUTO_INCREMENT NOT NULL,
    title      VARCHAR(15)        NOT NULL,
    content    VARCHAR(10000)     NOT NULL,
    writer     VARCHAR(10)        NOT NULL,
    created_at datetime           NULL DEFAULT NOW(),
    CONSTRAINT pk_board PRIMARY KEY (id)
);
ALTER TABLE board
    MODIFY COLUMN created_at DATETIME DEFAULT CURRENT_TIME;
DROP TABLE board;
CREATE TABLE member
(
    id         VARCHAR(10)    NOT NULL,
    nick_name  VARCHAR(10)    NOT NULL UNIQUE,
    password   VARCHAR(16)    NOT NULL,
    info       VARCHAR(10000) NULL,
    created_at datetime       NULL DEFAULT NOW(),
    CONSTRAINT pk_member PRIMARY KEY (id)
);
DROP TABLE member;

UPDATE board
SET writer = 'ㅁㄴㅇㄴㅁㅇ'
WHERE id % 2 = 1;

UPDATE board
SET writer = 'ㅁㄴㅇ'
WHERE id % 2 = 0;

ALTER TABLE board
    ADD FOREIGN KEY (writer) REFERENCES member (id);

INSERT INTO member (id, password, nick_name, info)
VALUES ('ㅁㄴㅇ', 'dummy', '비회원A', '비회원 작성자'),
       ('ㅁㄴㅇㄴㅁㅇ', 'dummy', '비회원B', '비회원 작성자');
