public class EasterEgg {
    private final Register register;
    private int counter;

    public EasterEgg(Register register) {
        this.register = register;
        this.counter = 0;
    }

    private boolean criteria(Instruction instruction) {
        if (instruction instanceof InstructionR instructionR) {
            return instructionR.name == InstructionName.NAND &&
                    instructionR.rd == RegisterIndex.$0 &&
                    instructionR.rs == RegisterIndex.$0 &&
                    instructionR.rt == RegisterIndex.$0;
        }
        return false;
    }

    private static int combination(int n, int r) {
        if (n == r || r == 0)
            return 1;
        else
            return combination(n - 1, r) + combination(n - 1, r - 1);
    }

    private void execute() {
        int a = this.register.get(RegisterIndex.$2);
        int b = this.register.get(RegisterIndex.$3);
        switch (this.counter) {
            case 3 -> {
                int c = a * b;
                this.register.set(RegisterIndex.$4, c);
            }
            case 4 -> {
                int c = combination(a, b);
                this.register.set(RegisterIndex.$4, c);
            }
        }
        this.counter = 0;
    }

    public void append(Instruction instruction) {
        boolean isCheatCode = criteria(instruction);
        if (isCheatCode) counter++;
        else if (this.counter != 0) execute();
    }
}
