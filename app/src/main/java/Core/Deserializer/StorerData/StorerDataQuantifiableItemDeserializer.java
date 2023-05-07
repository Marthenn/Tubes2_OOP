package Core.Deserializer.StorerData;

import Core.DataStore.StorerData.StorerDataImageWithID;
import Core.DataStore.StorerData.StorerDataQuantifiableItem;
import Core.Item.Bill.Image.ImageWithID;
import Core.Item.QuantifiableItem;
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
import java.lang.reflect.Field;
import java.util.HashMap;

public class StorerDataQuantifiableItemDeserializer extends StdDeserializer<StorerDataQuantifiableItem> {

    public StorerDataQuantifiableItemDeserializer(){
        this(null);
    }

    public StorerDataQuantifiableItemDeserializer(Class<?> vc){
        super(vc);
    }

    @SneakyThrows
    @Override
    public StorerDataQuantifiableItem deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        StorerDataDeserializerUtility<QuantifiableItem> utility = new StorerDataDeserializerUtility<>();
        TypeReference<HashMap<Integer, QuantifiableItem>> typeRef = new TypeReference<>() {};
        HashMap<Integer, QuantifiableItem> store = new ObjectMapper().readValue(node.get("store").asText(), typeRef);
        StorerDataQuantifiableItem loadedStorerData = new StorerDataQuantifiableItem();
        Pair<String, HashMap<Integer, QuantifiableItem>> attribute = new Pair<>(utility.getName(node), store);
        utility.setDeserializedPropertyStorerEmitter(loadedStorerData, attribute);

        return loadedStorerData;
    }


}
