package org.example;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            Person person = session.get(Person.class, 3);
            System.out.println(person);
            List<Item> itemList = person.getItemList();
            System.out.println(itemList.toString());


            Item item = session.get(Item.class, 1);
            Person owner = item.getOwner();
            System.out.println(owner.toString());

            Person person1 = session.get(Person.class, 2);
            Item item1 = new Item("Item from hibernate");
            person1.addItem(item1); //Чтобы избежать ошибки кэша. hibernate кэширует значения, информация может не обновиться
            session.persist(item1);

            Person person2 = new Person("test", 25);
            Item newItem = new Item("testItem");
            person2.addItem(newItem);
            session.persist(person2);
            session.persist(newItem);// При наличии каскадирования - не обязательный код

            Person person3 = session.get(Person.class, 3);
            List<Item>itemList1 =  person3.getItemList();
            for (Item itemIterate: itemList1
                 ) {
                session.remove(itemIterate);
            }
            person3.getItemList().clear();//Чтобы избежать ошибки кэша. hibernate кэширует значения, информация может не обновиться

            Person person4 = session.get(Person.class, 2);
            session.remove(person4);
            person4.getItemList().forEach(i -> i.setOwner(null));//Чтобы избежать ошибки кэша. hibernate кэширует значения, информация может не обновиться

            Person person5 = session.get(Person.class, 4);
            Item item6 = session.get(Item.class,1);
            item6.getOwner().getItemList().remove(item6);//Чтобы избежать ошибки кэша. hibernate кэширует значения, информация может не обновиться
            item6.setOwner(person5);
            person5.getItemList().add(item6);//Чтобы избежать ошибки кэша. hibernate кэширует значения, информация может не обновиться

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();

        }


    }
}
