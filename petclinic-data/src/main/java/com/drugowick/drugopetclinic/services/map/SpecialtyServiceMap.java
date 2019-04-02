package com.drugowick.drugopetclinic.services.map;

import java.util.Set;

import com.drugowick.drugopetclinic.model.Specialty;
import com.drugowick.drugopetclinic.services.SpecialtyService;

public class SpecialtyServiceMap extends AbstractMapService<Specialty> implements SpecialtyService {
	
	@Override
	public Set<Specialty> findAll() {
		return super.findAll();
	}
	
	@Override
	public Specialty findById(Long id) {
		return super.findById(id);
	}
	
	@Override
	public Specialty save(Specialty object) {
		return super.save(object);
	}
	
	@Override
	public void delete(Specialty object) {
		super.delete(object);
	}
	
	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}
	
}
