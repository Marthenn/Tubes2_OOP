package Core.Item.Exception;

public class NegativeQuantityModifierException extends Exception {

    public NegativeQuantityModifierException() {
        super("Added/removed quantity cannot be negative");
    }
}
