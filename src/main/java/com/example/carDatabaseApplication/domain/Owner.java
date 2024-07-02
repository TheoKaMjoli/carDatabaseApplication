package com.example.carDatabaseApplication.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Owner {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long ownerId;
    private String firstname, lastname;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Car> cars;

//    @ManyToMany(cascade = CascadeType.PERSIST)
//    @JoinTable(name="car_owner", joinColumns = { @JoinColumn(name = "ownerId")}, inverseJoinColumns = { @JoinColumn(name = "id")})
//    private Set<Car> car = new HashSet<Car>();

    public Owner(){}

    public Owner(String firstname, String lastname) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
    }



}
