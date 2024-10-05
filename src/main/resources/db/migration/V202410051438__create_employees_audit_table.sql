CREATE TABLE employees_audit (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(150) ,
    old_name VARCHAR(150) ,
    salary DECIMAL(10, 2),
    old_salary DECIMAL(10, 2),
    birthday TIMESTAMP,
    old_birthday TIMESTAMP,
    operation CHAR(1),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)