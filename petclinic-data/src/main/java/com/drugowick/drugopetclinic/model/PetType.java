package com.drugowick.drugopetclinic.model;

public class PetType extends BaseEntity {

    private String name;

    public PetType(String type) {
        super();
        this.name = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PetType{" +
                "name='" + name + '\'' +
                '}';
    }
}
