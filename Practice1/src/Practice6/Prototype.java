package Practice6;


abstract class Shape {
    public abstract Shape clone();
}

class Circle extends Shape {
    public int radius;

    public Circle(int number) {
        radius = number;
    }

    public Shape clone() {
        return new Circle(radius);
    }

}

class Rectangle extends Shape {
    public int width;
    public int height;

    public Rectangle(int w, int h) {
        width = w;
        height = h;
    }

    public Shape clone() {
        return new Rectangle(width, height);
    }
}

public class Prototype {
    public static void main(String[] args) {
        Shape circle1 = new Circle(23);

        Shape circle2 = circle1.clone();
        System.out.println(circle1);
        System.out.println(circle2);
    }

}
