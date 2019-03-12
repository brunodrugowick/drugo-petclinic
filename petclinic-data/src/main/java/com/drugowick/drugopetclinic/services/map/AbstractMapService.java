package com.drugowick.drugopetclinic.services.map;

import com.drugowick.drugopetclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity> {

    protected Map<Long, T> map = new HashMap<>();

    Set<T> findAll(){
        return new HashSet<>(map.values());
    }

    T findById(Long id) {
        return map.get(id);
    }

    T save(T object) {

        if (object == null)
            throw new RuntimeException("Object cannot be null.");

        if(object.getId() == null) {
            object.setId(getNextId());
        }
        map.put(object.getId(), object);

        return object;
    }

    void deleteById(Long id) {
        map.remove(id);
    }

    void delete (T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    private Long getNextId() {

        if (map.keySet().isEmpty()) return 1L;

        return Collections.max(map.keySet()) + 1;
    }

}
