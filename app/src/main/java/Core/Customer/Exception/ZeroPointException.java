package Core.Customer.Exception;

public class ZeroPointException extends Exception{

    public ZeroPointException() {
        super("You don't have any points!");
    }
}
