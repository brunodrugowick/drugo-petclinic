package com.drugowick.drugopetclinic.converters;

import com.drugowick.drugopetclinic.converters.commands.PetCommand;
import com.drugowick.drugopetclinic.model.Pet;
import com.drugowick.drugopetclinic.model.Visit;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PetToPetCommand implements Converter<Pet, PetCommand> {

    private final VisitToVisitCommand visitToVisitCommand;

    public PetToPetCommand(VisitToVisitCommand visitToVisitCommand) {
        this.visitToVisitCommand = visitToVisitCommand;
    }

    @Override
    public PetCommand convert(Pet pet) {
        if (pet == null) {
            return null;
        }

        PetCommand petCommand = new PetCommand();
        petCommand.setId(pet.getId());
        petCommand.setBirthDate(pet.getBirthDate());
        petCommand.setName(pet.getName());
        petCommand.setPetType(pet.getPetType().getName());
        for (Visit visit : pet.getVisits()) {
            petCommand.getVisits().add(visitToVisitCommand.convert(visit));
        }

        return petCommand;
    }
}
