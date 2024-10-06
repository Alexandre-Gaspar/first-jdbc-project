CREATE OR REPLACE FUNCTION employee_audit_insert() RETURNS TRIGGER AS $$
BEGIN
    -- Inserir um registro na tabela employees_audit após a inserção em employees
    INSERT INTO employees_audit (name, salary, birthday, operation, employee_id)
    VALUES (NEW.name, NEW.salary, NEW.birthday, 'I', NEW.id);
    
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER tgr_employee_audit_insert
AFTER INSERT ON employees
FOR EACH ROW
EXECUTE FUNCTION employee_audit_insert();
