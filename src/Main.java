import java.io.IOException;

public class Main {
    public static void main(String[] args) throws SMCException {
        if (args.length != 1) {
            System.out.println("please input program");
            return;
        }
        String programFile = args[0];

        Loader loader = new DecimalStringLoader(programFile);
        Register register = new SimpleRegister();
        Memory memory = new SimpleMemory();
        Simulator simulator = new SimpleSimulator(loader, register, memory);

        simulator.load();
        simulator.execute();
    }
}