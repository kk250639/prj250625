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
    nick_name  VARCHAR(10)    NOT NULL,
    password   VARCHAR(16)    NOT NULL,
    info       VARCHAR(10000) NULL,
    created_at datetime       NULL,
    CONSTRAINT pk_member PRIMARY KEY (id)
);
DROP TABLE member;