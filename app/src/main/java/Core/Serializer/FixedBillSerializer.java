package Core.Serializer;

import Core.Item.Bill.FixedBill.FixedBill;
import Core.Item.Bill.FixedBill.FixedBillModifier.FixedBillModifier;
import Core.Item.QuantifiableItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class FixedBillSerializer extends StdSerializer<FixedBill> {
    private ObjectMapper mapper = new ObjectMapper();

    public FixedBillSerializer() {
        this(null);
    }

    public FixedBillSerializer(Class<FixedBill> t) {
        super(t);
    }

    @Override
    public void serialize(FixedBill value,
                          JsonGenerator jgen,
                          SerializerProvider serializers)
            throws IOException, JsonProcessingException {
        jgen.writeStartObject();
        jgen.writeNumberField("id", value.getID());

        jgen.writeArrayFieldStart("items");
        for (QuantifiableItem item : value.getItems()) {
            jgen.writeObject(item);
        }
        jgen.writeEndArray();

        jgen.writeArrayFieldStart("modifiers");
        for (FixedBillModifier modifier : value.getModifiers()) {
            jgen.writeObject(modifier);
        }
        jgen.writeEndArray();

        jgen.writeEndObject();
    }
}
