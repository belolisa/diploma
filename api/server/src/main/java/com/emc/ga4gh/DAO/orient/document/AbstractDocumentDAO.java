package com.emc.ga4gh.DAO.orient.document;

import com.emc.ga4gh.DAO.CrudSelectDAO;
import com.emc.ga4gh.DAO.OTransacrional;
import com.emc.ga4gh.DTO.Entity;
import com.orientechnologies.orient.core.record.impl.ODocument;

/**
 * Created by Elizaveta Belokopytova.
 */

@OTransacrional
public abstract class AbstractDocumentDAO<T extends Entity> implements CrudSelectDAO<T> {

    @Override
    public T create(T newInstance) {
        ODocument document = createDocument(newInstance);
        document = document.save();
        newInstance.setRid(document.getIdentity().toString());
        return newInstance;
    }

    @Override
    public T read(String id) {

        return null;
    }

    @Override
    public void update(T transientObject) {

    }

    @Override
    public void delete(T persistentObject) {

    }

    protected abstract ODocument createDocument(T newInstance);
}
