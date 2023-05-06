package Core.Deserializer;

import Core.Item.Bill.Image.ImageWithID;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import lombok.SneakyThrows;

import java.io.IOException;

public class ImageWithIDDeserializer extends StdDeserializer<ImageWithID> {

    public ImageWithIDDeserializer(){
        this(null);
    }

    public ImageWithIDDeserializer(Class<?> vc){
        super(vc);
    }

    @SneakyThrows
    @Override
    public ImageWithID deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        int id = (Integer) ((IntNode) node.get("id")).numberValue();
        String image = node.get("image").asText();
        return new ImageWithID(id, image);
    }


}
