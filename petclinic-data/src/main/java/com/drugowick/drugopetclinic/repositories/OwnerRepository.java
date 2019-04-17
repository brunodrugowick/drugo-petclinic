package com.drugowick.drugopetclinic.repositories;

import com.drugowick.drugopetclinic.model.Owner;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OwnerRepository extends PagingAndSortingRepository<Owner, Long> {
}
