/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skogemann.service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author thomas
 */

@Configuration
public class ServiceConfig {
    @Bean
    public EntityManagerFactory createEmf(){
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("CA2PU");
    return emf;
    }
    
    @Bean
    public CityInfoService createCityInfoService(){
    return new CityInfoService();
    }
    
    @Bean
    public AddressService createAddressService(){
    return new AddressService();
    }
    
    @Bean
    public HobbyService createHobbyService(){
    return new HobbyService();
    }
    
    @Bean
    public PersonService createPersonService(EntityManagerFactory emf, AddressService addressService,
            CityInfoService cityInfoService, HobbyService hobbyService){
    return new PersonService(emf, addressService, cityInfoService, hobbyService);
    }
}
