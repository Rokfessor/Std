package Exc;

public class WeigherException extends Exception {
    public enum WeigherError {
        EMPTY_INPUT("Error: input area is empty"),
        INAPPROPRIATE_SYMBOLS("Error: input area contains inappropriate symbols"),
        NULL_DATE("All weighers are 0"),
        INCORRECT_NUMBER("The number is incorrect");
        private String message;

        WeigherError(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    WeigherException() {
        super("Unknown issue");
    }

    WeigherException(String message) {
        super(message);
    }

    public WeigherException(WeigherError error) {
        super(error.getMessage());
    }

    public String getMessage() {
        return super.getMessage();
    }
}