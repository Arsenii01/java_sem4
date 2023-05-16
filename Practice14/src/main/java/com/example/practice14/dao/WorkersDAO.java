package com.example.practice14.dao;

import com.example.practice14.entities.Worker;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Component
public class WorkersDAO {
    private List<Worker> workerList = new ArrayList<>();

    public WorkersDAO() {

        workerList.add(new Worker("musya", "ARS", "SERG"));
        workerList.add(new Worker("shap", "kir", "alexeevich"));
    }

    public void add(Worker worker) {
        workerList.add(worker);
    }

    public void delete(Worker workerToDelete) {
        for (Worker worker : workerList) {
            if (worker.equals(workerToDelete)) {
                workerList.remove(worker);
            }
        }
    }

    public List<Worker> getAll() {
        return workerList;
    }
}
