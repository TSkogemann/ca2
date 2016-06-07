/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skogemann.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import skogemann.entity.Address;
import skogemann.entity.CityInfo;
import skogemann.entity.Hobby;
import skogemann.entity.Person;

/**
 *
 * @author thomas
 */
public class PersonService {

    private final EntityManagerFactory emf;
    private final AddressService addressService;
    private final CityInfoService cityInfoService;
    private final HobbyService hobbyService;

    public PersonService(EntityManagerFactory emf, AddressService addressService,
            CityInfoService cityInfoService, HobbyService hobbyService) {
        this.emf = emf;
        this.addressService = addressService;
        this.cityInfoService = cityInfoService;
        this.hobbyService = hobbyService;
    }

    public void savePerson(Person person) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Address address = person.getAddress();
            if (address != null) {
                CityInfo cityInfo = address.getCityInfo();

                CityInfo foundCity = cityInfoService.findCity(em, cityInfo.getCity(), cityInfo.getZipCode());
                if (foundCity != null) {
                    address.setCityInfo(foundCity);
                    Address foundAddress = addressService.findAddress(em, address.getStreet(), address.getAdditionalInfo(), foundCity);
                    if (foundAddress != null) {
                        person.setAddress(foundAddress);
                    }
                }
            }
            if (person.getHobbies() != null) {
                List<Hobby> newList = new ArrayList<>();
                List<Hobby> existing = new ArrayList<>();
                for (Hobby hobby : person.getHobbies()) {
                    Hobby foundHobby = hobbyService.findHobby(em, hobby.getName());
                    if (foundHobby != null) {
                        existing.add(foundHobby);

                    } else {
                        newList.add(hobby);
                    }
                }
                for (Hobby found : existing) {
                    found.getPersons().add(person);
                }
                newList.addAll(existing);
                person.setHobbies(newList);
            }
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Person> getPersonsWithHobby(String hobbyName) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Person a inner join a.hobbies hobby "
                    + "WHERE hobby.name = :name", Person.class)
                    .setParameter("name", hobbyName)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    public List<Person> getPersonsWithPhoneNumber(String number) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Person a inner join a.phones phone "
                    + "WHERE phone.number = :number", Person.class)
                    .setParameter("number", number)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Person> getPersonsWithZipCode(int zipCode) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Person p "
                    + "WHERE p.address.cityInfo.zipCode = :code", Person.class)
                    .setParameter("code", zipCode)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Person> getPersonsWithCityName(String cityName) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Person p "
                    + "WHERE p.address.cityInfo.city = :name", Person.class)
                    .setParameter("name", cityName)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Person> getPersons() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Person p", Person.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Person getPersonWithId(int id){
        EntityManager em = emf.createEntityManager();
        List<Person> result;
        try {
            result = em.createQuery("SELECT p FROM Person p "
                    + "WHERE p.id = :id", Person.class)
                    .setParameter("id", id)
                    .getResultList();
        } finally {
            em.close();
        }
        return result.get(0);
    }

}
