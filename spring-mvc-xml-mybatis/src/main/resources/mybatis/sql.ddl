use mybatis;

CREATE TABLE `members` (
  id bigint(20) unsigned auto_increment COMMENT 'id' PRIMARY KEY,
  pwd VARCHAR(20) NOT NULL COMMENT '암호',
  name VARCHAR(50) NOT NULL COMMENT 'name'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Member 정보';;