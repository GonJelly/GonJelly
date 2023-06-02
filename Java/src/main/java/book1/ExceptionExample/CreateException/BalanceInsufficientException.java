package book1.ExceptionExample.CreateException;

public class BalanceInsufficientException extends Exception{

    public BalanceInsufficientException() {

    }

    public BalanceInsufficientException(String message) {
        super(message);
    }
}
