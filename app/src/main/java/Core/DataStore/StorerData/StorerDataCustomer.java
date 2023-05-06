package Core.DataStore.StorerData;

import Core.Customer.Customer;
import Core.Serializer.StorerData.StorerDataImageWithIDSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = StorerDataImageWithIDSerializer.class)
public class StorerDataCustomer extends StorerData<Customer> {
    public StorerDataCustomer() {
        super("Customer");
    }
}
