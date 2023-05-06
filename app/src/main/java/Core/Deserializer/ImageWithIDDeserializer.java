package Core.Deserializer;

import Core.Item.Bill.Image.ImageWithID;
import Core.Item.Item;
import Core.Item.QuantifiableItem;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
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
