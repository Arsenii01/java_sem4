package Practice6;

class Computer {
    public void putCPU() {
        System.out.println("Поставили процессор");
    }
    public void putRAM() {
        System.out.println("Поставили оперативку");
    }
    public void putGPU(String gpuName) {
        System.out.println("Поставили видеокарту: " + gpuName);
    }
}

interface GraphicsCard {
    String getGPUName();

}

class AMDGPU implements GraphicsCard {
    public String getGPUName() {
        return "RX6800";
    }
}

class GeforceGPU implements GraphicsCard {
    public String getGPUName() {
        return "RTX 4080";
    }
}

abstract class Builder {
    protected Computer computer;
    public abstract Computer buildComputer();
}

class RegularComputerBuilder extends Builder {
    public RegularComputerBuilder() {
        computer = new Computer();
    }
    public Computer buildComputer() {
        computer.putCPU();
        computer.putRAM();
        GraphicsCard gpu = new GeforceGPU();
        computer.putGPU(gpu.getGPUName());
        return computer;
    }
}

class StrangeComputerBuilder extends Builder {
    public StrangeComputerBuilder() {
        computer = new Computer();
    }
    public Computer buildComputer() {
        computer.putCPU();
        computer.putCPU();
        computer.putCPU();
        computer.putRAM();
        GraphicsCard gpu = new GeforceGPU();
        computer.putGPU(gpu.getGPUName());
        gpu = new AMDGPU();
        computer.putGPU(gpu.getGPUName());
        return computer;
    }
}

public class BuilderTest {
    public static void main(String[] args) {
        int choice = 12;
        if (choice == 1) {
            RegularComputerBuilder computer = new RegularComputerBuilder();
            computer.buildComputer();
        } else {
            StrangeComputerBuilder computerBuilder = new StrangeComputerBuilder();
            computerBuilder.buildComputer();
        }
    }
}
