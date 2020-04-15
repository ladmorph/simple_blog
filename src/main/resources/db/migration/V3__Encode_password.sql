CREATE extension IF NOT EXISTS pgcrypto;

UPDATE authors SET password = crypt(password, gen_salt('bf'))