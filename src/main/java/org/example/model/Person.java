package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//   If need to use existing sequence (if the database doesn't support incrementing)
//
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceName_for_java")
//    @SequenceGenerator(name = "sequenceName_for_java", sequenceName = "sequenceName_in_db", allocationSize = 1)
    private  int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
