CREATE TABLE IF NOT EXISTS stock
(

    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    script_name VARCHAR(64) UNIQUE,
    company     VARCHAR(64),
    currency    VARCHAR(10),
    listed_at   DATETIME NOT NULL

) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;