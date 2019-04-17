package com.drugowick.drugopetclinic.repositories;

import com.drugowick.drugopetclinic.model.Visit;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VisitRepository extends PagingAndSortingRepository<Visit, Long> {
}
