package org.example;

import org.example.model.Item;
import org.example.model.Passport;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Passport.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            Person person = new Person("test", 19);
            Passport passport = new Passport(12345);
            person.setPassport(passport);
            session.persist(person);

            Person person1 = session.get(Person.class, 16);
            System.out.println(person1.getPassport().getPassportNumber());

            Passport passport1 = session.get(Passport.class, 16);
            System.out.println(passport1.getPerson().getName());

            Person person2 = session.get(Person.class, 16);
            person2.getPassport().setPassportNumber(4321);
            session.remove(person2);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();

        }


    }
}
