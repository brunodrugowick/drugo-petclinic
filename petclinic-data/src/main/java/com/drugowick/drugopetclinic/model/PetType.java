package com.drugowick.drugopetclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pet_types")
public class PetType extends BaseEntity {

    @Builder
    public PetType(Long id, String name) {
        super(id);
        this.name = name;
    }

    @Column(name = "name", unique = true)
    private String name;

    @Override
    public String toString() {
        return "PetType{" +
                "name='" + name + '\'' +
                '}';
    }
}
