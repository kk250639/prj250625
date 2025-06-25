CREATE DATABASE prj250625;
USE prj250625;
CREATE TABLE board
(
    id         INT AUTO_INCREMENT NOT NULL,
    title      VARCHAR(15)        NOT NULL,
    content    VARCHAR(10000)     NOT NULL,
    writer     VARCHAR(10)        NOT NULL,
    created_at datetime           NULL,
    CONSTRAINT pk_board PRIMARY KEY (id)
);