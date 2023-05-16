package Practice6;

interface CPU {
    public int getPrice();
}
interface GPU {
    public int getPrice();
}

class usCPU implements CPU{
    public int getPrice() {
        return 300;
    }
}
class usGPU implements GPU {
    public int getPrice() {
        return 1000;
    }
}

class ruCPU implements CPU{
    public int getPrice() {
        return 10000;
    }
}

class ruGPU implements GPU{
    public int getPrice() {
        return 30000;
    }
}

interface TechFactory {
    CPU getCPU();
    GPU getGPU();
}

class usTechFactory implements TechFactory{
    public CPU getCPU() {
        return new usCPU();
    }
    public GPU getGPU() {
        return new usGPU();
    }
}

class ruTechFactory implements TechFactory{
    public CPU getCPU() {
        return new ruCPU();
    }
    public GPU getGPU() {
        return new ruGPU();
    }
}
public class AbstractFactory {
    public static void main(String[] args) {
        String country = "RU";
        TechFactory factory;
        if (country.equals("US")) {
            factory = new usTechFactory();
        } else {
            factory = new ruTechFactory();
        }
        CPU cpu = factory.getCPU();
        GPU gpu = factory.getGPU();
        System.out.println(cpu.getPrice());
    }
}
