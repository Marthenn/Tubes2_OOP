package Core.Item.Bill.Exception;

public class DataStoreNotSetException extends Exception{

    public DataStoreNotSetException() {
        super("Datastore is null, set the data datastore first before getting value");
    }
}
