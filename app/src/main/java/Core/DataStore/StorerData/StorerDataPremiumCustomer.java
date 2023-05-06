package Core.DataStore.StorerData;

import Core.Customer.PremiumCustomer;
import Core.Deserializer.StorerData.StorerDataPremiumCustomerDeserializer;
import Core.Serializer.StorerData.StorerDataPremiumCustomerSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonDeserialize(using = StorerDataPremiumCustomerDeserializer.class)
@JsonSerialize(using = StorerDataPremiumCustomerSerializer.class)
public class StorerDataPremiumCustomer extends StorerData<PremiumCustomer> {
    public StorerDataPremiumCustomer() {
        super("Premium Customer");
    }
}

