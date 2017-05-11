/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chef.datamodel;

import java.util.Date;

/**
 *
 * @author RhysJones
 */
public class Person {
    
    private String Person_ID;
    private String Forename;
    private String Surname;
    private String DOB;
    private String Email;
    private String Mobile_Number;
    private String Town_City;
    private String County;
    private String Postcode;
    private String Address2;
    private String Address1;

    
    public Person(){
    }

    public Person(String Person_ID, String Forename, String Surname, String DOB, String Email, String Mobile_Number, String Town_City, String County, String Postcode, String Address1) {
        this.Person_ID = Person_ID;
        this.Forename = Forename;
        this.Surname = Surname;
        this.DOB = DOB;
        this.Email = Email;
        this.Mobile_Number = Mobile_Number;
        this.Town_City = Town_City;
        this.County = County;
        this.Postcode = Postcode;
        this.Address1 = Address1;
        this.Address2 = Address2;
    }

    public String getPerson_ID() {
        return Person_ID;
    }

    public void setPerson_ID(String Person_ID) {
        this.Person_ID = Person_ID;
    }

    public String getForename() {
        return Forename;
    }

    public void setForename(String Forename) {
        this.Forename = Forename;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String Surname) {
        this.Surname = Surname;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getMobile_Number() {
        return Mobile_Number;
    }

    public void setMobile_Number(String Mobile_Number) {
        this.Mobile_Number = Mobile_Number;
    }

    public String getTown_City() {
        return Town_City;
    }

    public void setTown_City(String Town_City) {
        this.Town_City = Town_City;
    }

    public String getCounty() {
        return County;
    }

    public void setCounty(String County) {
        this.County = County;
    }

    public String getPostcode() {
        return Postcode;
    }

    public void setPostcode(String Postcode) {
        this.Postcode = Postcode;
    }

    public String getAddress1() {
        return Address1;
    }

    public void setAddress1(String Address1) {
        this.Address1 = Address1;
    }

    public String getAddress2() {
        return Address2;
    }

    public void setAddress2(String Address2) {
        this.Address2 = Address2;
    }
    
    
}
