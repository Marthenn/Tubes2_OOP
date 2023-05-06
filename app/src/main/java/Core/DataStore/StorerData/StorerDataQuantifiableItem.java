package Core.DataStore.StorerData;

import Core.Item.QuantifiableItem;
import Core.Serializer.StorerData.StorerDataQuantifiableItemSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = StorerDataQuantifiableItemSerializer.class)
public class StorerDataQuantifiableItem extends StorerData<QuantifiableItem> {
    public StorerDataQuantifiableItem() {
        super("Quantifiable Item");
    }
}
