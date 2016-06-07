/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skogemann.service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import skogemann.entity.Address;
import skogemann.entity.CityInfo;
import skogemann.entity.Hobby;
import skogemann.entity.Person;

/**
 *
 * @author thomas skogemann
 */
public class PersonServiceTest {
    
    public PersonServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of savePerson method, of class PersonService.
     */
    @Test
    public void testSavePerson() {
        System.out.println("savePerson");
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CA2PUTEST");
        AddressService as = new AddressService();
        CityInfoService cs = new CityInfoService();
        HobbyService hs = new HobbyService();
        PersonService ps = new PersonService(emf, as, cs, hs);
        Person klaus = new Person();

        klaus.setFirstName("KlausTest");
        klaus.setLastName("GroenbaekTest");
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
        
        // set mail + address
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
        Person testPerson = ps.getPersonsWithPhoneNumber("abe123").get(0);
        
        assertEquals("First Name", klaus.getFirstName(), testPerson.getFirstName());
        assertEquals("Last Name", klaus.getLastName(), testPerson.getLastName());
        assertEquals("Email", klaus.getEmail(), testPerson.getEmail());
        assertEquals("ID", klaus.getId(), testPerson.getId());
        assertEquals("Street name", klaus.getAddress().getStreet(), testPerson.getAddress().getStreet());
        assertEquals("additonal info", klaus.getAddress().getAdditionalInfo(), testPerson.getAddress().getAdditionalInfo());
        assertEquals("getPersonWithID", ps.getPersonWithId(1).getFirstName(), testPerson.getFirstName());
    }
}
