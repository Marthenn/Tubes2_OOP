package Core.Deserializer;

import Core.Item.Bill.FixedBill.FixedBill;
import Core.Item.Bill.FixedBill.FixedBillModifier.FixedBillModifier;
import Core.Item.QuantifiableItem;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FixedBillDeserializer extends StdDeserializer<FixedBill> {

    public FixedBillDeserializer(){
        this(null);
    }

    public FixedBillDeserializer(Class<?> vc){
        super(vc);
    }

    @SneakyThrows
    @Override
    public FixedBill deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        Integer id = ((IntNode) node.get("id")).intValue();

        ObjectMapper objectMapper = new ObjectMapper();

        FixedBillModifier[] fixedBillModifiers = objectMapper.readValue(node.get("modifiers").asText(), FixedBillModifier[].class);
        QuantifiableItem[] quantifiableItems = objectMapper.readValue(node.get("items").asText(), QuantifiableItem[].class);

        return new FixedBill(id, new ArrayList<QuantifiableItem>(Arrays.asList(quantifiableItems)), new ArrayList<FixedBillModifier>(Arrays.asList(fixedBillModifiers)));
    }


}
