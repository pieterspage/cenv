package page.pieters.cenv;

public class CenvException extends Exception {

    public CenvException(String message) {
        super(message);
    }

    public CenvException(String message, Throwable cause) {
        super(message, cause);
    }
}
