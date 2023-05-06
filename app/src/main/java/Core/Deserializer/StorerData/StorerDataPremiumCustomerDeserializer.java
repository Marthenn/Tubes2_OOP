package Core.Deserializer.StorerData;

import Core.Customer.PremiumCustomer;
import Core.DataStore.StorerData.StorerDataCustomer;
import Core.DataStore.StorerData.StorerDataPremiumCustomer;
import Core.Item.QuantifiableItem;
import Core.Pair;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
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
        StorerDataDeserializerUtility<PremiumCustomer> utility = new StorerDataDeserializerUtility<>();
        Pair<String, HashMap<Integer, PremiumCustomer>> attribute = utility.getDeserializedProperty(jp);
        StorerDataPremiumCustomer loadedStorerData = new StorerDataPremiumCustomer();
        utility.setDeserializedProperty(loadedStorerData, attribute);
        return loadedStorerData;
    }


}
