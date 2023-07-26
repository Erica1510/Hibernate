package org.example;

import org.example.model.Department;
import org.example.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CRUD {
    public static void main(String[] args) {
        // Create a SessionFactory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Department.class)
                .buildSessionFactory();

        // Create and save a new Department
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            Department department = new Department();
            department.setName("HR");
            session.save(department);

            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Create and save a new Employee and associate it with the Department
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            Department department = session.get(Department.class, 1);

            Employee employee = new Employee();
            employee.setName("John");
            employee.setSurname("Doe");
            employee.setSalary(5000);
            employee.setDepartment(department);

            session.save(employee);

            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Read all employees from the Department with ID 1
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            Department department = session.get(Department.class, 1);

            System.out.println("Employees in the " + department.getName() + " department:");
            for (Employee employee : department.getEmployees()) {
                System.out.println(employee);
            }

            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Update an employee's salary (use an existing employee ID)
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            Employee employee = session.get(Employee.class, 2); // Use an existing employee ID here
            employee.setSalary(6000);

            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Delete an employee
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            Employee employee = session.get(Employee.class, 2); // Use an existing employee ID here
            if (employee != null) {
                session.delete(employee);
            }

            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Close the SessionFactory
        factory.close();
    }
}
