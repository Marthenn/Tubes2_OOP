package Core.Serializer.Customer;

import Core.Customer.Customer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CustomerSerializer extends StdSerializer<Customer> {
    private ObjectMapper mapper = new ObjectMapper();

    public CustomerSerializer() {
        this(null);
    }

    public CustomerSerializer(Class<Customer> t) {
        super(t);
    }

    @Override
    public void serialize(Customer value,
                          JsonGenerator jgen,
                          SerializerProvider serializers)
            throws IOException, JsonProcessingException {
        jgen.writeStartObject();
        CustomerSerializerUtility utility = new CustomerSerializerUtility();
        utility.writeCustomerProperty(value, jgen);
        jgen.writeEndObject();
    }
}
