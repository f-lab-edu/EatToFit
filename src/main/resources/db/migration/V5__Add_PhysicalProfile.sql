CREATE TABLE physical_profile(
    id BIGINT NOT NULL AUTO_INCREMENT,
    birth_year INTEGER NOT NULL,
    gender ENUM('MALE', 'FEMALE') NOT NULL,
    weight DECIMAL(5, 2) NOT NULL,
    height DECIMAL(5, 2) NOT NULL,
    member_id BIGINT NOT NULL,
    PRIMARY KEY (id)
);
