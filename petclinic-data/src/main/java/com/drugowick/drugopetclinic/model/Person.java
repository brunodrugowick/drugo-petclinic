package com.drugowick.drugopetclinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class Person extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public String getLastName() {return lastName;}

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + this.getId() + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
