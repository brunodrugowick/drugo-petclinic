package com.drugowick.drugopetclinic.converters.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class OwnerCommand {
    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String address;
    private String city;
    private String country;
    private String telephone;
    private Set<PetCommand> pets = new HashSet<>();
}