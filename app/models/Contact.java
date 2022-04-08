/*
 * File   : Address.java
 * Date   : 2016
 * Version: 1.00
 * Author : Ibrahim Olanrewaju Semiu
 * Modified Date : feb 17 2016
 * Copyright (c) 2016
 */
package models;

import java.util.List;
import java.util.Map;

public class Contact {
	public String contactName;
	public String email;
	public List<String> phone;// business phone no, home phone no, office phone no
	public String fax;// provide fax number if possible
	public String country;// country or region
	public String state;
	public String city;
	public String street;
	public String zipCode;


	@Override
	public String toString() {
		return "Contact{" +
				"contactName='" + contactName + '\'' +
				", email='" + email + '\'' +
				", phone=" + phone +
				", fax='" + fax + '\'' +
				", country='" + country + '\'' +
				", state='" + state + '\'' +
				", city='" + city + '\'' +
				", street='" + street + '\'' +
				", zipCode='" + zipCode + '\'' +
				'}';
	}
}
