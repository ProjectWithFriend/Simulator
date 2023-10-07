public interface Loader {
    /**
     * load next instruction
     *
     * @return value of instruction, if no instruction to load return `null`
     * @throws SMCException if there are fails to load
     */
    Integer load() throws SMCException;
}
