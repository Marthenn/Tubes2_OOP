package Core.DataStore;

public class DataStore {
    private static DataStore instance = null;

    private DataStore() {

    }

    public static DataStore getInstance() {
        if (DataStore.instance == null) {
            DataStore.instance = new DataStore();
        }
        return DataStore.instance;
    }

}
