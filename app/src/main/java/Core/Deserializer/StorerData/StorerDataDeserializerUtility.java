package Core.Deserializer.StorerData;

import Core.DataStore.StorerData.StorerData;
import Core.IDAble.IDAble;
import Core.Pair;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;

public class StorerDataDeserializerUtility<T extends IDAble> {

    public StorerDataDeserializerUtility() {
    }

    public Pair<String, HashMap<Integer, T>> getDeserializedProperty(JsonParser jp) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        String storerName = node.get("storedItemName").asText();
        TypeReference<HashMap<Integer, T>> typeRef = new TypeReference<>() {};
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<Integer, T> store = objectMapper.readValue(node.get("store").asText(), typeRef);

        return new Pair<>(storerName, store);
    }

    @SneakyThrows
    public void setDeserializedProperty(StorerData<?> storerData, Pair<String, HashMap<Integer, T>> attribute) throws IOException {
        Field storeNameField = storerData.getClass().getSuperclass().getDeclaredField("storedItemName");
        Field storeField = storerData.getClass().getSuperclass().getDeclaredField("store");

        storeNameField.setAccessible(true);
        storeField.setAccessible(true);

        storeField.set(storerData, attribute.getFirst());
        storeNameField.set(storerData, attribute.getSecond());
    }
}
