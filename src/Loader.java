public interface Loader {
    /**
     * load next instruction
     *
     * @return value of instruction, if no instruction to load return `null`
     * @throws SMCException if I/O error occurs
     */
    Integer load() throws SMCException;
}
