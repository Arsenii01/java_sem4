package Practice1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class FunctionClass implements Function<List<Student>, Map<String, List<Student>>> {
    @Override
    public Map<String, List<Student>> apply(List<Student> studentList) {
        Map<String, List<Student>> resultMap = new HashMap<>();
        for (Student student : studentList) {
            String studentGroup = student.getGroup();
            if (!resultMap.containsKey(studentGroup)) {
                ArrayList<Student> arr = new ArrayList<>();
                arr.add(student);
                resultMap.put(studentGroup, arr);
            } else {
                resultMap.get(studentGroup).add(student);
            }
        }
        return resultMap;
    }
}
