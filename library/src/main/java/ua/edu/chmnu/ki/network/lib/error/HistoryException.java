package ua.edu.chmnu.ki.network.lib.error;

public class HistoryException extends RuntimeException {

    public HistoryException() {
    }

    public HistoryException(String message) {
        super(message);
    }

    public HistoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public HistoryException(Throwable cause) {
        super(cause);
    }

    public HistoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}