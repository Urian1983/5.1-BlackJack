CREATE TABLE IF NOT EXISTS player_ranking (
                                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                              name VARCHAR(255) NOT NULL,
    games_won INT DEFAULT 0
    );