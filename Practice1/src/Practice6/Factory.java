package Practice6;

public class Factory {
    public static Car createCar(String brand) {
        if (brand.equals("Ford")) {
            return new Ford();
        } else if (brand.equals("Renault")) {
            return new Renault();
        } else {
            return new UnknownCar();
        }
    }

    public static void main(String[] args) {
        Car myCar = createCar("Renault");
        myCar.getInfo();
    }
}
interface Car {
    public void getInfo();
}
class Ford implements Car {

    @Override
    public void getInfo() {
        System.out.println("Форд");
    }
}
class UnknownCar implements Car {
    @Override
    public void getInfo() {
        System.out.println("Неизвестная марка машины");
    }
}
class Renault implements Car {
    public void getInfo() {
        System.out.println("Реново логан черного цвета 21 века");
    }
}


