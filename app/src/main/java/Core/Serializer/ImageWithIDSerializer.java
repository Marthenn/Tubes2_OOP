package Core.Serializer;

import Core.Item.Bill.Image.ImageWithID;
import Core.Item.QuantifiableItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class ImageWithIDSerializer extends StdSerializer<ImageWithID> {
    private ObjectMapper mapper = new ObjectMapper();

    public ImageWithIDSerializer() {
        this(null);
    }

    public ImageWithIDSerializer(Class<ImageWithID> t) {
        super(t);
    }

    @Override
    public void serialize(ImageWithID value,
                          JsonGenerator jgen,
                          SerializerProvider serializers)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeNumberField("id", value.getID());
        jgen.writeStringField("image", value.getBase64Image());
        jgen.writeEndObject();
    }
}
