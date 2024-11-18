package ua.edu.chmnu.ki.network.lib.error;

public class BookedException extends RuntimeException {
    public BookedException() {
    }

    public BookedException(String message) {
        super(message);
    }

    public BookedException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookedException(Throwable cause) {
        super(cause);
    }

    public BookedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
