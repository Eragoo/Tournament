CREATE TABLE tournament
(
    id BIGINT GENERATED ALWAYS AS IDENTITY,
    matchesNumber BIGINT NOT NULL,
    PRIMARY KEY (id)
)