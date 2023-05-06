package Core.DataStore.StorerData;

import Core.Deserializer.StorerData.StorerDataBillDeserializer;
import Core.Item.Bill.Bill;
import Core.Serializer.StorerData.StorerDataBillSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = StorerDataBillSerializer.class)
@JsonDeserialize(using = StorerDataBillDeserializer.class)
public class StorerDataBill extends StorerData<Bill> {
    public StorerDataBill() {
        super("Bill");
    }
}
