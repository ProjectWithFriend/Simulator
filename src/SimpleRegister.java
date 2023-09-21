import java.util.ArrayList;

public class SimpleRegister implements Register {
    private final ArrayList<Integer> registers;

    public SimpleRegister() {
        this.registers = new ArrayList<>(this.size());
        for (int i = 0; i < this.size(); i++)
            this.registers.add(0);
    }

    @Override
    public Integer get(RegisterIndex index) {
        int idx = index.ordinal();
        if (idx == 0)
            return 0;
        else
            return this.registers.get(idx);
    }

    @Override
    public Integer set(RegisterIndex index, Integer value) {
        int idx = index.ordinal();
        if (idx == 0)
            return 0;
        else
            return this.registers.set(idx, value);
    }

    @Override
    public Integer size() {
        return 8;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < 8; i++) {
            RegisterIndex index = RegisterIndex.values()[i];
            builder.append("Register [$")
                    .append(i)
                    .append("]: ")
                    .append(this.get(index))
                    .append("\n");
        }
        return builder.toString();
    }
}
