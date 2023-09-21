import java.util.ArrayList;

public class SimpleMemory implements Memory {
    private final ArrayList<Integer> memory;

    public SimpleMemory() {
        this.memory = new ArrayList<>(this.size());
        for (int i = 0; i < this.size(); i++)
            this.memory.add(0);
    }

    @Override
    public Integer get(Integer address) {
        return this.memory.get(address);
    }


    @Override
    public Integer set(Integer address, Integer value) {
        return this.memory.set(address, value);
    }

    @Override
    public Integer size() {
        return 65536;
    }
}