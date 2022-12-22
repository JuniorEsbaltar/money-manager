CREATE TABLE transaction (
    id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    amount DECIMAL(10, 2) NOT NULL,
    type VARCHAR(20) NOT NULL,
    date TIMESTAMP NOT NULL,
    user_id SERIAL NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users(id)
);