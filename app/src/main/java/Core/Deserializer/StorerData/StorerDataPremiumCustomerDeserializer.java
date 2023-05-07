package Core.Deserializer.StorerData;

import Core.Customer.PremiumCustomer;
import Core.DataStore.StorerData.StorerDataCustomer;
import Core.DataStore.StorerData.StorerDataPremiumCustomer;
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

public class StorerDataPremiumCustomerDeserializer extends StdDeserializer<StorerDataPremiumCustomer> {

    public StorerDataPremiumCustomerDeserializer(){
        this(null);
    }

    public StorerDataPremiumCustomerDeserializer(Class<?> vc){
        super(vc);
    }

    @SneakyThrows
    @Override
    public StorerDataPremiumCustomer deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        StorerDataDeserializerUtility<PremiumCustomer> utility = new StorerDataDeserializerUtility<>();
        TypeReference<HashMap<Integer, PremiumCustomer>> typeRef = new TypeReference<>() {};
        HashMap<Integer, PremiumCustomer> store = new ObjectMapper().readValue(node.get("store").asText(), typeRef);
        StorerDataPremiumCustomer loadedStorerData = new StorerDataPremiumCustomer();
        Pair<String, HashMap<Integer, PremiumCustomer>> attribute = new Pair<>(utility.getName(node), store);
        utility.setDeserializedProperty(loadedStorerData, attribute);
        return loadedStorerData;
    }


}
