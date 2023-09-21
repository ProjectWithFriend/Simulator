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
    public Integer toInteger() {
        return (this.name.ordinal() & 0b111) << 22 |
                (this.rs.ordinal() & 0b111) << 19 |
                (this.rt.ordinal() & 0b111) << 16 |
                (this.rd.ordinal() & 0b111);
    }
}
