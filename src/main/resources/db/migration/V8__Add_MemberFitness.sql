CREATE TABLE member_fitnesses (
    id BIGINT NOT NULL AUTO_INCREMENT,
    member_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT unique_member_id_constraint UNIQUE (member_id)
);

CREATE TABLE member_fitness (
    id BIGINT NOT NULL AUTO_INCREMENT,
    fitness_id BIGINT NOT NULL,
    member_fitnesses_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (member_fitnesses_id) REFERENCES member_fitnesses(id) ON DELETE CASCADE
);
