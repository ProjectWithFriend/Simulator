public interface Simulator {
    void load() throws SMCException;

    void step() throws SMCException;
    void execute() throws SMCException;
    void executeWithin(Integer maximumSteps) throws SMCException;
    void printState();
}
