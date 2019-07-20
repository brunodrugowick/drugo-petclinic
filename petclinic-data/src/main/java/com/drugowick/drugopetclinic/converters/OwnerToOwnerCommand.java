package com.drugowick.drugopetclinic.converters;

import com.drugowick.drugopetclinic.converters.commands.OwnerCommand;
import com.drugowick.drugopetclinic.model.Owner;
import com.drugowick.drugopetclinic.model.Pet;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OwnerToOwnerCommand implements Converter<Owner, OwnerCommand> {

    private final PetToPetCommand petToPetCommand;

    public OwnerToOwnerCommand(PetToPetCommand petToPetCommand) {
        this.petToPetCommand = petToPetCommand;
    }

    @Override
    public OwnerCommand convert(Owner source) {
        if (source == null) {
            return null;
        }

        final OwnerCommand ownerCommand = new OwnerCommand();
        ownerCommand.setAddress(source.getAddress());
        ownerCommand.setCity(source.getCity());
        ownerCommand.setCountry(source.getCountry());
        ownerCommand.setFirstName(source.getFirstName());
        ownerCommand.setLastName(source.getLastName());
        ownerCommand.setFullName(source.getFullName());
        ownerCommand.setTelephone(source.getTelephone());
        ownerCommand.setId(source.getId());
        for (Pet pet : source.getPets()) {
            ownerCommand.getPets().add(petToPetCommand.convert(pet));
        }

        return ownerCommand;
    }
}
