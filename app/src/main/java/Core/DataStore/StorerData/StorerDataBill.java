package Core.DataStore.StorerData;

import Core.Customer.Customer;
import Core.Item.Bill.Bill;
import Core.Serializer.StorerData.StorerDataImageWithIDSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = StorerDataImageWithIDSerializer.class)
public class StorerDataBill extends StorerData<Bill> {
    public StorerDataBill() {
        super("Bill");
    }
}
