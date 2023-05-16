package Practice1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        FunctionClass funcClass = new FunctionClass();
        List<Student> studentsArray = new ArrayList<>();
        // Имплементировать интерфейс Function, получающий на вход массив
        //студентов и возвращающий сгруппированных по группе студентов
        //(Map<String,List<Student>>).
        studentsArray.add(new Student("arsa", "ikbo-16"));
        studentsArray.add(new Student("kirill", "ikbo-20"));
        studentsArray.add(new Student("german", "ikbo-16"));
        Map<String, List<Student>> result = funcClass.apply(studentsArray);
        System.out.println(result);
    }
}
