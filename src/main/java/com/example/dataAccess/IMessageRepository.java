package com.example.dataAccess;

import com.example.model.Message;
import com.example.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMessageRepository  extends CrudRepository<Message, Long> {
    Message getMessageById(Long id);

    Iterable<Message> findMessagesBySender(Person sender);

}
