package Core.Item.Bill.Image;

import Core.Deserializer.ImageWithIDDeserializer;
import Core.IDAble.IDAble;
import Core.Serializer.ImageWithIDSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@JsonSerialize(using = ImageWithIDSerializer.class)
@JsonDeserialize(using = ImageWithIDDeserializer.class)
public class ImageWithID implements IDAble, Serializable {

    private Integer id;

    @Getter
    @Setter
    @NonNull
    private String base64Image;
    @Override
    public Integer getID() {
        return id;
    }
}
