package com.drugowick.drugopetclinic.bootstrap;

import com.drugowick.drugopetclinic.model.Owner;
import com.drugowick.drugopetclinic.model.Vet;
import com.drugowick.drugopetclinic.services.OwnerService;
import com.drugowick.drugopetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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

        Owner owner1 = new Owner();
        owner1.setFirstName("Bruno");
        owner1.setLastName("Uno");

        Owner owner2 = new Owner();
        owner2.setFirstName("Lara");
        owner2.setLastName("Rara");

        ownerService.save(owner1);
        ownerService.save(owner2);

        Vet vet1 = new Vet();
        vet1.setFirstName("Marcos");
        vet1.setLastName("Parcos");

        Vet vet2 = new Vet();
        vet2.setFirstName("Jonas");
        vet2.setLastName("Monas");

        vetService.save(vet1);
        vetService.save(vet2);

        for (Vet vet : vetService.findAll()) {
            System.out.println("Loaded vet: " + vet.toString());
        }

        for (Owner owner : ownerService.findAll()) {
            System.out.println("Loaded owner: " + owner.toString());
        }
    }
}
