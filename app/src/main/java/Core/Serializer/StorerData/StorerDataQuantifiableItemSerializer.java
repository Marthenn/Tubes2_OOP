package Core.Serializer.StorerData;

import Core.DataStore.StorerData.StorerDataQuantifiableItem;
import Core.Item.QuantifiableItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.SneakyThrows;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;

public class StorerDataQuantifiableItemSerializer extends StdSerializer<StorerDataQuantifiableItem> {
    protected ObjectMapper mapper = new ObjectMapper();

    public StorerDataQuantifiableItemSerializer() {
        this(null);
    }

    public StorerDataQuantifiableItemSerializer(Class<StorerDataQuantifiableItem> t) {
        super(t);
    }
    
    @SneakyThrows
    @Override
    public void serialize(StorerDataQuantifiableItem value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeStringField("storedItemName", value.getStoredItemName());

        Field fieldItemsStore = value.getClass().getSuperclass().getDeclaredField("store");
        fieldItemsStore.setAccessible(true);
        ObjectMapper objectMapper = new ObjectMapper();
        jgen.writeObjectField("store", objectMapper.writeValueAsString((HashMap<Integer, QuantifiableItem>) fieldItemsStore.get(value)));
        jgen.writeEndObject();

    }


}
