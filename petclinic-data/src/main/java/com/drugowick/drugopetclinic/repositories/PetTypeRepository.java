package com.drugowick.drugopetclinic.repositories;

import com.drugowick.drugopetclinic.model.PetType;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PetTypeRepository extends PagingAndSortingRepository<PetType, Long> {

    PetType findByName(String name);
}
