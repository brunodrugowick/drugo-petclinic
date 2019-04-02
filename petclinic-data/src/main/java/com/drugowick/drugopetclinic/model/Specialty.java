package com.drugowick.drugopetclinic.model;

public class Specialty extends BaseEntity {

    private String description;

    public Specialty(String description) {
		this.description = description;
	}

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Specialty{" +
                "description='" + description + '\'' +
                '}';
    }
}
