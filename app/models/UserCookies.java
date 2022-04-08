package models;

import org.jongo.marshall.jackson.oid.Id;

/**
 * Created by Ibrahim Olanrewaju Abdulsemiu on 28/08/2017.
 */
public class UserCookies extends MyModel{
    @Id
    public long _id; // user id
    public String schoolId;
    public String userAgent;
    public String acceptLanguage;
    public String subDomain;
    public String domain;
    public String ipAddress;
    public String geoLatitude;
    public String geoLongtitude;
    public String geoAddress;
    public String deviceBrowserType;
    public String device;
    public String deviceName;
    public String deviceOs;

    @Override
    public String toString() {
        return "UserCookies{" +
                "id=" + _id +
                ", schoolId='" + schoolId + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", acceptLanguage='" + acceptLanguage + '\'' +
                ", subDomain='" + subDomain + '\'' +
                ", domain='" + domain + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", geoLatitude='" + geoLatitude + '\'' +
                ", geoLongtitude='" + geoLongtitude + '\'' +
                ", geoAddress='" + geoAddress + '\'' +
                ", deviceBrowserType='" + deviceBrowserType + '\'' +
                ", device='" + device + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceOs='" + deviceOs + '\'' +
                '}';
    }
}
