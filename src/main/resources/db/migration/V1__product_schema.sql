CREATE TABLE product
(
    id             UUID PRIMARY KEY,
    price_amount   DECIMAL(19, 2) NOT NULL,
    price_currency VARCHAR(3)     NOT NULL
);
