package ru.musailov.Project2SpringBoot.services;

import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.musailov.Project2SpringBoot.models.Book;
import ru.musailov.Project2SpringBoot.models.Person;
import ru.musailov.Project2SpringBoot.repositories.PeopleRepository;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Neil Alishev
 */
@Service
@Slf4j
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;
    private final EmailService emailService;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, EmailService emailService) {
        this.peopleRepository = peopleRepository;
        this.emailService = emailService;
    }

    public List<Person> findAll() {
        log.info("Find all users");
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        log.info("Find user by id");
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        log.info("Save new user");
        peopleRepository.save(person);
        try {
            emailService.sendEmail( "Order saved", "Object: " + person);
        } catch (MessagingException e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        log.info("Update user");
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        log.info("Delete user");
        peopleRepository.deleteById(id);
    }

    public Optional<Person> getPersonByFullName(String fullName) {
        return peopleRepository.findByFullName(fullName);
    }

    public List<Book> getBooksByPersonId(int id) {
        Optional<Person> person = peopleRepository.findById(id);

        if (person.isPresent()) {
            Hibernate.initialize(person.get().getBooks());
            // Мы внизу итерируемся по книгам, поэтому они точно будут загружены, но на всякий случай
            // не мешает всегда вызывать Hibernate.initialize()
            // (на случай, например, если код в дальнейшем поменяется и итерация по книгам удалится)

            // Проверка просроченности книг
            person.get().getBooks().forEach(book -> {
                long diffInMillies = Math.abs(book.getTakenAt().getTime() - new Date().getTime());
                // 864000000 милисекунд = 10 суток
                if (diffInMillies > 864000000)
                    book.setExpired(true); // книга просрочена
            });

            return person.get().getBooks();
        }
        else {
            return Collections.emptyList();
        }
    }
}
