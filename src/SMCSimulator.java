public class SMCSimulator {
    public static void main(String[] args) throws SMCException {
        if (args.length != 1) { // check if program file presence
            System.out.println("please input program");
            return;
        }
        String programFile = args[0];

        // initialize an instances that required by a simulator
        Loader loader = new DecimalStringLoader(programFile);
        Register register = new SimpleRegister();
        Memory memory = new SimpleMemory();
        // we're using dependency injection for abstraction
        Simulator simulator = new SimpleSimulator(loader, register, memory);

        simulator.load(); // first, load a program into memory
        simulator.execute(); // then, execute it
    }
}