package com.drugowick.drugopetclinic.bootstrap;

import com.drugowick.drugopetclinic.model.Owner;
import com.drugowick.drugopetclinic.model.Pet;
import com.drugowick.drugopetclinic.model.PetType;
import com.drugowick.drugopetclinic.model.Vet;
import com.drugowick.drugopetclinic.services.OwnerService;
import com.drugowick.drugopetclinic.services.PetService;
import com.drugowick.drugopetclinic.services.PetTypeService;
import com.drugowick.drugopetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

/**
 * This class extends CommandLineRunner, which Spring runs just after loading everything
 * and before run the application custom code.
 *
 * This is a bootstrap class to create example data for development and test purposes.
 */
@Component
public class DataLoader implements CommandLineRunner {

    //TODO refactor to leverage on Spring stuff.
    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType typeDog = new PetType("Dog");
        PetType typeCat = new PetType("Cat");

        Owner bruno = createAndSaveOwner("Bruno", "Uno", "Rua Paulo Setubal, 415",
                "Campinas", "Brazil", "5519996559966");
        Owner lara = createAndSaveOwner("Lara", "Rara", "Ifsdgsdf 12",
                "Amsterdam", "Netherlands", "5519983559523");

        createAndSavePet("Melo", typeDog, bruno, LocalDate.of(2012, 12,25));
        createAndSavePet(" Tukinha", typeCat, lara, LocalDate.of(2012, 04,01));

        createAndSaveVet("Marcos", "Parcos");
        createAndSaveVet("Jonas", "Monas");
    }

    private void createAndSavePet(String petName, PetType petType, Owner owner, LocalDate birthDate) {
        Pet pet = new Pet();
        pet.setName(petName);
        pet.setBirthDate(birthDate);
        pet.setOwner(owner);
        pet.setPetType(petType);

        Set<Pet> pets = owner.getPets();
        pets.add(pet);
        owner.setPets(pets);
        ownerService.save(owner);
        System.out.println("Pet added to owner: " + owner.toString());
    }

    private void createAndSaveVet(String firstName, String lastName) {
        Vet vet = new Vet();
        vet.setFirstName(firstName);
        vet.setLastName(lastName);
        vetService.save(vet);
        System.out.println("Loaded vet: " + vet.toString());
    }

    private Owner createAndSaveOwner(String firstName, String lastName, String address, String city, String country, String phoneNumber) {
        Owner owner = new Owner();
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        owner.setAddress(address);
        owner.setCity(city);
        owner.setCountry(country);
        owner.setTelephone(phoneNumber);
        ownerService.save(owner);
        System.out.println("Loaded owner: " + owner.toString());
        return owner;
    }
}
