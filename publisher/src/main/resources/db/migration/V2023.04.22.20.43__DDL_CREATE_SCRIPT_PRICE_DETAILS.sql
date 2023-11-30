CREATE TABLE IF NOT EXISTS script_price_details
(

    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    price       INT         NOT NULL,
    script_name VARCHAR(64) NOT NULL,
    updated_at  DATETIME(6) NOT NULL,

    FOREIGN KEY (script_name) REFERENCES stock (script_name)

) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;