package com.drugowick.drugopetclinic.services.map;

import com.drugowick.drugopetclinic.model.Owner;
import com.drugowick.drugopetclinic.services.OwnerService;
import com.drugowick.drugopetclinic.services.PetService;
import com.drugowick.drugopetclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OwnerMapService extends AbstractMapService<Owner> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerMapService(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    /**
     * This method takes care of saving the owner and all its related assets, like pets and petType.
     *
     * TODO this method doesn't take duplicated pets or petTypes into consideration.
     * @param object
     * @return
     */
    @Override
    public Owner save(Owner object) {
        if (object == null)
            return null;

        if (object.getPets() != null) {
            object.getPets().forEach(pet -> {
                if (pet.getPetType() != null) {
                    if (pet.getPetType().getId() == null) {
                        pet.setPetType(petTypeService.save(pet.getPetType()));
                    }
                } else {
                    throw new RuntimeException("Pet Type is required.");
                }

                if (pet.getId() == null) {
                    pet.setId(petService.save(pet).getId());
                }
            });
        }
        return super.save(object);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        //TODO implement this.
        return null;
    }
}
