public class InstructionO extends Instruction {
    public InstructionO(InstructionName name) {
        this.type = InstructionType.O;
        this.name = name;
    }

    @Override
    public Integer toInteger() {
        return (this.name.ordinal() & 0b111) << 22;
    }
}
