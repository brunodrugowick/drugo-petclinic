package com.drugowick.drugopetclinic.repositories;

import com.drugowick.drugopetclinic.model.Person;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
}
