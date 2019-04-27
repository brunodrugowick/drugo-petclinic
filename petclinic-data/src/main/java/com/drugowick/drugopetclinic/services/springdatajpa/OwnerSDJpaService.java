package com.drugowick.drugopetclinic.services.springdatajpa;

import com.drugowick.drugopetclinic.model.Owner;
import com.drugowick.drugopetclinic.repositories.OwnerRepository;
import com.drugowick.drugopetclinic.services.OwnerService;
import com.drugowick.drugopetclinic.services.PetService;
import com.drugowick.drugopetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("externalDatabase")
public class OwnerSDJpaService implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PetService petService;
    private final PetTypeService petTypeService;

    public OwnerSDJpaService(OwnerRepository ownerRepository, PetService petService, PetTypeService petTypeService) {
        this.ownerRepository = ownerRepository;
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners = new HashSet<>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public Owner findById(Long aLong) {
        return ownerRepository.findById(aLong).orElse(null);
    }

    @Override
    public Owner save(Owner object) {
        if (object == null)
            return null;

        object.getPets().forEach(pet -> {
            pet.setPetType(petTypeService.save(pet.getPetType()));
            petService.save(pet);
        });

        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        ownerRepository.deleteById(aLong);
    }
}
