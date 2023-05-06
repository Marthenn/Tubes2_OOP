package Core.Serializer.StorerData;

import Core.Customer.Customer;
import Core.Customer.PremiumCustomer;
import Core.DataStore.StorerData.StorerDataBill;
import Core.DataStore.StorerData.StorerDataCustomer;
import Core.DataStore.StorerData.StorerDataPremiumCustomer;

public class StorerDataCustomerSerializer extends StorerDataSerializer<StorerDataCustomer, Customer> {

    public StorerDataCustomerSerializer() {
        super();
    }

    public StorerDataCustomerSerializer(Class<StorerDataCustomer> t) {
        super(t);
    }
}
