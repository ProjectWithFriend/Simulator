public interface Simulator {
    /**
     * load program into memory
     * @throws SMCException if Loader load fails
     */
    void load() throws SMCException;

    /**
     * execute and step a instruction by one
     * @throws SMCException if Simulator alreay halted, or got invalid instruction at PC
     */
    void step() throws SMCException;

    /**
     * execute all instruction until halted
     * @throws SMCException if some step fails
     */
    void execute() throws SMCException;

    /**
     * show current status of pc, memory and registers
     */
    void printState();
}
