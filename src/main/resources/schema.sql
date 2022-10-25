CREATE TABLE IF NOT EXISTS eventshot_log (
    id bigint GENERATED ALWAYS AS IDENTITY,
    aggregate_class text,
    aggregate_id text,
    aggregate_json text,
    created_by text,
    created_date timestamp,
    event_class text,
    event_json text
);

CREATE TABLE IF NOT EXISTS contract_category (
    id varchar(255) PRIMARY KEY NOT NULL,
    created_by varchar(255),
    created_date timestamp,
    updated_by varchar(255),
    updated_date timestamp,
    version bigint DEFAULT 0
);

ALTER TABLE contract_category ADD COLUMN IF NOT EXISTS code text;
ALTER TABLE contract_category ADD COLUMN IF NOT EXISTS name text;

CREATE TABLE IF NOT EXISTS contract_category_role (
    id varchar(255) PRIMARY KEY NOT NULL,
    created_by varchar(255),
    created_date timestamp,
    updated_by varchar(255),
    updated_date timestamp,
    version bigint DEFAULT 0
    );

ALTER TABLE contract_category_role ADD COLUMN IF NOT EXISTS contract_category_id varchar(255);
ALTER TABLE contract_category_role ADD COLUMN IF NOT EXISTS role varchar(255);
