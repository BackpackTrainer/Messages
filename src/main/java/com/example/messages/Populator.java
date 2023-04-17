package com.example.messages;

import com.example.messages.dataAccess.IMessageRepository;
import com.example.messages.dataAccess.IPersonRepository;
import com.example.messages.model.Message;
import com.example.messages.model.Person;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Populator  implements CommandLineRunner {

    private IMessageRepository messageRepo;
    private IPersonRepository personRepository;

    @Override
    public void run(String... args) throws Exception {
        Person sender1 = new Person("Will");
        personRepository.save(sender1);
        Message helloWorld = new Message("Hello World", sender1);
        messageRepo.save(helloWorld);

        Message fromWill = new Message("GLHF!", sender1);
        messageRepo.save(fromWill);

        Person kamila = new Person("Kamila");
        personRepository.save(kamila);
        Message fromKamila = new Message("Kamila says hi", kamila);
        messageRepo.save(fromKamila);

        System.out.println(sender1.getName() + " has sent " + sender1.getMySentMessages().size() + " messages");
    }

    public Populator(IMessageRepository messageRepo, IPersonRepository personRepository) {

        this.messageRepo = messageRepo;
        this.personRepository = personRepository;
    }
}

