package Core.Deserializer.Customer;

import Core.Customer.Customer;
import Core.Customer.MembershipState.MembershipStateName;
import Core.Customer.PremiumCustomer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import lombok.SneakyThrows;

import java.io.IOException;
import java.lang.reflect.Field;

public class PremiumCustomerDeserializer extends StdDeserializer<Customer> {

    public PremiumCustomerDeserializer(){
        this(null);
    }

    public PremiumCustomerDeserializer(Class<?> vc){
        super(vc);
    }

    @SneakyThrows
    @Override
    public Customer deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        Customer loadedCustomer = new CustomerDeserializerUtility().loadCustomer(node);

        MembershipStateName stateName = MembershipStateName.valueOf(node.get("stateName").asText());

        String name = node.get("name").asText();
        String email = node.get("email").asText();
        String phoneNumber = node.get("phoneNumber").asText();
        Integer point = ((IntNode) node.get("point")).intValue();

        PremiumCustomer loadedPremiumCustomer = new PremiumCustomer(loadedCustomer, name, phoneNumber, email);

        Field pointField = loadedPremiumCustomer.getClass().getDeclaredField("point");
        pointField.setAccessible(true);
        pointField.set(loadedPremiumCustomer, point);

        loadedPremiumCustomer.transitionToState(stateName);

        return new CustomerDeserializerUtility().loadCustomer(node);
    }


}
