public interface Memory {
    /**
     * get word of given address
     * @param address address of word, where 0 <= address < size
     * @return `value` of given address
     */
    Integer get(Integer address);

    /**
     * set word of given address
     * @param address address of word, where 0 <= address < size
     * @param value `value` for given address
     * @return old `value` of given address
     */
    Integer set(Integer address, Integer value);

    /**
     * @return size of memory
     */
    Integer size();

    String toString(Integer offset, Integer size);
}
