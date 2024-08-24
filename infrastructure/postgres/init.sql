CREATE DATABASE scala_spark_db;

\c scala_spark_db

create table if not exists airlines(
    iata_code varchar(50) primary key,
    airline varchar(500)
);

create table if not exists airports(
    iata_code varchar(50) primary key,
    airport varchar(500),
    city varchar(100),
    state varchar(100),
    country varchar(100),
    lat double precision,
    long double precision
);

create table if not exists flights(
    year int,
    month int,
    day int,
    day_of_week int,
    airline varchar(50),
    flight_number int,
    tail_number varchar(50),
    origin_airport varchar(50),
    destination_airport varchar(50),
    scheduled_departure varchar(15),
    departure_time varchar(15),
    departure_delay int,
    taxi_out int,
    wheels_off varchar(15),
    scheduled_time varchar(15),
    elapsed_time int,
    air_time int,
    distance int,
    wheels_on varchar(15),
    taxi_in int,
    scheduled_arrival varchar(15),
    arrival_time varchar(15),
    arrival_delay int,
    diverted int,
    cancelled int,
    cancellation_reason varchar(500),
    air_system_delay int,
    security_delay int,
    airline_delay int,
    late_aircraft_delay int,
    weather_delay int
);

COPY airlines FROM '/docker-entrypoint-initdb.d/data/airlines.csv' DELIMITER ',' CSV HEADER;
COPY airports FROM '/docker-entrypoint-initdb.d/data/airports.csv' DELIMITER ',' CSV HEADER;
COPY flights FROM '/docker-entrypoint-initdb.d/data/flights.csv' DELIMITER ',' CSV HEADER;
