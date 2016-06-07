/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skogemann.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.persistence.EntityManagerFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author thomas
 */
public class ServiceConfigTest {
    
    public ServiceConfigTest() {
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
     * Test of createEmf method, of class ServiceConfig.
     */
    @Test
    public void testCreateEmf() throws JsonProcessingException {
        AnnotationConfigApplicationContext cxt = new AnnotationConfigApplicationContext();
        cxt.register(ServiceConfig.class);
        cxt.refresh();
        PersonService bean = cxt.getBean(PersonService.class);
        assertNotNull(bean.getPersons());
        
        ObjectMapper mapper = new ObjectMapper();
        String writeValueAsString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(bean.getPersons());
                System.out.println(writeValueAsString);
    }

    
}
