package org.example.CRUD;

import org.example.model.Address;
import org.example.model.Department;
import org.example.model.Employee;
import org.example.model.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class CRUD {
    private final SessionFactory factory;

    public CRUD(SessionFactory factory) {
        this.factory = factory;
    }

    /**
     * Creates and saves a new Department in the database.
     *
     * @param departmentName The name of the department to be created.
     * @return The newly created Department object.
     */
    public Department createDepartment(String departmentName) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            Department department = new Department();
            department.setName(departmentName);
            session.save(department);

            session.getTransaction().commit();
            return department;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Creates and saves a new Address in the database.
     *
     * @param city       The city of the address.
     * @param postalCode The postal code of the address.
     * @return The newly created Address object.
     */
    public Address createAddress(String city, String postalCode) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            Address address = new Address();
            address.setCity(city);
            address.setPostalCode(postalCode);
            session.save(address);

            session.getTransaction().commit();
            return address;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Creates and saves a new Employee in the database and associates it with a Department and Address.
     *
     * @param name       The name of the employee.
     * @param surname    The surname of the employee.
     * @param salary     The salary of the employee.
     * @param department The Department the employee belongs to.
     * @param address    The Address of the employee.
     * @return The newly created Employee object.
     */
    public Employee createEmployee(String name, String surname, int salary, Department department, Address address) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            Employee employee = new Employee(name, surname, salary);
            employee.setDepartment(department);
            employee.setAddress(address);
            session.save(employee);

            session.getTransaction().commit();
            return employee;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Creates and saves a new Project in the database.
     *
     * @param projectName The name of the project.
     * @return The newly created Project object.
     */
    public Project createProject(String projectName) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            Project project = new Project();
            project.setName(projectName);
            session.save(project);

            session.getTransaction().commit();
            return project;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Associates an Employee with a Project and saves the association in the database.
     *
     * @param employee The Employee to be associated with the Project.
     * @param project  The Project to which the Employee will be associated.
     */
    public void addEmployeeToProject(Employee employee, Project project) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            List<Project> projects = employee.getProjects();
            if (projects == null) {
                projects = new ArrayList<>();
                employee.setProjects(projects);
            }
            projects.add(project);

            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Reads all employees from the Department with the given departmentId and prints them.
     *
     * @param departmentId The ID of the Department to fetch employees from.
     */
    public void readEmployeesByDepartment(int departmentId) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            Department department = session.get(Department.class, departmentId);

            System.out.println("Employees in the " + department.getName() + " department:");
            for (Employee employee : department.getEmployees()) {
                System.out.println(employee);
            }

            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Updates the salary of an Employee with the given employeeId and saves the update in the database.
     *
     * @param employeeId The ID of the Employee whose salary is to be updated.
     * @param newSalary  The new salary to be set for the Employee.
     */
    public void updateEmployeeSalary(int employeeId, int newSalary) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            Employee employee = session.get(Employee.class, employeeId);
            employee.setSalary(newSalary);

            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Deletes an Employee with the given employeeId from the database.
     *
     * @param employeeId The ID of the Employee to be deleted.
     */
    public void deleteEmployee(int employeeId) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            Employee employee = session.get(Employee.class, employeeId);
            if (employee != null) {
                session.delete(employee);
            }

            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
