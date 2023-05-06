package Core.Serializer.Customer;

import Core.Customer.PremiumCustomer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class PremiumCustomerSerializer extends StdSerializer<PremiumCustomer> {
    private ObjectMapper mapper = new ObjectMapper();

    public PremiumCustomerSerializer() {
        this(null);
    }

    public PremiumCustomerSerializer(Class<PremiumCustomer> t) {
        super(t);
    }

    @Override
    public void serialize(PremiumCustomer value,
                          JsonGenerator jgen,
                          SerializerProvider serializers)
            throws IOException, JsonProcessingException {
        jgen.writeStartObject();
        CustomerSerializerUtility utility = new CustomerSerializerUtility();
        utility.writeCustomerProperty(value, jgen);
        jgen.writeStringField("stateName", value.getStatus().toString());
        jgen.writeStringField("name", value.getName());
        jgen.writeStringField("email", value.getEmail());
        jgen.writeStringField("phoneNumber", value.getPhoneNumber());
        jgen.writeNumberField("point", value.getPoint());
        jgen.writeEndObject();
    }
}
