public interface Simulator {
    /**
     * load program into memory
     * @throws SMCException if there are fails to load
     */
    void load() throws SMCException;

    void step() throws SMCException;
    void execute() throws SMCException;
    void printState();
}
