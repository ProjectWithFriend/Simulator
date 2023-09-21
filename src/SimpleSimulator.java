public class SimpleSimulator implements Simulator {
    private final Loader loader;
    private final Register register;
    private final Memory memory;
    private int pc;
    private boolean halt;

    public SimpleSimulator(Loader loader, Register register, Memory memory) {
        this.loader = loader;
        this.register = register;
        this.memory = memory;
        this.halt = false;
        this.pc = 0;
    }

    @Override
    public void load() throws SMCException {
        int address = 0;
        do {
            Integer value = this.loader.load();
            if (value == null)
                return;
            this.memory.set(address, value);
            address += 1;
        } while (true);
    }

    @Override
    public void step() throws SMCException {
        if (this.pc >= this.memory.size())
            return;

        int value = this.memory.get(this.pc);
        Instruction instruction = loader.decode(value);
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
            throw new SMCException.NotImplemented();
    }

    @Override
    public void execute() throws SMCException {
        do {
            this.step();
            this.printState();
        } while (!this.halt);
    }

    @Override
    public void printState() {
        System.out.printf("Halted: %b\n", this.halt);
        System.out.print(this.register);
        System.out.println();
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
            default -> throw new SMCException.NotImplemented();
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
            default -> throw new SMCException.NotImplemented();
        }
    }

    private void executeJ(InstructionJ instruction) throws SMCException {
        if (instruction.name() != InstructionName.JALR)
            throw new SMCException.NotImplemented();
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
            default -> throw new SMCException.NotImplemented();
        }
    }
}
