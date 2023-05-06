package Core.Serializer;

import Core.Item.Bill.FixedBill.FixedBillModifier.FixedBillModifier;
import Core.Item.QuantifiableItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class FixedBillModifierSerializer extends StdSerializer<FixedBillModifier> {
    private ObjectMapper mapper = new ObjectMapper();

    public FixedBillModifierSerializer() {
        this(null);
    }

    public FixedBillModifierSerializer(Class<FixedBillModifier> t) {
        super(t);
    }

    @Override
    public void serialize(FixedBillModifier value,
                          JsonGenerator jgen,
                          SerializerProvider serializers)
            throws IOException, JsonProcessingException {
        jgen.writeStartObject();

        jgen.writeEndObject();
    }
}
