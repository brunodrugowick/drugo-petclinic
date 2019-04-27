package com.drugowick.drugopetclinic.services.springdatajpa;

import com.drugowick.drugopetclinic.model.PetType;
import com.drugowick.drugopetclinic.repositories.PetTypeRepository;
import com.drugowick.drugopetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("externalDatabase")
public class PetTypeSDJpaService implements PetTypeService {

    private final PetTypeRepository petTypeRepository;

    public PetTypeSDJpaService(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Set<PetType> findAll() {
        Set<PetType> pets = new HashSet<>();
        petTypeRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public PetType findById(Long aLong) {
        return petTypeRepository.findById(aLong).orElse(null);
    }

    @Override
    public PetType save(PetType object) {
        PetType existingPetType = petTypeRepository.findByName(object.getName());
        if (existingPetType == null)
            return petTypeRepository.save(object);
        else
            return existingPetType;
    }

    @Override
    public void delete(PetType object) {
        petTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        petTypeRepository.deleteById(aLong);
    }
}
