package Core.Serializer.StorerData;

import Core.DataStore.StorerData.StorerDataBill;
import Core.DataStore.StorerData.StorerDataQuantifiableItem;
import Core.Item.Bill.Bill;
import Core.Item.QuantifiableItem;

public class StorerDataQuantifiableItemSerializer extends StorerDataSerializer<StorerDataQuantifiableItem, QuantifiableItem> {
    public StorerDataQuantifiableItemSerializer() {
        super();
    }

    public StorerDataQuantifiableItemSerializer(Class<StorerDataQuantifiableItem> t) {
        super(t);
    }
}
