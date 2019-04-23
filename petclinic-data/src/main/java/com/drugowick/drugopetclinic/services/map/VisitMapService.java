package com.drugowick.drugopetclinic.services.map;

import com.drugowick.drugopetclinic.model.Visit;
import com.drugowick.drugopetclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class VisitMapService extends AbstractMapService<Visit> implements VisitService {

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Visit object) {
        super.delete(object);
    }

    @Override
    public Visit save(Visit visit) {
        // Verifies if the visit has what's necessary, pet and owner saved (has ID), in order to be then saved.
        if (visit.getPet() == null || visit.getPet().getOwner() == null
            || visit.getPet().getId() == null || visit.getPet().getOwner().getId() == null) {
            throw new RuntimeException("Visits must have an Owner and a Pet.");
        }
        return super.save(visit);
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }
}
