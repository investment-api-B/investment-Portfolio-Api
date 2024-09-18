package org.bartoszwojcik.investmentportfolioapi.exception;

public class StockNotFountException extends RuntimeException {
    public StockNotFountException(Throwable cause) {
        super(cause);
    }

    public StockNotFountException(String message, Throwable cause) {
        super(message, cause);
    }

    public StockNotFountException(String message) {
        super(message);
    }
}
