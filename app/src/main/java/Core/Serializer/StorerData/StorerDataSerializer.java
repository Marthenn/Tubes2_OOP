package Core.Serializer.StorerData;

import Core.Customer.PremiumCustomer;
import Core.DataStore.StorerData.StorerData;
import Core.DataStore.StorerData.StorerDataCustomer;
import Core.IDAble.IDAble;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.SneakyThrows;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;

public class StorerDataSerializer<T extends StorerData<K>, K extends IDAble> extends StdSerializer<T> {

    public StorerDataSerializer() {
        this(null);
    }

    public StorerDataSerializer(Class<T> t) {
        super(t);
    }
    @Override
    @SneakyThrows
    public void serialize(T value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeStringField("storedItemName", value.getStoredItemName());
        System.out.println("Saving " +  value.getStoredItemName());

        Field fieldItemsStore = value.getClass().getSuperclass().getDeclaredField("store");
        fieldItemsStore.setAccessible(true);
        ObjectMapper objectMapper = new ObjectMapper();
        jgen.writeObjectField("store", objectMapper.writeValueAsString((HashMap<Integer, K>) fieldItemsStore.get(value)));
        jgen.writeEndObject();

        System.out.println("Finish saving " +  value.getStoredItemName());
    }

    @SneakyThrows
    public void serializeStorerEmitter(T value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeStringField("storedItemName", value.getStoredItemName());
        System.out.println("Saving " +  value.getStoredItemName());

        Field fieldItemsStore = value.getClass().getSuperclass().getSuperclass().getDeclaredField("store");
        fieldItemsStore.setAccessible(true);
        ObjectMapper objectMapper = new ObjectMapper();
        jgen.writeObjectField("store", objectMapper.writeValueAsString((HashMap<Integer, K>) fieldItemsStore.get(value)));
        jgen.writeEndObject();

        System.out.println("Finish saving " +  value.getStoredItemName());
    }


}
