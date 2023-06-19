package org.example.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Passport")
public class Passport
//        implements Serializable необходимо когда первичный ключ не числовой
{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @Column(name = "passport_number")
    private int passportNumber;

    public Passport() {
    }

    public Passport(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Passport(int id, Person person, int passportNumber) {
        this.id = id;
        this.person = person;
        this.passportNumber = passportNumber;
    }

    public Passport(Person person, int passportNumber) {
        this.person = person;
        this.passportNumber = passportNumber;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "person=" + person +
                ", passportNumber=" + passportNumber +
                '}';
    }
}
