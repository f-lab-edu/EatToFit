CREATE TABLE member(
    id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    nickname CHAR(20) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (email),
    UNIQUE (nickname)
);
