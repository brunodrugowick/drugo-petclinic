package com.drugowick.drugopetclinic.bootstrap;

import com.drugowick.drugopetclinic.model.*;
import com.drugowick.drugopetclinic.services.OwnerService;
import com.drugowick.drugopetclinic.services.VetService;
import com.drugowick.drugopetclinic.services.VisitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * This is a Data Loader, responsible for creating new data on the database.
 */
@Component
public class DataLoader {

    //TODO refactor to leverage on Spring stuff.
    private final OwnerService ownerService;
    private final VetService vetService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.visitService = visitService;
    }

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    public void loadData() {

        cleanUpDatabase();

        PetType typeDog = new PetType("Dog");
        PetType typeCat = new PetType("Cat");
        PetType typeParrot = new PetType("Parrot");
        PetType typeSnake = new PetType("Snake");

        Owner bruno = createAndSaveOwner("Bruno", "Uno", "Rua Paulo Setubal, 415",
                "Campinas", "Brazil", "5519996559966");
        Owner lara = createAndSaveOwner("Lara", "Rara", "Ifsdgsdf 12",
                "Amsterdam", "Netherlands", "5519983559523");
        Owner robert = createAndSaveOwner("Robert", "Duval", "Holywood street, 123",
                "Los Angeles", "US", "11233455678");
        Owner anna = createAndSaveOwner("Anna", "Bacanna", "Coolplace, 983",
                "Belo Horizonte", "Brazil", "5511239402334");

        Set<Pet> pets = new HashSet<>();
        pets.add(createAndSavePet("Melo", typeDog, bruno, LocalDate.of(2012, 12,25)));
        pets.add(createAndSavePet("Tukinha", typeCat, lara, LocalDate.of(2012, 04,01)));
        pets.add(createAndSavePet("Celeste", typeSnake, robert, LocalDate.of(2015, 07,11)));
        pets.add(createAndSavePet("Xunênis", typeCat, bruno, LocalDate.of(2012, 12,25)));
        pets.add(createAndSavePet("Hablante", typeParrot, anna, LocalDate.of(2018, 04,01)));
        pets.add(createAndSavePet("Parlante", typeParrot, anna, LocalDate.of(2009, 07,11)));

        Specialty specialtyLargeAnimals = new Specialty("Surgery");
        Specialty specialtyTinyAnimals = new Specialty("Dentistry");
        Specialty specialtyRadiology = new Specialty("Radiology");

        createAndSaveVet("Marcos", "Parcos", specialtyLargeAnimals);
        createAndSaveVet("Jonas", "Monas", specialtyTinyAnimals, specialtyRadiology);
        createAndSaveVet("Julius", "Moolius");

        pets.forEach(pet -> {
           createAndSaveVisits(pet);
        });
    }

    /**
     * Cleans up main entities. Cascade takes care of deleting other entities.
     *
     * Entities not cascaded are not deleted because they have verifications
     * in order not to have duplicates in their own Service implementations.
     */
    private void cleanUpDatabase() {
        log.warn("Cleaning up database (main entities). Specialties and PetTypes will remain.");
        ownerService.findAll().forEach(owner -> ownerService.delete(owner));
        vetService.findAll().forEach(vet -> vetService.delete(vet));
    }

    private Set<Visit> createAndSaveVisits(Pet pet) {
        Set<Visit> visits = new HashSet<>();

        Visit visit = new Visit();
        visit.setDate(LocalDate.now());
        visit.setDescription("Visit " + visit.getDate());
        visit.setPet(pet);
        visitService.save(visit);
        log.info("Visit created: " + visit.toString());
        visits.add(visit);

        //Add a second visit to the same pet.
        visit.setDate(LocalDate.of(2020, 4, 23));
        visit.setDescription("Visit " + visit.getDate());
        visitService.save(visit);
        log.info("Visit created: " + visit.toString());
        visits.add(visit);

        return visits;
    }

    private Pet createAndSavePet(String petName, PetType petType, Owner owner, LocalDate birthDate) {
        Pet pet = new Pet();
        pet.setName(petName);
        pet.setBirthDate(birthDate);
        pet.setOwner(owner);
        pet.setPetType(petType);

        Set<Pet> pets = owner.getPets();
        pets.add(pet);
        owner.setPets(pets);
        
        Owner saved = ownerService.save(owner);
        log.info("Pet added to owner: " + saved.toString());
        return pet;
    }

    private Vet createAndSaveVet(String firstName, String lastName, Specialty... specialties) {
        Vet vet = new Vet();
        vet.setFirstName(firstName);
        vet.setLastName(lastName);
        
        Set<Specialty> vetSpecialties = vet.getSpecialties();
        for (Specialty specialty: specialties) {
            vetSpecialties.add(specialty);
        }
        vet.setSpecialties(vetSpecialties);
        
        Vet saved = vetService.save(vet);
        log.info("Loaded vet: " + saved.toString());
        return saved;
    }

    private Owner createAndSaveOwner(String firstName, String lastName, String address, String city, String country, String phoneNumber) {
        Owner owner = new Owner();
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        owner.setAddress(address);
        owner.setCity(city);
        owner.setCountry(country);
        owner.setTelephone(phoneNumber);

        Owner saved = ownerService.save(owner);
        log.info("Loaded owner: " + saved.toString());
        return saved;
    }
}
