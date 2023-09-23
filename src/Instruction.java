public abstract class Instruction {
    protected InstructionType type;
    protected InstructionName name;
    protected RegisterIndex rd;
    protected RegisterIndex rs;
    protected RegisterIndex rt;
    protected Short offset;

    public InstructionName name() {
        return this.name;
    }

    public InstructionType type() {
        return this.type;
    }

    public abstract Integer toInteger();

    public abstract String toString();
}
