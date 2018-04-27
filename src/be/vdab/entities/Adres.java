package be.vdab.entities;

public class Adres {
 private String straat;
 private String huisNr;
 private int postcode;
 private String gemeente;
 
public Adres() {
}

public Adres(String straat, String huisNr, int postcode, String gemeente) {
	this.straat = straat;
	this.huisNr = huisNr;
	this.postcode = postcode;
	this.gemeente = gemeente;
}
public String getStraat() {
	return straat;
}
public void setStraat(String straat) {
	this.straat = straat;
}
public String getHuisNr() {
	return huisNr;
}
public void setHuisNr(String huisNr) {
	this.huisNr = huisNr;
}
public int getPostcode() {
	return postcode;
}
public void setPostcode(int postcode) {
	this.postcode = postcode;
}
public String getGemeente() {
	return gemeente;
}
public void setGemeente(String gemeente) {
	this.gemeente = gemeente;
}
 
}
