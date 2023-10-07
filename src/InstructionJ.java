public class InstructionJ extends Instruction {
    public InstructionJ(InstructionName name, RegisterIndex rt, RegisterIndex rs) {
        this.type = InstructionType.J;
        this.name = name;
        this.rt = rt;
        this.rs = rs;
    }

    public RegisterIndex rt() {
        return this.rt;
    }

    public RegisterIndex rs() {
        return this.rs;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", this.name, this.rs, this.rt);
    }
}
