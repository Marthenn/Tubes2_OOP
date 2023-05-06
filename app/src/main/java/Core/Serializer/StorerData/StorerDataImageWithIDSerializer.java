package Core.Serializer.StorerData;

import Core.DataStore.StorerData.StorerData;
import Core.DataStore.StorerData.StorerDataImageWithID;
import Core.Item.Bill.Image.ImageWithID;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.SneakyThrows;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;

public class StorerDataImageWithIDSerializer extends StdSerializer<StorerDataImageWithID> {
    protected ObjectMapper mapper = new ObjectMapper();

    public StorerDataImageWithIDSerializer() {
        this(null);
    }

    public StorerDataImageWithIDSerializer(Class<StorerDataImageWithID> t) {
        super(t);
    }

    @SneakyThrows
    @Override
    public void serialize(StorerDataImageWithID value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeStringField("storedItemName", value.getStoredItemName());

        Field fieldItemsStore = value.getClass().getSuperclass().getDeclaredField("store");
        fieldItemsStore.setAccessible(true);
        ObjectMapper objectMapper = new ObjectMapper();
        jgen.writeObjectField("store", objectMapper.writeValueAsString((HashMap<Integer, ImageWithID>) fieldItemsStore.get(value)));
        jgen.writeEndObject();

    }


}
