package fr.epita.advjava.datamodel;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    String number;
    String street;
    String city;
    String country;

}
