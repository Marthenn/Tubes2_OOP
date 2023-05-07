package Core.Deserializer.StorerData;

import Core.DataStore.StorerData.StorerDataBill;
import Core.Item.Bill.Bill;
import Core.Pair;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.HashMap;

public class StorerDataBillDeserializer extends StdDeserializer<StorerDataBill> {

    public StorerDataBillDeserializer(){
        this(null);
    }

    public StorerDataBillDeserializer(Class<?> vc){
        super(vc);
    }

    @SneakyThrows
    @Override
    public StorerDataBill deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        StorerDataDeserializerUtility<Bill> utility = new StorerDataDeserializerUtility<>();
        TypeReference<HashMap<Integer, Bill>> typeRef = new TypeReference<>() {};
        HashMap<Integer, Bill> store = new ObjectMapper().readValue(node.get("store").asText(), typeRef);
        StorerDataBill loadedStorerData = new StorerDataBill();
        Pair<String, HashMap<Integer, Bill>> attribute = new Pair<>(utility.getName(node), store);
        utility.setDeserializedProperty(loadedStorerData, attribute);
        return loadedStorerData;
    }


}
