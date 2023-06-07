package com.example.controllers;


import com.example.model.Person;
import com.example.services.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class PersonController {

    IPersonService personService;

    @PostMapping("/addperson")
    public Person addPerson(@RequestBody Person person) {
        return personService.savePerson(person);
    }



    public IPersonService getPersonService() {
        return personService;
    }

    @Autowired
    public void setPersonService(IPersonService personService) {
        this.personService = personService;
    }
}
