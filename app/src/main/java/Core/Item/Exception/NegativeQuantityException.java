package Core.Item.Exception;

public class NegativeQuantityException extends Exception {
    public NegativeQuantityException() {
        super("Quantity Cannot Be Negative");
    }
}
