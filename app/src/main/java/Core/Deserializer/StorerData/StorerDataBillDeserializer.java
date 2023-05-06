package Core.Deserializer.StorerData;

import Core.Customer.PremiumCustomer;
import Core.DataStore.StorerData.StorerDataBill;
import Core.DataStore.StorerData.StorerDataPremiumCustomer;
import Core.Item.Bill.Bill;
import Core.Pair;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.HashMap;

public class StorerDataBillDeserializer extends StdDeserializer<StorerDataBill> {

    public StorerDataBillDeserializer(){
        this(null);
    }

    public StorerDataBillDeserializer(Class<?> vc){
        super(vc);
    }

    @SneakyThrows
    @Override
    public StorerDataBill deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        StorerDataDeserializerUtility<Bill> utility = new StorerDataDeserializerUtility<>();
        Pair<String, HashMap<Integer, Bill>> attribute = utility.getDeserializedProperty(jp);
        StorerDataBill loadedStorerData = new StorerDataBill();
        utility.setDeserializedProperty(loadedStorerData, attribute);
        return loadedStorerData;
    }


}
