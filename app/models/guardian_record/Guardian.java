/*
 * File   : Guardian.java
 * Date   : 2016
 * Version: 1.00
 * Author : Ibrahim Olanrewaju Semiu
 * Modified Date : feb 17 2016
 * Copyright (c) 2016
 */

package models.guardian_record;

import models.Contact;
import org.jongo.marshall.jackson.oid.Id;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Guardian{
	@Id
	public long _id; // school id stand as guardian
	public String schoolId;
	public String title;
	public String firstName;
	public String lastName;
	public String nickName;
	public String relationship;// father, mother
	public List<Long> studentId;// = new ArrayList<>();//  a parent can have more than one student in a school
	public String gender;
	public String occupation;
	public double income;
	public Date dob;
	public String religion;
	public String tribe; // tribe
	public List<String> communicationPreference;// home, business, sms, mail
	//public String driverLicence;
	public List<Contact> contact;
	public long modifier;
	public Date modifierDate;
	public Date lastModified;
	public int counter;

	public Guardian(){}

	@Override
	public String toString() {
		return "Guardian{" +
				"id=" + _id +
				", schoolId='" + schoolId + '\'' +
				", relationship='" + relationship + '\'' +
				", title='" + title + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", nickName='" + nickName + '\'' +
				", studentId=" + studentId +
				", gender='" + gender + '\'' +
				", occupation='" + occupation + '\'' +
				", income=" + income +
				", dob=" + dob +
				", religion='" + religion + '\'' +
				", tribe='" + tribe + '\'' +
				", communicationPreference=" + communicationPreference +
				", contact=" + contact +
				", modifier=" + modifier +
				", modifierDate=" + modifierDate +
				", lastModified=" + lastModified +
				", counter=" + counter +
				'}';
	}
}
