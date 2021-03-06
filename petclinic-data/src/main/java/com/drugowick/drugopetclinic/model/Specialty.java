package com.drugowick.drugopetclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "specialties")
public class Specialty extends BaseEntity {

    @Column(name = "description", unique = true)
    private String description;

    @Override
    public String toString() {
        return "Specialty{" +
                "description='" + description + '\'' +
                '}';
    }
}
