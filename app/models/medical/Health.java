/*
 * File   : Health.java
 * Date   : 2016
 * Version: 1.00
 * Author : Ibrahim Olanrewaju Semiu
 * Modified Date : feb 17 2016
 * Copyright (c) 2016
 */
package models.medical;

import java.util.Arrays;

public class Health {
	// this info admin and guardian have right to fill the form
	public String bloodGroup;
	public String genotype;
	public String height;
	public String weight;
	public String bestFoods[];// the best foods which the student like to eat
	public String reactingFoods[];// the foods which the student can not eat
	public String drug[];// the kinds of drugs which the student can use
	public String reactingDrug[];//the kinds of drugs which the student can not take
	public String sicknessType[]; // the
	public String oralHygiene;
	public String specificAilment;
	public String[] allergies;//a condition that makes a person become ill or develop skin or breathing problems because they have eaten certain foods or been near certain substances
	public String[] immunizations;
	public String visionLeft;
	public String visionRight;
	public String[] like;
	public String[] dislike;
	public Health(){}

	@Override
	public String toString() {
		return "Health{" +
				"bloodGroup='" + bloodGroup + '\'' +
				", genotype='" + genotype + '\'' +
				", height='" + height + '\'' +
				", weight='" + weight + '\'' +
				", bestFoods=" + Arrays.toString(bestFoods) +
				", reactingFoods=" + Arrays.toString(reactingFoods) +
				", drug=" + Arrays.toString(drug) +
				", reactingDrug=" + Arrays.toString(reactingDrug) +
				", sicknessType=" + Arrays.toString(sicknessType) +
				", oralHygiene='" + oralHygiene + '\'' +
				", specificAilment='" + specificAilment + '\'' +
				", Allergies=" + Arrays.toString(allergies) +
				", immunizations=" + Arrays.toString(immunizations) +
				", visionLeft='" + visionLeft + '\'' +
				", visionRight='" + visionRight + '\'' +
				", like=" + Arrays.toString(like) +
				", dislike=" + Arrays.toString(dislike) +
				'}';
	}

	public void add(){
	String add =	Arrays.toString(bestFoods);
		System.out.println(add);
	}
}
