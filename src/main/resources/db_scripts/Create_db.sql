CREATE SCHEMA IF NOT EXISTS rental
DROP TABLE IF EXISTS USER_ACCOUNT
DROP TABLE IF EXISTS CAR
DROP TABLE IF EXISTS BOOKING

CREATE TABLE IF NOT EXISTS USER_ACCOUNT (
    user_id           VARCHAR(255) NOT NULL,
    user_name         VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS CAR (
    car_id            VARCHAR(255) NOT NULL,
    price_per_hour    INT NOT NULL,
    PRIMARY KEY (car_id)
);

CREATE TABLE IF NOT EXISTS BOOKING (
    booking_id         UUID NOT NULL,
    amount             INT NOT NULL,
    start_date         DATE NOT NULL,
    end_date           DATE NOT NULL,
    status             VARCHAR(255) NOT NULL,
    user_id            VARCHAR(255) NOT NULL,
    car_id             VARCHAR(255) NOT NULL,
    PRIMARY KEY (booking_id)
);

-- Use btree_gist extension to add no overlapping constraint
CREATE EXTENSION btree_gist;

-- Don't allow overlapping bookings
ALTER TABLE BOOKING
ADD CONSTRAINT  overlapping_booking
EXCLUDE USING gist (
    DATERANGE(start_date, end_date, '[]') WITH &&
);

ALTER TABLE BOOKING
ADD CONSTRAINT fk_booking_user_id
FOREIGN KEY (user_id) REFERENCES USER_ACCOUNT(user_id)
ON DELETE CASCADE;

ALTER TABLE BOOKING
ADD CONSTRAINT fk_booking_car_id
FOREIGN KEY (car_id) REFERENCES CAR(car_id)
ON DELETE CASCADE;
