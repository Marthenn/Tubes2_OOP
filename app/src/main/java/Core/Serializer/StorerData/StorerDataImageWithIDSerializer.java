package Core.Serializer.StorerData;

import Core.DataStore.StorerData.StorerDataImageWithID;
import Core.DataStore.StorerData.StorerDataQuantifiableItem;
import Core.Item.Bill.Image.ImageWithID;
import Core.Item.QuantifiableItem;

public class StorerDataImageWithIDSerializer extends StorerDataSerializer<StorerDataImageWithID, ImageWithID> {
    public StorerDataImageWithIDSerializer() {
        super();
    }

    public StorerDataImageWithIDSerializer(Class<StorerDataImageWithID> t) {
        super(t);
    }
}
