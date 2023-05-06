package Core.IDAble;

public interface IDAbleListener<T extends IDAble> {
    void onItemWithIDChange(T item);
}
