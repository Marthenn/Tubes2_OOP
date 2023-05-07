package Core.Serializer;

import Core.Item.QuantifiableItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class QuantifiableItemSerializer extends StdSerializer<QuantifiableItem> {
    private ObjectMapper mapper = new ObjectMapper();

    public QuantifiableItemSerializer() {
        this(null);
    }

    public QuantifiableItemSerializer(Class<QuantifiableItem> t) {
        super(t);
    }

    @Override
    public void serialize(QuantifiableItem value,
                          JsonGenerator jgen,
                          SerializerProvider serializers)
            throws IOException, JsonProcessingException {
        jgen.writeStartObject();
        jgen.writeNumberField("id", value.getID());
        jgen.writeStringField("name", value.getName());
        jgen.writeNumberField("singularCost", value.getSingularCost());
        jgen.writeNumberField("singularPrice" , value.getSingularPrice());
        jgen.writeNumberField("quantity", value.getQuantity());
        jgen.writeNumberField("imageId" , value.getImageID());
        jgen.writeStringField("category" , value.getCategory());
        jgen.writeBooleanField("deleted", value.isDeleted());
        jgen.writeEndObject();
    }
}
