package Core.Deserializer.StorerData;

import Core.DataStore.StorerData.Exception.StorerDataOfEmitter;
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

    public String getName(JsonNode node) throws IOException {
        return node.get("storedItemName").asText();
    }

    @SneakyThrows
    public void setDeserializedProperty(StorerData<?> storerData, Pair<String, HashMap<Integer, T>> attribute) throws IOException {
        Field storeNameField = storerData.getClass().getSuperclass().getDeclaredField("storedItemName");
        Field storeField = storerData.getClass().getSuperclass().getDeclaredField("store");

        storeNameField.setAccessible(true);
        storeField.setAccessible(true);

        storeNameField.set(storerData, attribute.getFirst());
        storeField.set(storerData, attribute.getSecond());
    }

    @SneakyThrows
    public void setDeserializedPropertyStorerEmitter(StorerDataOfEmitter<?, ?> storerData, Pair<String, HashMap<Integer, T>> attribute) throws IOException {
        Field storeNameField = storerData.getClass().getSuperclass().getSuperclass().getDeclaredField("storedItemName");
        Field storeField = storerData.getClass().getSuperclass().getSuperclass().getDeclaredField("store");

        storeNameField.setAccessible(true);
        storeField.setAccessible(true);

        storeNameField.set(storerData, attribute.getFirst());
        storeField.set(storerData, attribute.getSecond());
    }
}
