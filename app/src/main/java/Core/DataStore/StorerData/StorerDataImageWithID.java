package Core.DataStore.StorerData;

import Core.Deserializer.StorerData.StorerDataWithImageIDDeserializer;
import Core.Item.Bill.Image.ImageWithID;
import Core.Serializer.StorerData.StorerDataImageWithIDSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = StorerDataImageWithIDSerializer.class)
@JsonDeserialize(using = StorerDataWithImageIDDeserializer.class)
public class StorerDataImageWithID extends StorerData<ImageWithID> {
    public StorerDataImageWithID() {
        super("ImageWithID");
    }
}
