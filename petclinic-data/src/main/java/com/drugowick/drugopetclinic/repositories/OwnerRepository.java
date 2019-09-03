package com.drugowick.drugopetclinic.repositories;

import com.drugowick.drugopetclinic.model.Owner;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OwnerRepository extends PagingAndSortingRepository<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByFirstNameLike(String firstName);
}
