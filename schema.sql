CREATE TABLE tournament (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    type VARCHAR(50),
    max_teams INT,
    status VARCHAR(50)
);

CREATE TABLE team (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    team_size INT,
    tournament_id BIGINT,
    FOREIGN KEY (tournament_id) REFERENCES tournament(id)
);

CREATE TABLE team_player (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    player_id BIGINT,
    team_id BIGINT,
    FOREIGN KEY (team_id) REFERENCES team(id)
);

CREATE TABLE match (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    tournament_id BIGINT,
    team1_id BIGINT,
    team2_id BIGINT,
    winner_team_id BIGINT,
    round_number INT,
    match_number INT,
    status VARCHAR(50),
    FOREIGN KEY (tournament_id) REFERENCES tournament(id),
    FOREIGN KEY (team1_id) REFERENCES team(id),
    FOREIGN KEY (team2_id) REFERENCES team(id),
    FOREIGN KEY (winner_team_id) REFERENCES team(id)
);