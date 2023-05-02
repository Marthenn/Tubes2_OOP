package Core.Item.Bill.Exception;

public class ItemIsNotInBillException extends  Exception {
    public ItemIsNotInBillException() {
        super("Item dengan ID yang diberikan tidak ada di dalam bill");
    }
}
