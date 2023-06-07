package com.example.controllerTests;

import com.example.controllers.PersonController;
import com.example.model.Person;
import com.example.services.IPersonService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class PersonControllerUnitTests {

    PersonController uut;
    IPersonService personService;

    Person testPerson = new Person();

    @BeforeEach
    public void setUp()  {
        personService = mock(IPersonService.class);
        uut = new PersonController();
        uut.setPersonService(personService);
    }

    @Test
    public void verifyServiceLayerWasCalled() {

        uut.addPerson(testPerson);
        verify(personService, times(1)).savePerson(testPerson);
    }

}
