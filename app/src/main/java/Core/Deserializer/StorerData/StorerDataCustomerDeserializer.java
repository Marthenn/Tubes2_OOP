package Core.Deserializer.StorerData;

import Core.Customer.Customer;
import Core.Customer.Customer;
import Core.DataStore.StorerData.StorerDataCustomer;
import Core.DataStore.StorerData.StorerDataCustomer;
import Core.DataStore.StorerData.StorerDataQuantifiableItem;
import Core.Item.QuantifiableItem;
import Core.Pair;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.HashMap;

public class StorerDataCustomerDeserializer extends StdDeserializer<StorerDataCustomer> {

    public StorerDataCustomerDeserializer(){
        this(null);
    }

    public StorerDataCustomerDeserializer(Class<?> vc){
        super(vc);
    }

    @SneakyThrows
    @Override
    public StorerDataCustomer deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        StorerDataDeserializerUtility<Customer> utility = new StorerDataDeserializerUtility<>();
        TypeReference<HashMap<Integer, Customer>> typeRef = new TypeReference<>() {};
        HashMap<Integer, Customer> store = new ObjectMapper().readValue(node.get("store").asText(), typeRef);
        StorerDataCustomer loadedStorerData = new StorerDataCustomer();
        Pair<String, HashMap<Integer, Customer>> attribute = new Pair<>(utility.getName(node), store);
        utility.setDeserializedProperty(loadedStorerData, attribute);
        return loadedStorerData;
    }


}
