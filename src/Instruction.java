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

    public abstract String toString();
}
