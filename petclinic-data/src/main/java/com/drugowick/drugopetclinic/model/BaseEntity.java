package com.drugowick.drugopetclinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public BaseEntity(Long id) {
        this.id = id;
    }

    /**
     * Used to return if a object is new (not saved to the database) which would generate
     * an ID.
     *
     * This is used, for example, on Thymeleaf templates to determine if the object being created
     * id new or not, changing labels from "Save" to "Create" and so on...
     *
     * @return
     */
    public boolean isNew() {
        return this.id == null;
    }
}
