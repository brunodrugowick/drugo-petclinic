package com.drugowick.drugopetclinic.services.map;

import com.drugowick.drugopetclinic.model.Vet;
import com.drugowick.drugopetclinic.services.SpecialtyService;
import com.drugowick.drugopetclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetMapService extends AbstractMapService<Vet> implements VetService {
	
	private final SpecialtyService specialtyService;
	
	public VetMapService(SpecialtyService specialtyService) {
		this.specialtyService = specialtyService;
	}
	
    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public Vet save(Vet object) {
    	if (object == null)
            return null;

        if (object.getSpecialties() != null) {
            object.getSpecialties().forEach(specialty -> {
                if (specialty.getId() == null) {
                    specialty.setId(specialtyService.save(specialty).getId());
                }
            });
        }
        return super.save(object);
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
}
