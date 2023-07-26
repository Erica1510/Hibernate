CREATE TABLE addresses (
                           id SERIAL PRIMARY KEY,
                           city VARCHAR(255),
                           postalCode VARCHAR(10)
);
CREATE TABLE departments (
                             id SERIAL PRIMARY KEY,
                             name VARCHAR(255)
);
CREATE TABLE projects (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255)
);
CREATE TABLE employees (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(255),
                           surname VARCHAR(255),
                           department_id INTEGER,
                           salary INTEGER,
                           address_id INTEGER,

                           CONSTRAINT fk_department
                               FOREIGN KEY (department_id)
                                   REFERENCES departments (id)
                                   ON UPDATE CASCADE
                                   ON DELETE SET NULL,

                           CONSTRAINT fk_address
                               FOREIGN KEY (address_id)
                                   REFERENCES addresses (id)
                                   ON UPDATE CASCADE
                                   ON DELETE SET NULL
);
CREATE TABLE employee_projects (
                                   employee_id INTEGER,
                                   project_id INTEGER,

                                   PRIMARY KEY (employee_id, project_id),

                                   CONSTRAINT fk_employee
                                       FOREIGN KEY (employee_id)
                                           REFERENCES employees (id)
                                           ON UPDATE CASCADE
                                           ON DELETE CASCADE,

                                   CONSTRAINT fk_project
                                       FOREIGN KEY (project_id)
                                           REFERENCES projects (id)
                                           ON UPDATE CASCADE
                                           ON DELETE CASCADE
);
INSERT INTO addresses (city, postalCode)
VALUES ('New York', '10001'),
       ('Los Angeles', '90001'),
       ('Chicago', '60601');
INSERT INTO departments (name)
VALUES ('Human Resources'),
       ('Finance'),
       ('Marketing');
INSERT INTO projects (name)
VALUES ('Project A'),
       ('Project B'),
       ('Project C');
INSERT INTO employees (name, surname, department_id, salary, address_id)
VALUES ('John', 'Doe', 1, 60000, 1),
       ('Jane', 'Smith', 2, 70000, 2),
       ('Mike', 'Johnson', 1, 55000, 3),
       ('Emily', 'Williams', 3, 65000, 1);
INSERT INTO employee_projects (employee_id, project_id)
VALUES (1, 1),
       (1, 2),
       (2, 2),
       (3, 1),
       (3, 3),
       (4, 2),
       (4, 3);
