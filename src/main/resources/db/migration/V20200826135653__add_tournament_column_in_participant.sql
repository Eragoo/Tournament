ALTER TABLE participant
    ADD COLUMN tournament_id BIGINT,
    ADD FOREIGN KEY (tournament_id) REFERENCES "tournament";