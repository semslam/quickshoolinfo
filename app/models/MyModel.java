package models;

import api.auth.Auth;

import java.util.Date;

/**
 * Created by Ibrahim Olanrewaju S on 14/01/2017.
 */
public class MyModel {
    public double version;
    public int delete;
    public Date deleteDate;
    public int deleteCount;
    public long modifier ;
    public Date modified;
    public Date lastModified;
    public int counter;

    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public int getDeleteCount() {
        return deleteCount;
    }

    public void setDeleteCount(int deleteCount) {
        this.deleteCount = deleteCount;
    }

    public long getModifier() {
        return modifier;
    }

    public void setModifier(long modifier) {
        this.modifier = modifier;
    }

    public Date getModified() {

        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
