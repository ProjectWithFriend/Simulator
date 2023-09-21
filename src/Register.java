public interface Register {
    Integer get(RegisterIndex index);

    Integer set(RegisterIndex index, Integer value);
    Integer size();
}
