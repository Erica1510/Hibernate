package org.example;

import org.example.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


    public class Update {
        public static void main(String[] args) {
            SessionFactory factory = null;
            try {
                factory = new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(Employee.class)
                        .buildSessionFactory();

                Session session = factory.getCurrentSession();
                session.beginTransaction();
                session.createQuery("update Employee set salary=1000 " +
                                "where name='Lola'").executeUpdate();

//                 Employee emp=session.get(Employee.class,1);
//                 emp.setSalary(500);
                session.getTransaction().commit();
                System.out.println("Done");

            } finally {
                assert factory != null;
                factory.close(); // Close the session factory when you are done using it.
            }
        }
    }

