/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skogemann.web.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import skogemann.entity.Person;
import skogemann.service.PersonService;

/**
 *
 * @author thomas
 */
@Controller
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonService ps;

    @RequestMapping(method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Person> getPersons() {
        return ps.getPersons();
    }

    @RequestMapping(method = RequestMethod.POST, produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Person createPerson(@RequestBody Person person) {

        ps.savePerson(person);
        return person;
    }

    @RequestMapping (method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Person getPersonWithId(int id) {
        
        return ps.getPersonWithId(id);

    }
}
