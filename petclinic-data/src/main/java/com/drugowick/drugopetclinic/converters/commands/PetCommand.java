package com.drugowick.drugopetclinic.converters.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PetCommand {
    private Long id;
    private String name;
    private String petType;
    private LocalDate birthDate;
    private Set<VisitCommand> visits = new HashSet<>();
}
