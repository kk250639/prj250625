CREATE
DATABASE prj250625;
USE
prj250625;
CREATE TABLE board
(
    id         INT AUTO_INCREMENT NOT NULL,
    title      VARCHAR(15)    NOT NULL,
    content    VARCHAR(10000) NOT NULL,
    writer     VARCHAR(10)    NOT NULL,
    created_at datetime NULL DEFAULT NOW(),
    CONSTRAINT pk_board PRIMARY KEY (id)
);
ALTER TABLE board
    MODIFY COLUMN created_at DATETIME DEFAULT CURRENT_TIME;
DROP TABLE board;
CREATE TABLE member
(
    id         VARCHAR(10) NOT NULL,
    nick_name  VARCHAR(10) NOT NULL UNIQUE,
    password   VARCHAR(16) NOT NULL,
    info       VARCHAR(10000) NULL,
    created_at datetime NULL DEFAULT NOW(),
    CONSTRAINT pk_member PRIMARY KEY (id)
);
DROP TABLE member;

INSERT INTO board (title, content, writer, created_at)
SELECT CONCAT('제목 ', LPAD(seq, 3, '0')),
       CONCAT('내용 ', LPAD(seq, 3, '0')),
       'asdf', -- writer는 존재하는 사용자 ID로
       NOW()
FROM (SELECT 1 AS seq
      UNION ALL
      SELECT 2
      UNION ALL
      SELECT 3
      UNION ALL
      SELECT 4
      UNION ALL
      SELECT 5
      UNION ALL
      SELECT 6
      UNION ALL
      SELECT 7
      UNION ALL
      SELECT 8
      UNION ALL
      SELECT 9
      UNION ALL
      SELECT 10
      UNION ALL
      SELECT 11
      UNION ALL
      SELECT 12
      UNION ALL
      SELECT 13
      UNION ALL
      SELECT 14
      UNION ALL
      SELECT 15
      UNION ALL
      SELECT 16
      UNION ALL
      SELECT 17
      UNION ALL
      SELECT 18
      UNION ALL
      SELECT 19
      UNION ALL
      SELECT 20
      UNION ALL
      SELECT 21
      UNION ALL
      SELECT 22
      UNION ALL
      SELECT 23
      UNION ALL
      SELECT 24
      UNION ALL
      SELECT 25
      UNION ALL
      SELECT 26
      UNION ALL
      SELECT 27
      UNION ALL
      SELECT 28
      UNION ALL
      SELECT 29
      UNION ALL
      SELECT 30
      UNION ALL
      SELECT 31
      UNION ALL
      SELECT 32
      UNION ALL
      SELECT 33
      UNION ALL
      SELECT 34
      UNION ALL
      SELECT 35
      UNION ALL
      SELECT 36
      UNION ALL
      SELECT 37
      UNION ALL
      SELECT 38
      UNION ALL
      SELECT 39
      UNION ALL
      SELECT 40
      UNION ALL
      SELECT 41
      UNION ALL
      SELECT 42
      UNION ALL
      SELECT 43
      UNION ALL
      SELECT 44
      UNION ALL
      SELECT 45
      UNION ALL
      SELECT 46
      UNION ALL
      SELECT 47
      UNION ALL
      SELECT 48
      UNION ALL
      SELECT 49
      UNION ALL
      SELECT 50) AS numbers;


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

CREATE TABLE auto_login_token
(
    token      VARCHAR(100) NOT NULL PRIMARY KEY,
    member_id  VARCHAR(10)  NOT NULL,
    created_at DATETIME DEFAULT NOW(),

    FOREIGN KEY (member_id) REFERENCES member (id)
);