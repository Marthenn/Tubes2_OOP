package Core.Deserializer;

import Core.Item.Bill.Bill;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.HashMap;

public class BillDeserializer extends StdDeserializer<Bill> {

    public BillDeserializer(){
        this(null);
    }

    public BillDeserializer(Class<?> vc){
        super(vc);
    }

    @SneakyThrows
    @Override
    public Bill deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        int id = (Integer) ((IntNode) node.get("id")).numberValue();
        Integer ownerId = null;
        if (node.get("ownerId") != null ){
            ownerId = (Integer) ((IntNode) node.get("ownerId")).numberValue();
        }

        TypeReference<HashMap<Integer, Integer>> typeRef = new TypeReference<HashMap<Integer, Integer>>() {};
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<Integer, Integer> itemsQuantity = objectMapper.readValue(node.get("itemsQuantity").asText(), typeRef);
        return new Bill(id, ownerId, itemsQuantity);
    }


}
