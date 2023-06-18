package org.example;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.util.List;


public class App
{
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            List<Person> people = session.createQuery("FROM Person WHERE name LIKE 'T%'", Person.class).getResultList();
            for (Person person: people
            ) {
                System.out.println(person.toString());
            }


            session.createQuery("update Person set name='Test' where age>20",null)
                    .executeUpdate();

            session.createQuery("delete from Person where age>20", null).executeUpdate();

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();

        }


    }
}
