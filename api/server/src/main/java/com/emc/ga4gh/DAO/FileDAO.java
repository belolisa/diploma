package com.emc.ga4gh.DAO;

import com.emc.ga4gh.DTO.FileEntity;
import com.emc.ga4gh.DTO.Reference;

public interface FileDAO extends CrudSelectDAO<FileEntity> {
    FileEntity getByPath(String absolutePath);
}
