package Practice8;


import java.sql.SQLOutput;

class Lever {
    public void turnOn() {
        System.out.println("Рычаг смотрит вниз");
    }
    public void turnOff() {
        System.out.println("Рычаг смотрит вверх");
    }
}

interface Command {
    void execute();
    void undo();
}

class TurnOn implements Command {
    protected Lever lever;
    public TurnOn(Lever lvr) {
        lever = lvr;

    }

    @Override
    public void execute() {
        lever.turnOn();
    }
    public void undo() {
        lever.turnOff();
    }
}

class TurnOff implements Command {
    protected Lever lever;
    public TurnOff(Lever lvr) {
        lever = lvr;

    }

    @Override
    public void execute() {
        lever.turnOff();
    }
    public void undo() {
        lever.turnOn();
    }
}

class RemoteControl {
    public static void submit(Command command) {
        command.execute();
    }
}

public class App {
    public static void main(String[] args) {
        Lever lever = new Lever();
        Command turnOn = new TurnOn(lever);
        Command turnOff = new TurnOff(lever);
        RemoteControl.submit(turnOn);
    }
}
