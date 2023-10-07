public class InstructionR extends Instruction {
    public InstructionR(InstructionName name, RegisterIndex rd, RegisterIndex rt, RegisterIndex rs) {
        this.type = InstructionType.R;
        this.name = name;
        this.rd = rd;
        this.rt = rt;
        this.rs = rs;
    }

    public RegisterIndex rd() {
        return this.rd;
    }

    public RegisterIndex rt() {
        return this.rt;
    }

    public RegisterIndex rs() {
        return this.rs;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s", this.name,this.rs, this.rt, this.rd);
    }
}
