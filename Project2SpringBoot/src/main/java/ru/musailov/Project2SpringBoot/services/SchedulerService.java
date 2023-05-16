package ru.musailov.Project2SpringBoot.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.musailov.Project2SpringBoot.models.Person;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@ManagedResource(objectName = "practice22:name=SchedulerService")
public class SchedulerService {
    private final BooksService booksService;
    private final PeopleService peopleService;
    private final String path = "C:\\Users\\Арсений\\Desktop\\PatternsJava4sem\\Project2SpringBoot\\info";

    @Autowired
    public SchedulerService(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }


    @ManagedOperation(description = "Creates DB files")
    @Scheduled(fixedDelay = 1800000)
    public void updateDirectory() throws IOException {
        File directory = new File(path);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
        File file = new File(path+"\\Person.txt");
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        List<Person> people = peopleService.findAll();
        for (Person person : people){
            fileWriter.write(person.toString());
            fileWriter.write("\n");
        }
        fileWriter.close();
    }
}