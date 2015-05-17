package com.emc.ga4gh.DAO;

import com.emc.ga4gh.DTO.Entity;
import com.emc.ga4gh.DTO.Read;

import java.util.List;

/**
 * Created by Elizaveta Belokopytova.
 */

public interface CrudSelectDAO<T extends Entity> {

    T create(T newInstance);

    T read(String id);

    void update(T transientObject);

    void delete(T persistentObject);
}
