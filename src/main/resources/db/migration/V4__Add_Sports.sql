CREATE TABLE sports(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name CHAR(10) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT unique_name_constraint UNIQUE (name)
);
