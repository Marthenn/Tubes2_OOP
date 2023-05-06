package Core.Deserializer;

import Core.Item.Exception.NegativeQuantityException;
import Core.Item.Item;
import Core.Item.QuantifiableItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.IntNode;
import lombok.SneakyThrows;

import java.io.IOException;

public class QuantifiableItemDeserializer extends StdDeserializer<QuantifiableItem> {

    public QuantifiableItemDeserializer(){
        this(null);
    }



    public QuantifiableItemDeserializer(Class<?> vc){
        super(vc);
    }

    @SneakyThrows
    @Override
    public QuantifiableItem deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        int id = (Integer) ((IntNode) node.get("id")).numberValue();
        String name = node.get("name").asText();
        Double price = (Double) ((DoubleNode) node.get("singularPrice")).numberValue();
        Double cost = (Double) ((DoubleNode) node.get("singularCost")).numberValue();
        String category = node.get("category").asText();
        int imageId = (Integer) ((IntNode) node.get("imageId")).numberValue();
        boolean deleted = (Boolean) ((BooleanNode) node.get("deleted")).booleanValue();
        int quantity = (Integer) ((IntNode) node.get("quantity")).numberValue();

        if (quantity < 0) {
            quantity = 0;
        }

        return new QuantifiableItem(new Item(id, name, price, cost, category, imageId, deleted), quantity);
    }


}
