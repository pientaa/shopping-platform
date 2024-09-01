CREATE TABLE total_price_discount
(
    id                UUID PRIMARY KEY,
    discount_modifier DECIMAL(5, 2) NOT NULL,
    active            BOOLEAN       NOT NULL,

    CONSTRAINT chk_discount_modifier CHECK (discount_modifier >= 0 AND discount_modifier <= 1)
);

CREATE TABLE product_count_discount
(
    id                UUID PRIMARY KEY,
    discount_modifier DECIMAL(5, 2) NOT NULL,
    threshold         INT,
    product_id        UUID          NOT NULL,
    active            BOOLEAN       NOT NULL,

    CONSTRAINT chk_discount_modifier CHECK (discount_modifier >= 0 AND discount_modifier <= 1)
);
