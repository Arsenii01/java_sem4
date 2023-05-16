package Practice1;

public class Student {
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", group='" + group + '\'' +
                '}';
    }

    public Student(String name, String group) {
        this.name = name;
        this.group = group;
    }

    private String name;
    private String group;

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }
}
