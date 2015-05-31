package com.emc.ga4gh.DTO;

import javax.persistence.Id;
import javax.persistence.Version;

public class FileEntity implements Entity {

    @Version
    private Integer version = 0;

    @Id
    private String rid;

    private String remotePath;

    private String localPath;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public FileEntity getFile() {
        return this;
    }

    @Override
    public void setFile(FileEntity file) {
        setRid(file.getRid());
        setLocalPath(file.getLocalPath());
        setRemotePath(file.getLocalPath());
        setVersion(file.getVersion()    );
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getRemotePath() {
        return remotePath;
    }

    public void setRemotePath(String remotePath) {
        this.remotePath = remotePath;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
}
