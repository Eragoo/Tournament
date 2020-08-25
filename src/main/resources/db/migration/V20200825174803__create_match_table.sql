CREATE TABLE match
(
    id BIGINT GENERATED ALWAYS AS IDENTITY,
    red_participant_id BIGINT NOT NULL,
    blue_participant_id BIGINT NOT NULL,
    blue_score BIGINT NOT NULL DEFAULT 0,
    red_score BIGINT NOT NULL DEFAULT 0,
    start_time TIMESTAMP,
    finish_time TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (red_participant_id) REFERENCES "participant",
    FOREIGN KEY (blue_participant_id) REFERENCES "participant"
);