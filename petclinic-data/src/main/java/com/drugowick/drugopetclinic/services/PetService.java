package com.drugowick.drugopetclinic.services;

import com.drugowick.drugopetclinic.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findById(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();
}
