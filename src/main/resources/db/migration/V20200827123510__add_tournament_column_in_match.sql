ALTER TABLE match
    ADD COLUMN tournament_id BIGINT,
    ADD FOREIGN KEY (tournament_id) REFERENCES "tournament";