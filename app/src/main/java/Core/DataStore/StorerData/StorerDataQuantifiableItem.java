package Core.DataStore.StorerData;

import Core.Deserializer.StorerData.StorerDataQuantifiableItemDeserializer;
import Core.Item.QuantifiableItem;
import Core.Serializer.StorerData.StorerDataQuantifiableItemSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = StorerDataQuantifiableItemSerializer.class)
@JsonDeserialize(using = StorerDataQuantifiableItemDeserializer.class)
public class StorerDataQuantifiableItem extends StorerData<QuantifiableItem> {
    public StorerDataQuantifiableItem() {
        super("Quantifiable Item");
    }
}
