ALTER TABLE member ADD COLUMN created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE member ADD COLUMN deleted_at TIMESTAMP NULL DEFAULT NULL;
