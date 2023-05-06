package Core.IDAble;

public interface IDAbleListener<T extends IDAble> {

    /**
     * Called by the observed IDAble item when the item's property changes.
     * @param item the item whose properties changed
     */
    void onItemWithIDChange(T item);
}
