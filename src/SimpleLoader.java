public abstract class SimpleLoader implements Loader {
    protected static int getOpcode(int value) {
        return (value >> 22) & 0b111;
    }

    protected static RegisterIndex getRd(int value) {
        int idx = value & 0b111;
        return RegisterIndex.values()[idx];
    }

    protected static RegisterIndex getRt(int value) {
        int idx = (value >> 16) & 0b111;
        return RegisterIndex.values()[idx];
    }

    protected static RegisterIndex getRs(int value) {
        int idx = (value >> 19) & 0b111;
        return RegisterIndex.values()[idx];
    }

    protected static InstructionType parseType(int value) {
        int opcode = getOpcode(value);
        return switch (opcode) {
            case 0, 1 -> InstructionType.R;
            case 2, 3, 4 -> InstructionType.I;
            case 5 -> InstructionType.J;
            case 6, 7 -> InstructionType.O;
            default -> InstructionType.Undefined;
        };
    }

    protected static InstructionName parseName(int value) {
        int opcode = getOpcode(value);
        return switch (opcode) {
            case 0 -> InstructionName.ADD;
            case 1 -> InstructionName.NAND;
            case 2 -> InstructionName.LW;
            case 3 -> InstructionName.SW;
            case 4 -> InstructionName.BEQ;
            case 5 -> InstructionName.JALR;
            case 6 -> InstructionName.HALT;
            case 7 -> InstructionName.NOOP;
            default -> InstructionName.Undefined;
        };
    }

    protected static Short getOffset(int value) {
        return (short) (value & 0xffff);
    }

    @Override
    public Instruction decode(int value) throws SMCException {
        return switch (parseType(value)) {
            case R -> parseR(value);
            case I -> parseI(value);
            case J -> parseJ(value);
            case O -> parseO(value);
            case Undefined -> throw new SMCException.NotImplemented();
        };
    }

    private static Instruction parseR(int value) throws SMCException {
        InstructionName name = parseName(value);
        RegisterIndex rd = getRd(value);
        RegisterIndex rs = getRs(value);
        RegisterIndex rt = getRt(value);
        return switch (name) {
            case ADD, NAND -> new InstructionR(name, rd, rs, rt);
            default -> throw new SMCException.NotImplemented();
        };
    }

    private static Instruction parseI(int value) throws SMCException {
        InstructionName name = parseName(value);
        Short offset = getOffset(value);
        RegisterIndex rs = getRs(value);
        RegisterIndex rt = getRt(value);
        return switch (name) {
            case LW, SW, BEQ -> new InstructionI(name, offset, rt, rs);
            default -> throw new SMCException.NotImplemented();
        };
    }

    private static Instruction parseJ(int value) throws SMCException {
        InstructionName name = parseName(value);
        RegisterIndex rs = getRs(value);
        RegisterIndex rt = getRt(value);
        if (name != InstructionName.JALR)
            throw new SMCException.NotImplemented();
        return new InstructionJ(name, rt, rs);
    }

    private static Instruction parseO(int value) throws SMCException {
        InstructionName name = parseName(value);
        return switch (name) {
            case HALT, NOOP -> new InstructionO(name);
            default -> throw new SMCException.NotImplemented();
        };

    }
}
