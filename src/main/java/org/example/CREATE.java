package org.example;

import org.example.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CREATE {
    public static void main(String[] args) {
        SessionFactory factory = null;
        try {
            factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Employee.class)
                    .buildSessionFactory();

            Session session = factory.getCurrentSession();
            Employee emp = new Employee(1,"Erica", "Diaciuc", 2000);
            session.beginTransaction();
            session.save(emp);
            int myId=emp.getId();
            session=factory.getCurrentSession();
            Employee employee=session.get(Employee.class,myId);
            session.getTransaction().commit();
            System.out.println(employee);
            System.out.println("Done");

        } finally {
            factory.close(); // Close the session factory when you are done using it.
        }
    }
}