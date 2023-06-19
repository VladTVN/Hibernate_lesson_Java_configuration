package org.example;

import org.example.model.Item;
import org.example.model.Movie;
import org.example.model.Passport;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Passport.class)
                .addAnnotatedClass(Movie.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try(sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();


            Movie movie = new Movie("Pulp fiction", 1994);
            Person person1 = new Person("Harvey Keitel",81);
            Person person2 = new Person("Samuel L. Jackson",72);
            movie.setPersonList(new ArrayList<>(List.of(person1, person2)));
            person1.setMovieList(new ArrayList<>(Collections.singletonList(movie)));
            person2.setMovieList(new ArrayList<>(Collections.singletonList(movie)));
            session.persist(movie);
            session.persist(person1);
            session.persist(person2);

            Movie movie1 = session.get(Movie.class, 1);
            System.out.println(movie1.getPersonList());

            Movie movie3 = new Movie("Reservoir Dogs", 1992);
           Person person = session.get(Person.class, 17);
           movie3.setPersonList(Collections.singletonList(person));
           person.getMovieList().add(movie3);
           session.persist(movie3);

            Person actor = session.get(Person.class, 17);
            Movie movieToRemove =  actor.getMovieList().get(0);
            actor.getMovieList().remove(movieToRemove);
            movieToRemove.getPersonList().remove(actor);

            session.getTransaction().commit();
        }


    }
}
