package Practice7;


import java.util.ArrayList;
import java.util.List;

interface Component {
    void execute();
}

class Leaf implements Component{
    public void execute() {
        System.out.println("Лист");
    }
}

public class Composite implements Component {
    private List<Component> children;

    public Composite() {
        this.children = new ArrayList<>();
    }

    public Composite(List<Component> components) {
        this.children = components;
    }
    public void add(Component item) {
        children.add(item);
    }

    public void remove(Component item) {
        children.remove(item);
    }
    public List<Component> getChildren() {
        return children;
    }

    public void execute() {
        System.out.println("Коробка");
        for (Component child : children) {
            child.execute();
        }
    }

    public static void main(String[] args) {
        Component c1 = new Leaf();
        Component c2 = new Leaf();
        Composite composite = new Composite();
        composite.add(c1);
        composite.add(c2);
        composite.add(new Leaf());
        Composite comp2 = new Composite();
        comp2.add(c1);
        comp2.add(composite);
        comp2.execute();

    }
}
