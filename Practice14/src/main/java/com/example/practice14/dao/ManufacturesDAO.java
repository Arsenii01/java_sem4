package com.example.practice14.dao;

import com.example.practice14.entities.Manufacture;
import com.example.practice14.entities.Worker;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ManufacturesDAO {
    private List<Manufacture> manufactures = new ArrayList<>();

    public void add(Manufacture manufacture) {
        manufactures.add(manufacture);
    }

    public void delete(Worker workerToDelete) {
        for (Manufacture worker : manufactures) {
            if (worker.equals(workerToDelete)) {
                manufactures.remove(worker);
            }
        }
    }

    public List<Manufacture> getAll() {
        return manufactures;
    }
}
