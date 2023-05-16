package Practice2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        List<Human> humanList = new ArrayList<>();
        Human h1 = new Human(2, "Melkiy", "Parenb", LocalDate.of(2022, 4, 12), 9);
        Human h2 = new Human(19, "Arsen", "Musailov", LocalDate.of(2004, 1, 21), 57);
        Human h3 = new Human(51, "Sergey", "Musailov", LocalDate.of(1972, 10, 1), 90);
        Human h4 = new Human(83, "Valeria", "Budagova", LocalDate.of(1939, 12, 25), 65);
        humanList.add(h4);
        humanList.add(h2);
        humanList.add(h1);
        humanList.add(h3);

        //Сортировка по дате рождения, фильтрация по возрасту меньше, чем
        //50, сортировка по весу, конкатенация всех имен в одну большую строку через
        //пробел.
        System.out.println(humanList);
        Comparator<Human> birthCompare = (human1, human2) -> human1.birthDate.compareTo(human2.birthDate);
        Comparator<Human> weightCompare = (human1, human2) -> (human1.weight > human2.weight ? 1 : (human1.weight < human2.weight ? -1 : 0));

        StringBuffer result = new StringBuffer();
        humanList.stream().sorted(birthCompare).filter((human) -> human.age < 50).sorted(weightCompare).forEach((human) -> result.append(human.firstName + " "));
        System.out.println(result.toString());


    }
}
