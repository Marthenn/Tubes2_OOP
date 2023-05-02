package Core.DataStore.Exception;

public class CustomerNotExistException extends Exception {
    public CustomerNotExistException() {
        super("Customer to be promoted does not exist");
    }
}
