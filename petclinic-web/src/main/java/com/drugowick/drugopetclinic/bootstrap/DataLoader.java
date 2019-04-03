package com.drugowick.drugopetclinic.bootstrap;

import com.drugowick.drugopetclinic.model.Owner;
import com.drugowick.drugopetclinic.model.Pet;
import com.drugowick.drugopetclinic.model.PetType;
import com.drugowick.drugopetclinic.model.Specialty;
import com.drugowick.drugopetclinic.model.Vet;
import com.drugowick.drugopetclinic.services.OwnerService;
import com.drugowick.drugopetclinic.services.PetService;
import com.drugowick.drugopetclinic.services.PetTypeService;
import com.drugowick.drugopetclinic.services.VetService;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${petclinic.devmode:#{'0'}}")
    private String devMode;

    @Value("${petclinic.devmode.password:#{'xibanga'}}")
    private String devPass;

    @Override
    public void run(String... args) throws Exception {

        if (devMode.equals("1") && devPass.equals("aiowas")) {
            System.out.println("DEVMODE: Sample data will be created.");
            loadData();
        }
    }

    private void loadData() {
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

        createAndSavePet("Melo", typeDog, bruno, LocalDate.of(2012, 12,25));
        createAndSavePet("Tukinha", typeCat, lara, LocalDate.of(2012, 04,01));
        createAndSavePet("Celeste", typeSnake, robert, LocalDate.of(2015, 07,11));
        createAndSavePet("Xunênis", typeCat, bruno, LocalDate.of(2012, 12,25));
        createAndSavePet("Hablante", typeParrot, anna, LocalDate.of(2018, 04,01));
        createAndSavePet("Parlante", typeParrot, anna, LocalDate.of(2009, 07,11));

        Specialty specialtyLargeAnimals = new Specialty("Surgery");
        Specialty specialtyTinyAnimals = new Specialty("Dentistry");
        Specialty specialtyRadiology = new Specialty("Radiology");

        createAndSaveVet("Marcos", "Parcos", specialtyLargeAnimals);
        createAndSaveVet("Jonas", "Monas", specialtyTinyAnimals, specialtyRadiology);
        createAndSaveVet("Julius", "Moolius");
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
        System.out.println("Pet added to owner: " + saved.toString());
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
        System.out.println("Loaded vet: " + saved.toString());
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
        System.out.println("Loaded owner: " + saved.toString());
        return saved;
    }
}
