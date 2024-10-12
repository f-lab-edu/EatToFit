CREATE TABLE member_sportses (
    id BIGINT NOT NULL AUTO_INCREMENT,
    member_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT unique_member_id_constraint UNIQUE (member_id)
);

CREATE TABLE member_sports (
    id BIGINT NOT NULL AUTO_INCREMENT,
    sports_id BIGINT NOT NULL,
    member_sportses_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (member_sportses_id) REFERENCES member_sportses(id)
);
