package Core.Item.Bill.Image;

import Core.IDAble.IDAble;
import Core.Serializer.ImageWithIDSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@JsonSerialize(using = ImageWithIDSerializer.class)
public class ImageWithID implements IDAble {

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
