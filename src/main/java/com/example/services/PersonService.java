package com.example.services;

import com.example.dataAccess.IPersonRepository;
import com.example.model.Person;
import com.example.dataAccess.IPersonRepository;
import com.example.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements IPersonService{

    IPersonRepository personRepository;


    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public  Person findPersonByName(String name) {
        return personRepository.findPersonByName(name);
    }

    public IPersonRepository getPersonRepository() {
        return personRepository;
    }

    @Autowired
    public void setPersonRepository(IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

}
