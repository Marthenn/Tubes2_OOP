package Core.Serializer.StorerData;

import Core.Customer.Customer;
import Core.DataStore.StorerData.StorerDataBill;
import Core.DataStore.StorerData.StorerDataCustomer;
import Core.Item.Bill.Bill;

public class StorerDataBillSerializer extends StorerDataSerializer<StorerDataBill, Bill> {

    public StorerDataBillSerializer() {
        super();
    }

    public StorerDataBillSerializer(Class<StorerDataBill> t) {
        super(t);
    }
}
