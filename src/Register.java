public interface Register {
    /**
     * get word of given register
     * @param index index of register
     * @return `value` of given register
     */
    Integer get(RegisterIndex index);

    /**
     * set word to given register
     * @param index index of register
     * @param value `value` for given register
     * @return old `value` of given register
     */
    Integer set(RegisterIndex index, Integer value);

    /**
     * @return size of registers
     */
    Integer size();
}
