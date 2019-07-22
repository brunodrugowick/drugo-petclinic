package com.drugowick.drugopetclinic.converters.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class VisitCommand {
    private LocalDate date;
    private String description;
}
