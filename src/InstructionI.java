public class InstructionI extends Instruction {
    public InstructionI(InstructionName name, Short offset, RegisterIndex rt, RegisterIndex rs) {
        this.type = InstructionType.I;
        this.name = name;
        this.offset = offset;
        this.rt = rt;
        this.rs = rs;
    }

    public int offset() {
        return this.offset;
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
                (this.offset & 0xffff);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %d", this.name, this.rs, this.rt, this.offset);
    }
}
