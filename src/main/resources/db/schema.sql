CREATE TABLE IF NOT EXISTS public.weather_data
(
    id bigserial NOT NULL,
    temperature real NOT NULL,
    wind real NOT NULL,
    pressure real NOT NULL,
    humidity integer NOT NULL,
    condition character varying(50) NOT NULL,
    location character varying(20) NOT NULL,
    date timestamp NOT NULL,
    CONSTRAINT "data_id" PRIMARY KEY (id)
);

