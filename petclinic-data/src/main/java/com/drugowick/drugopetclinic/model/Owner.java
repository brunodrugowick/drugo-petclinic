package com.drugowick.drugopetclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "owners")
public class Owner extends Person {

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "telephone")
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    @Override
    public String toString() {
        return "Owner{" +
                "address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", telephone='" + telephone + '\'' +
                ", pets=" + pets +
                '}';
    }

    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     *
     * @param petName to find
     * @return the Pet if pet name is already in use
     */
    public Pet getPet(String petName) {
        return getPet(petName, false);
    }

    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     *
     * @param petName to find
     * @param ignoreNew to ignore if it's a new Pet (not saved).
     * @return the Pet if pet name is already in use
     */
    public Pet getPet(String petName, boolean ignoreNew) {
        petName = petName.toLowerCase();
        for (Pet pet : pets) {
            if (!ignoreNew || !pet.isNew()) {
                String name = pet.getName();
                name = name.toLowerCase();
                if (name.equals(petName)) {
                    return pet;
                }
            }
        }
        return null;
    }
}
