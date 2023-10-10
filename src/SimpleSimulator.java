public class SimpleSimulator implements Simulator {
    private final Loader loader;
    private final Register register;
    private final Memory memory;
    private int pc;
    private boolean halt;
    private int loaded;
    private int executed;

    public SimpleSimulator(Loader loader, Register register, Memory memory) {
        this.loader = loader;
        this.register = register;
        this.memory = memory;
        this.halt = false;
        this.pc = 0;
        this.executed = 0;
    }

    @Override
    public void load() throws SMCException {
        this.loaded = 0;
        do {
            Integer value = this.loader.load();
            if (value == null)
                break;
            this.memory.set(this.loaded, value);
            System.out.printf("memory[%d]=%d\n", this.loaded, value);
            this.loaded += 1;
        } while (true);
        System.out.println();
    }

    @Override
    public void step() throws SMCException {
        if (this.halt)
            throw new SMCException.AlreadyHalted();
        if (this.pc >= this.memory.size())
            return;

        int value = this.memory.get(this.pc);
        Instruction instruction = Decoder.decode(value);
        this.pc += 1;

        if (instruction instanceof InstructionR)
            this.executeR((InstructionR) instruction);
        else if (instruction instanceof InstructionI)
            this.executeI((InstructionI) instruction);
        else if (instruction instanceof InstructionJ)
            this.executeJ((InstructionJ) instruction);
        else if (instruction instanceof InstructionO)
            this.executeO((InstructionO) instruction);
        else
            throw new SMCException.InstructionTypeNotFound();
    }

    @Override
    public void execute() throws SMCException {
        this.executed = 0;
        while (!this.halt) {
            this.printState();
            this.step();
            this.executed++;
        }
        this.printState();
    }

    @Override
    public void printState() {
        if (this.halt) {
            System.out.println("machine halted");
            System.out.printf("total of %d instructions executed\n", this.executed);
            System.out.println("final state of machine:");
        }
        System.out.println();
        System.out.println("@@@");
        System.out.println("state:");
        System.out.printf("\tpc %d\n", this.pc);
        System.out.print(this.memory.toString(0, loaded));
        System.out.print(this.register);
        System.out.println("end state");
    }

    private void executeR(InstructionR instruction) throws SMCException {
        switch (instruction.name()) {
            case ADD -> {
                Integer regA = this.register.get(instruction.rs());
                Integer regB = this.register.get(instruction.rt());
                Integer result = regA + regB;
                this.register.set(instruction.rd(), result);
            }
            case NAND -> {
                Integer regA = this.register.get(instruction.rs());
                Integer regB = this.register.get(instruction.rt());
                Integer result = ~(regA & regB);
                this.register.set(instruction.rd(), result);
            }
            default -> throw new SMCException.InvalidRInstruction();
        }
    }

    private void executeI(InstructionI instruction) throws SMCException {
        switch (instruction.name()) {
            case LW -> {
                Integer regA = this.register.get(instruction.rs());
                Integer offset = instruction.offset();
                Integer address = regA + offset;
                this.register.set(instruction.rt(), this.memory.get(address));
            }
            case SW -> {
                Integer regA = this.register.get(instruction.rs());
                Integer regB = this.register.get(instruction.rt());
                Integer offset = instruction.offset();
                Integer address = regA + offset;
                this.memory.set(address, regB);
            }
            case BEQ -> {
                Integer regA = this.register.get(instruction.rs());
                Integer regB = this.register.get(instruction.rt());
                if (!regA.equals(regB))
                    return;

                int offset = instruction.offset();
                this.pc = this.pc + offset;
            }
            default -> throw new SMCException.InvalidIInstruction();
        }
    }

    private void executeJ(InstructionJ instruction) throws SMCException {
        if (instruction.name() != InstructionName.JALR)
            throw new SMCException.InvalidJInstruction();
        Integer regA = this.register.get(instruction.rs());
        this.register.set(instruction.rt(), this.pc);
        if (instruction.rt() != instruction.rs())
            this.pc = regA;
    }

    private void executeO(InstructionO instruction) throws SMCException {
        switch (instruction.name()) {
            case HALT -> this.halt = true;
            case NOOP -> {
            }
            default -> throw new SMCException.InvalidOInstruction();
        }
    }
}
