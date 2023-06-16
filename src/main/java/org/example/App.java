package org.example;

import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            Person person1 = new Person("test1", 20);
            session.persist(person1);
            System.out.println(person1.getId());

            Person person = session.get(Person.class, 1);
            System.out.println(person.toString());

            Person person2 = session.get(Person.class, 1);
            person2.setName("New name");

            Person person3 = session.get(Person.class, 1);
            session.remove(person3);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();

        }


    }
}
