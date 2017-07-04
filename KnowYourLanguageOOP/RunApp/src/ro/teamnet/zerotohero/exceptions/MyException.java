package ro.teamnet.zerotohero.exceptions;

/**
 * Created by Gianina.Carp on 7/4/2017.
 */
public class MyException extends RuntimeException {
    public MyException() { super(); }
    public MyException(String message) { super(message); }
    public MyException(String message, Throwable cause) { super(message, cause); }
    public MyException(Throwable cause) { super(cause); }
}
