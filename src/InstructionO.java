public class InstructionO extends Instruction {
    public InstructionO(InstructionName name) {
        this.type = InstructionType.O;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name.toString();
    }
}
