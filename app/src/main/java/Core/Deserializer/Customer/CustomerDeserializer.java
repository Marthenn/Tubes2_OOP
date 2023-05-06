package Core.Deserializer.Customer;

import Core.Customer.Customer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.SneakyThrows;

import java.io.IOException;

public class CustomerDeserializer extends StdDeserializer<Customer> {

    public CustomerDeserializer(){
        this(null);
    }

    public CustomerDeserializer(Class<?> vc){
        super(vc);
    }

    @SneakyThrows
    @Override
    public Customer deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        return new CustomerDeserializerUtility().loadCustomer(node);
    }


}
