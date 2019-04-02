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
    private final PetTypeService petTypeService;
    private final PetService petService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, PetService petService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = createAndSavePetType("Dog");
        PetType cat = createAndSavePetType("Cat");

        Owner bruno = createAndSaveOwner("Bruno", "Uno");
        Owner lara = createAndSaveOwner("Lara", "Rara");

        createAndSavePet(dog, bruno, LocalDate.of(2012, 12,25));
        createAndSavePet(cat, lara, LocalDate.of(2012, 04,01));

        createAndSaveVet("Marcos", "Parcos");
        createAndSaveVet("Jonas", "Monas");
    }

    private void createAndSavePet(PetType petType, Owner owner, LocalDate birthDate) {
        Pet pet = new Pet();
        pet.setBirthDate(birthDate);
        pet.setOwner(owner);
        pet.setPetType(petType);
        petService.save(pet);
        System.out.println("Loaded pet: " + pet.toString());
    }

    private void createAndSaveVet(String firstName, String lastName) {
        Vet vet = new Vet();
        vet.setFirstName(firstName);
        vet.setLastName(lastName);
        vetService.save(vet);
        System.out.println("Loaded vet: " + vet.toString());
    }

    private Owner createAndSaveOwner(String firstName, String lastName) {
        Owner owner = new Owner();
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        ownerService.save(owner);
        System.out.println("Loaded owner: " + owner.toString());
        return owner;
    }

    private PetType createAndSavePetType(String petName) {
        PetType petType = new PetType();
        petType.setName(petName);
        petTypeService.save(petType);
        System.out.println("Loaded pet type: " + petType.toString());
        return petType;
    }
}
