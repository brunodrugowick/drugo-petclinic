package com.drugowick.drugopetclinic.repositories;

import com.drugowick.drugopetclinic.model.Specialty;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SpecialtyRepository extends PagingAndSortingRepository<Specialty, Long> {

    Specialty findByDescription(String description);
}
