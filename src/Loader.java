public interface Loader {
    Integer load() throws SMCException;

    Instruction decode(int value) throws SMCException;
}
