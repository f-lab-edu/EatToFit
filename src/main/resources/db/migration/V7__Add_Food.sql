CREATE TABLE food(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name CHAR(20) NOT NULL,
    serving_size DECIMAL(5, 2) NOT NULL,
    unit ENUM('GRAM', 'KILOGRAM') NOT NULL,
    kcal DECIMAL(5, 2) NOT NULL,
    carbohydrate DECIMAL(5, 2) NOT NULL,
    protein DECIMAL(5, 2) NOT NULL,
    fat DECIMAL(5, 2) NOT NULL,
    sodium DECIMAL(5, 2) NOT NULL,
    member_id BIGINT NOT NULL,
    PRIMARY KEY (id)
);
