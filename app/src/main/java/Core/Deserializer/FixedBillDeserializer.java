package Core.Deserializer;

import Core.Item.Bill.FixedBill.FixedBill;
import Core.Item.Bill.FixedBill.FixedBillModifier.FixedBillModifier;
import Core.Item.QuantifiableItem;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.SneakyThrows;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FixedBillDeserializer extends StdDeserializer<FixedBill> {

    public FixedBillDeserializer(){
        this(null);
    }

    public FixedBillDeserializer(Class<?> vc){
        super(vc);
    }

    @Override
    public FixedBill deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        Integer id = ((IntNode) node.get("id")).intValue();

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode modifiersNode = node.get("modifiers");
        JsonNode quantifiableItemsNode = node.get("items");

        ArrayList<FixedBillModifier> fixedBillModifiers = new ArrayList<>();
        ArrayList<QuantifiableItem> quantifiableItems = new ArrayList<>();

        if (modifiersNode != null && modifiersNode.isArray()) {
            for (JsonNode modifierNode : modifiersNode) {
                fixedBillModifiers.add(objectMapper.treeToValue((ObjectNode) modifierNode, FixedBillModifier.class));
            }
        }

        if (quantifiableItemsNode != null && quantifiableItemsNode.isArray()) {
            for (JsonNode quantifiableItemNode : quantifiableItemsNode) {
                    quantifiableItems.add(objectMapper.treeToValue((ObjectNode) quantifiableItemNode, QuantifiableItem.class));
            }
        }


//        try {
//            fixedBillModifiers = objectMapper.readValue(modifiersNode.asText(),  new TypeReference<List<FixedBillModifier>>(){});
////        Catch empty array
//        } catch (JsonProcessingException ignored) {
//        }

//        QuantifiableItem[] quantifiableItems = objectMapper.readValue(quantifiableItemNode.traverse(), QuantifiableItem[].class);

        return new FixedBill(id, quantifiableItems, fixedBillModifiers);
    }


}
