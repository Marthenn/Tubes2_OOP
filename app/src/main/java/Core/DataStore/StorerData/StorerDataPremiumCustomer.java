package Core.DataStore.StorerData;

import Core.Customer.Customer;
import Core.Customer.PremiumCustomer;
import Core.Serializer.StorerData.StorerDataImageWithIDSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = StorerDataImageWithIDSerializer.class)
public class StorerDataPremiumCustomer extends StorerData<PremiumCustomer> {
    public StorerDataPremiumCustomer() {
        super("Premium Customer");
    }
}
