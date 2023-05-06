package Core.DataStore.StorerData;

import Core.Item.Bill.Image.ImageWithID;
import Core.Serializer.StorerData.StorerDataImageWithIDSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = StorerDataImageWithIDSerializer.class)
public class StorerDataImageWithID extends StorerData<ImageWithID> {
    public StorerDataImageWithID() {
        super("ImageWithID");
    }
}
