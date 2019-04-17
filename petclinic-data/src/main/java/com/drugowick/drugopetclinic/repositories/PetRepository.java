package com.drugowick.drugopetclinic.repositories;

import com.drugowick.drugopetclinic.model.Pet;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PetRepository extends PagingAndSortingRepository<Pet, Long> {
}
