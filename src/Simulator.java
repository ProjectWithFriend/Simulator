public interface Simulator {
    void load() throws SMCException;

    void step() throws SMCException;
    void execute() throws SMCException;

    void printState();
}
