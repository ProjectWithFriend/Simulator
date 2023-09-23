public class DecimalStringLoader extends BinaryStringLoader {
    public DecimalStringLoader(String filename) throws SMCException {
        super(filename);
    }

    @Override
    protected Integer toInt(String decimalString) {
        return (int) Long.parseLong(decimalString, 10);
    }
}
