package Core.Deserializer.StorerData;

import Core.DataStore.StorerData.StorerDataQuantifiableItem;
import Core.Item.QuantifiableItem;
import Core.Pair;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.SneakyThrows;

import java.io.IOException;
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
        StorerDataDeserializerUtility<QuantifiableItem> utility = new StorerDataDeserializerUtility<QuantifiableItem>();
        Pair<String, HashMap<Integer, QuantifiableItem>> attribute = utility.getDeserializedProperty(jp);
        StorerDataQuantifiableItem loadedStorerData = new StorerDataQuantifiableItem();
        utility.setDeserializedProperty(loadedStorerData, attribute);
        return loadedStorerData;
    }


}
