package models.application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ibrahim Olanrewaju S DEV on 30/03/2017.
 */
public class Feature {
// this is to add new feature release
    public long id;
    public String appModule;
    public List<String> subModule = new ArrayList<>();
    public String menuName;
    public String menuHtmlCode;
    public String description;
    public String support;// support, drop support
    public String workFlowHtmlCode;
    public String videoUrl;
    public String devProgress; // underDev,demo, testing, release, modified , new
    public double version;
    public List<Long> developers = new ArrayList<>();
    public long modified;
    public Date modifiedDate;
    public Date lastModifiedDate;

    @Override
    public String toString() {
        return "Feature{" +
                "id=" + id +
                ", appModule='" + appModule + '\'' +
                ", subModule=" + subModule.size() +
                ", menuName='" + menuName + '\'' +
                ", menuHtmlCode='" + menuHtmlCode + '\'' +
                ", description='" + description + '\'' +
                ", support='" + support + '\'' +
                ", workFlowHtmlCode='" + workFlowHtmlCode + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", devProgress='" + devProgress + '\'' +
                ", version=" + version +
                ", developers=" + developers +
                ", modified=" + modified +
                ", modifiedDate=" + modifiedDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
