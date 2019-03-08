package com.drugowick.drugopetclinic.services;

import com.drugowick.drugopetclinic.model.Owner;

import java.util.Set;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

}
