/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skogemann.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import skogemann.service.AddressService;
import skogemann.service.CityInfoService;
import skogemann.service.HobbyService;
import skogemann.service.PersonService;

/**
 *
 * @author thomas
 */
public class Main {

    public static void main(String[] arg) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CA2PU");
        AddressService as = new AddressService();
        CityInfoService cs = new CityInfoService();
        HobbyService hs = new HobbyService();
        PersonService ps = new PersonService(emf, as, cs, hs);
        Person klaus = new Person();

        klaus.setFirstName("Klaus");
        klaus.setLastName("Groenbaek");
        
        // Hobby
        Hobby strikke = new Hobby();
        strikke.setDescription("håndarbejde");
        strikke.setName("Strikke");
        strikke.getPersons().add(klaus);
        Hobby kirke = new Hobby();
        kirke.setDescription("mundarbejde");
        kirke.setName("Kirke");
        kirke.getPersons().add(klaus);
        ArrayList<Hobby> hobbyList = new ArrayList<>();
        hobbyList.add(strikke);
        hobbyList.add(kirke);
        klaus.setHobbies(hobbyList);
        
        for (Hobby h : klaus.getHobbies()){
            System.out.println("Klaus går til " + h.getName());
        }
        System.out.println(klaus.getHobbies().iterator().next().getName() + "  første af hvad klaus går til");
        
        // set email
        klaus.setEmail("klaus@hub.com");
        Address klausAddress = new Address();
        klausAddress.setStreet("Rådhuspladsen");
        klausAddress.setAdditionalInfo("tæt på McD");
        CityInfo vanloese = new CityInfo();
        vanloese.setCity("Vanløse");
        vanloese.setZipCode(2720);
        klausAddress.setCityInfo(vanloese);
        klaus.setAddress(klausAddress);
        klaus.addPhone("abe123", "home").addPhone("911", "mobil");
        
        ps.savePerson(klaus);
        ps.savePerson(klaus);
        
        // get person with hobby  kirke.getName();
        List<Person> personsWithHobby = ps.getPersonsWithHobby(kirke.getName());
        System.out.println(personsWithHobby.size() + " antallet på folk som går i kirken");
        for (Person p : personsWithHobby) {
            System.out.println(p.getFirstName() + " Hvad han hedder");
            for (Hobby h : p.getHobbies()){
            System.out.println(h.getName() + " Hvad " + p.getFirstName() + " går til");
            }
            }
        // get person with hobby null
        personsWithHobby = ps.getPersonsWithHobby("ost");
        System.out.println(personsWithHobby.size() + " BØR VÆRE 0");
        for (Person p : personsWithHobby) {
            System.out.println(p.getFirstName() + " FEJL!");

        }
        // get person with phone number
        List<Person> personsWithNumber = ps.getPersonsWithPhoneNumber("abe123");
        System.out.println(personsWithNumber.size() + " antallet af folk med nummer: abe123");
        for (Person p: personsWithNumber){
            System.out.println(p.getLastName() + " har nummer abe123");
            System.out.println(p.getAddress().getStreet() + " street");
            System.out.println(p.getAddress().getCityInfo().getCity() + " City");
        }
        // get person with wrong phone number
        personsWithNumber = ps.getPersonsWithPhoneNumber("wrong123");
        System.out.println(personsWithNumber.size() + " antallet bør være nul");
        for (Person p: personsWithNumber){
            System.out.println(p.getLastName() + " FEJL");
        }
        
        //get person with zipcode
        List<Person> personsWithZipCode = ps.getPersonsWithZipCode(2720);
        System.out.println(personsWithZipCode.size() + " antal");
        for (Person p: personsWithZipCode) {
            System.out.println(p.getFirstName() +" " + p.getLastName() + " navn på folk som bor i 2720");
        }
        
        //get person with city
        List<Person> personsWithCityName = ps.getPersonsWithCityName("Vanløse");
        System.out.println(personsWithCityName.size() + " antal");
        for (Person p: personsWithCityName) {
            System.out.println(p.getFirstName() + " " + p.getLastName() +
                    " description af det første han går til: " 
                    + p.getHobbies().iterator().next().getDescription());
        }
        
        // get count of  members doing a specifik hobby
        System.out.println(hs.getCountOfSpecificHobby(emf, "kirke") + 
                " antal af folk som går til specifik hobby(kirke)");
        
        // get a list of all zipcodes in denmark
        System.out.println(cs.getZipCodeList(emf).size() +
                " antal af zipcodes");
    }
}
