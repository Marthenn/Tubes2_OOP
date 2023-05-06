package Core.Item.BillTest;

import Core.DataStore.DataStore;
import Core.Item.Bill.Bill;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BillTest {
    @Test
    public void testCreateBill() {
        Bill bill = DataStore.getInstance().createNewBill();
        assertNotNull(bill);
    }
}
