import java.io.*;

public class DecimalStringLoader implements Loader {
    private final BufferedReader reader;

    public DecimalStringLoader(String filename) throws SMCException {
        File file = new File(filename);
        try {
            this.reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new SMCException.FileNotFound();
        }
    }

    @Override
    public Integer load() throws SMCException {
        String line;
        try {
            line = this.reader.readLine();
        } catch (IOException e) {
            throw new SMCException.IoException();
        }
        if (line == null || line.length() == 0)
            return null;
        return toInt(line);
    }

    protected Integer toInt(String decimalString) {
        return (int) Long.parseLong(decimalString, 10);
    }
}
