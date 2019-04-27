package com.drugowick.drugopetclinic.services.springdatajpa;

import com.drugowick.drugopetclinic.model.Specialty;
import com.drugowick.drugopetclinic.model.Vet;
import com.drugowick.drugopetclinic.repositories.VetRepository;
import com.drugowick.drugopetclinic.services.SpecialtyService;
import com.drugowick.drugopetclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("externalDatabase")
public class VetSDJpaService implements VetService {

    private final VetRepository vetRepository;
    private final SpecialtyService specialtyService;

    public VetSDJpaService(VetRepository vetRepository, SpecialtyService specialtyService) {
        this.vetRepository = vetRepository;
        this.specialtyService = specialtyService;
    }

    @Override
    public Set<Vet> findAll() {
        Set<Vet> vets = new HashSet<>();
        vetRepository.findAll().forEach(vets::add);
        return vets;
    }

    @Override
    public Vet findById(Long aLong) {
        return vetRepository.findById(aLong).orElse(null);
    }

    @Override
    public Vet save(Vet object) {
        if (object == null)
            return null;

        Set<Specialty> savedSpecialties = new HashSet<>();
        for (Specialty specialty : object.getSpecialties()) {
            savedSpecialties.add(specialtyService.save(specialty));
        }
        object.setSpecialties(savedSpecialties);

        return vetRepository.save(object);
    }

    @Override
    public void delete(Vet object) {
        vetRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        vetRepository.deleteById(aLong);
    }
}
