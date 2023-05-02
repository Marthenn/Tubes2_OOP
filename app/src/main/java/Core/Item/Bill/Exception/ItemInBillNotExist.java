package Core.Item.Bill.Exception;

public class ItemInBillNotExist extends Exception{
    public ItemInBillNotExist(int id){
        super("Item with the " + id + " does not exist in the datastore");
    }
}
