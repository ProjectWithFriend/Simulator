import java.io.*;

public class BinaryStringLoader extends SimpleLoader {
    private final BufferedReader reader;

    public BinaryStringLoader(String filename) throws FileNotFoundException {
        File file = new File(filename);
        this.reader = new BufferedReader(new FileReader(file));
    }

    @Override
    public Integer load() throws SMCException {
        String line;
        try {
            line = this.reader.readLine();
        } catch (IOException e) {
            throw new SMCException.NotImplemented();
        }
        if (line == null || line.length() == 0)
            return null;
        return toInt(line);
    }

    private static Integer toInt(String binaryString) {
        if (binaryString == null || binaryString.length() != 32)
            return null;
        return (int) Long.parseLong(binaryString, 2);
    }
}
