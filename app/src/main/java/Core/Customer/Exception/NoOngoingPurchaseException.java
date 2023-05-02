package Core.Customer.Exception;

public class NoOngoingPurchaseException extends Exception{

    public NoOngoingPurchaseException() {
        super("You can't pay, you don't have any ongoing purchase!");
    }
}
