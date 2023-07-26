package org.example.app;

import org.example.CRUD.CRUD;
import org.example.model.Address;
import org.example.model.Department;
import org.example.model.Employee;
import org.example.model.Project;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainApp {
    public static void main(String[] args) {
        // Create a SessionFactory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Project.class)
                .buildSessionFactory();

        // Create an instance of the CRUD class
        CRUD crud = new CRUD(factory);

        try {
            // Create and save a new Department
            Department department = crud.createDepartment("HR");

            // Create and save Employees and associate them with the Department and Address
            Address address1 = crud.createAddress("New York", "10001");
            Address address2 = crud.createAddress("Los Angeles", "90001");

            Employee employee1 = crud.createEmployee("John", "Doe", 5000, department, address1);
            Employee employee2 = crud.createEmployee("Jane", "Smith", 6000, department, address2);

            // Create and save Projects
            Project project1 = crud.createProject("Project A");
            Project project2 = crud.createProject("Project B");

            // Associate Employees with Projects
            crud.addEmployeeToProject(employee1, project1);
            crud.addEmployeeToProject(employee1, project2);
            crud.addEmployeeToProject(employee2, project1);

            // Read all employees from the Department with ID 1
            crud.readEmployeesByDepartment(1);

            // Update an employee's salary
            crud.updateEmployeeSalary(1, 7000);

            // Delete an employee
            crud.deleteEmployee(2);
        } finally {
            // Close the SessionFactory
            factory.close();
        }
    }
}
