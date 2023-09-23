public abstract class SMCException extends Exception {
    public static final class InvalidRInstruction extends SMCException {
    }

    public static final class InvalidIInstruction extends SMCException {
    }

    public static final class InvalidJInstruction extends SMCException {
    }

    public static final class InvalidOInstruction extends SMCException {
    }

    public static final class InstructionTypeNotFound extends SMCException {
    }

    public static final class IoException extends SMCException {
    }

    public static final class FileNotFound extends SMCException {
    }

    public static final class NotHaltedWithinLimit extends SMCException {}
    public static final class AlreadyHalted extends SMCException {
    }

    private static String splitCamelCase(String s) {
        return s.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                " "
        );
    }

    public String toString() {
        String className = this.getClass().getSimpleName();
        return splitCamelCase(className);
    }
}
