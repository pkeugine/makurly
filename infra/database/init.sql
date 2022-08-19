CREATE TABLE customer(
                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                     name VARCHAR(255),
                     gender VARCHAR(30),
                     birth_date TIMESTAMP,
                     device VARCHAR(30),
                     main_address VARCHAR(255),
                     detailed_address VARCHAR(255),
                     sign_in_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE item(
                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                     name VARCHAR(255),
                     category VARCHAR(30),
                     price INTEGER,
                     img_url VARCHAR(255),
);

CREATE TABLE interaction(
                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                     customer_id BIGINT,
                     order_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                     FOREIGN KEY(customer_id) REFERENCES customer(id) ON UPDATE CASCADE ON DELETE RESTRICT,
);

CREATE TABLE cart(
                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                     customer_id BIGINT,
                     item_id BIGINT,
                     quantity INTEGER,
                     FOREIGN KEY(customer_id) REFERENCES customer(id) ON UPDATE CASCADE ON DELETE RESTRICT,
                     FOREIGN KEY(item_id) REFERENCES item(id) ON UPDATE CASCADE ON DELETE RESTRICT,
);

CREATE TABLE recommend(
                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                     customer_id BIGINT,
                     interaction_id BIGINT,
                     FOREIGN KEY(customer_id) REFERENCES customer(id) ON UPDATE CASCADE ON DELETE RESTRICT,
                     FOREIGN KEY(interaction_id) REFERENCES interaction(id) ON UPDATE CASCADE ON DELETE RESTRICT
);

