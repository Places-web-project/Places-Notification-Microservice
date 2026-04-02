CREATE SCHEMA IF NOT EXISTS notification_schema;

CREATE TABLE IF NOT EXISTS notification_schema.notifications (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(80) NOT NULL UNIQUE,
    subject VARCHAR(200) NOT NULL,
    body VARCHAR(2000) NOT NULL
);

CREATE TABLE IF NOT EXISTS notification_schema.email_logs (
    id BIGSERIAL PRIMARY KEY,
    recipient VARCHAR(200) NOT NULL,
    subject VARCHAR(200) NOT NULL,
    status VARCHAR(255) NOT NULL,
    sent_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS notification_schema.feedback_entries (
    id BIGSERIAL PRIMARY KEY,
    category VARCHAR(80) NOT NULL,
    experience VARCHAR(80) NOT NULL,
    recommend BOOLEAN NOT NULL,
    message VARCHAR(1200) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);
