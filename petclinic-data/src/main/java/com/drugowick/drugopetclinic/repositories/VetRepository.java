package com.drugowick.drugopetclinic.repositories;

import com.drugowick.drugopetclinic.model.Vet;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VetRepository extends PagingAndSortingRepository<Vet, Long> {
}
