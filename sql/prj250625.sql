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
