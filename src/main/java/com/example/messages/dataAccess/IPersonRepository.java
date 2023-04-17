package com.example.messages.dataAccess;

import com.example.messages.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonRepository extends CrudRepository <Person, Long> {

    Person findPersonByName(String name);
}
