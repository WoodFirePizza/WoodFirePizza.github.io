/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connecttoapi;

/**
 *
 * @author mgsadumiano
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
    
    public Person() {
        Person_ID = null;
        Forename = null;
        Surname = null;
        DOB = null;
        Email = null;
        Mobile_Number = null;
        Town_City = null;
        County = null;
        Postcode = null;
        Address1 = null;
        Address2 = null;
    }
    public Person(String id, String fname, String lname, String dob, String email, String mobile, 
            String town, String county, String postcode, String addr1) {
        
        this();
        Person_ID = id;
        Forename = fname;
        Surname = lname;
        DOB = dob;
        Email = email;
        Mobile_Number = mobile;
        Town_City = town;
        County = county;
        Postcode = postcode;
        Address1 = addr1;
        
        
    }
}
