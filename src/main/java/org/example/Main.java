package org.example;

import org.example.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = null;
        try {
            factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Employee.class)
                    .buildSessionFactory();

            Session session = factory.getCurrentSession();
            session.beginTransaction();
            List<Employee> emps = session.createQuery("from Employee where salary>600")
                    .getResultList();
            for (Employee e:emps)
                System.out.println(e);

            session.getTransaction().commit();

            System.out.println("Done");

        } finally {
            assert factory != null;
            factory.close(); // Close the session factory when you are done using it.
        }
    }
}