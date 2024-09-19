CREATE TABLE fitness(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name CHAR(10) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name)
);
