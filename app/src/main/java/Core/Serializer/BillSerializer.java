package Core.Serializer;

import Core.Item.Bill.Bill;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.SneakyThrows;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;

public class BillSerializer extends StdSerializer<Bill> {
    private ObjectMapper mapper = new ObjectMapper();

    public BillSerializer() {
        this(null);
    }

    public BillSerializer(Class<Bill> t) {
        super(t);
    }

    @SneakyThrows
    @Override
    public void serialize(Bill value,
                          JsonGenerator jgen,
                          SerializerProvider serializers)
            throws IOException, JsonProcessingException {
        jgen.writeStartObject();
        jgen.writeNumberField("id", value.getID());
        if (value.getOwnerId() != null){
            jgen.writeNumberField("ownerId", value.getOwnerId());
        }

        Field fieldItemsQuantity = value.getClass().getDeclaredField("itemsQuantity");
        fieldItemsQuantity.setAccessible(true);

        ObjectMapper objectMapper = new ObjectMapper();

        jgen.writeObjectField("itemsQuantity", objectMapper.writeValueAsString((HashMap<Integer, Integer>) fieldItemsQuantity.get(value)));

        jgen.writeEndObject();
    }
}
