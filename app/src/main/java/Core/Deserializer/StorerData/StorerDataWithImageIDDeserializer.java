package Core.Deserializer.StorerData;

import Core.DataStore.StorerData.StorerDataImageWithID;
import Core.Item.Bill.Image.ImageWithID;
import Core.Pair;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.HashMap;

public class StorerDataWithImageIDDeserializer extends StdDeserializer<StorerDataImageWithID> {

    public StorerDataWithImageIDDeserializer(){
        this(null);
    }

    public StorerDataWithImageIDDeserializer(Class<?> vc){
        super(vc);
    }

    @SneakyThrows
    @Override
    public StorerDataImageWithID deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        StorerDataDeserializerUtility<ImageWithID> utility = new StorerDataDeserializerUtility<ImageWithID>();
        TypeReference<HashMap<Integer, ImageWithID>> typeRef = new TypeReference<>() {};

        HashMap<Integer, ImageWithID> store = new ObjectMapper().readValue(node.get("store").asText(), typeRef);
        StorerDataImageWithID loadedStorerData = new StorerDataImageWithID();
        Pair<String, HashMap<Integer, ImageWithID>> attribute = new Pair<>(utility.getName(node), store);
        utility.setDeserializedProperty(loadedStorerData, attribute);
        return loadedStorerData;
    }

}
