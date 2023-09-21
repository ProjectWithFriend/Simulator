public interface Memory {
    Integer get(Integer address);

    Integer set(Integer address, Integer value);

    Integer size();
}
