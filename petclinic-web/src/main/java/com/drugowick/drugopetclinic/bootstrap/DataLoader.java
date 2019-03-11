package com.drugowick.drugopetclinic.bootstrap;

import com.drugowick.drugopetclinic.model.Owner;
import com.drugowick.drugopetclinic.model.Vet;
import com.drugowick.drugopetclinic.services.OwnerService;
import com.drugowick.drugopetclinic.services.VetService;
import com.drugowick.drugopetclinic.services.map.OwnerServiceMap;
import com.drugowick.drugopetclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    //TODO refactor to leverage on Spring stuff.
    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader() {
        this.ownerService = new OwnerServiceMap();
        this.vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Bruno");
        owner1.setLastName("Muniz");

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Lara");
        owner2.setLastName("Herrera");

        ownerService.save(owner1);
        ownerService.save(owner2);
        System.out.println("Owners loaded.");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Marcos");
        vet1.setLastName("Parcos");

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Jonas");
        vet2.setLastName("Monas");

        vetService.save(vet1);
        vetService.save(vet2);
        System.out.println("Vets loaded.");

        for (Vet vet : vetService.findAll()) {
            System.out.println(vet.getLastName());
        }
    }
}
