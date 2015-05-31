package com.emc.ga4gh.DAO.orient.object;

import com.emc.ga4gh.DAO.FileDAO;
import com.emc.ga4gh.DTO.FileEntity;
import com.emc.ga4gh.DTO.Variant;
import org.springframework.stereotype.Repository;

@Repository
public class FileObjectDAO extends AbstractObjectDAO<FileEntity> implements FileDAO {
    @Override
    protected String getCollectionName() {
        return "File";
    }


    @Override
    public FileEntity getByPath(String absolutePath) {
        return null;
    }
}
