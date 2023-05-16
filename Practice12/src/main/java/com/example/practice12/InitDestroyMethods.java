package com.example.practice12;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Scanner;

@Component
public class InitDestroyMethods {
    @Value("input.txt")
    private String inputFileName;
    @Value("output.txt")
    private String outputFileName;
    @PostConstruct
    public void hashData() {
        System.out.println("Init method сработал!");
        try {
            File currentDir = new File(System.getProperty("user.dir"));
            String path = "C:\\Users\\Арсений\\Desktop\\PatternsJava4sem\\Practice12\\src\\main\\java\\com\\example\\practice12\\";
            File inputFile1 = new File(path + inputFileName);
            FileWriter outputFile = new FileWriter(path + outputFileName);
            if (!inputFile1.exists()) {
                System.out.println("Исходного файла не было");
                outputFile.write("null");
                outputFile.close();
            } else {
                FileReader inputFile = new FileReader(path + inputFileName);

                Scanner scan = new Scanner(inputFile);
                System.out.println("Считываем данные из файла" + inputFileName);
                String str = scan.nextLine();
                int hashCode = str.hashCode();
                outputFile.write(Integer.toString(hashCode));
                scan.close();
                outputFile.close();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PreDestroy
    public void deleteFile() {
        String path = "C:\\Users\\Арсений\\Desktop\\PatternsJava4sem\\Practice12\\src\\main\\java\\com\\example\\practice12\\";
        File inputFile = new File(path + inputFileName);
        if (inputFile.delete()) {
            System.out.println("Исходный файл удалён");
        } else {
            System.out.println("Исходного файл не удалён");
        }
        System.out.println("DestroyMethod сработал");
    }

    public InitDestroyMethods() {
    }
}

