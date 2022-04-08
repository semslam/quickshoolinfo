package api.util.browser_util;


import models.enum_config.YesNoEnum;

/**
 * Created by TRAVELDEN DEV on 24/02/2017.
 */
public class WebTraffic {

    public String referring_url;
    public String ip_address;
    public String ip_country;
    public String ip_country_code;
    public String time_zone;
    public String ip_city;
    public String cookie_id;
    public String http_user_agent;
   // @Enumerated
    public YesNoEnum is_mobile;
    public String machine_name;
}
