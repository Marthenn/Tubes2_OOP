package Core.DataStore.StorerData;

import Core.Customer.Customer;
import Core.DataStore.StorerData.Exception.StorerDataOfEmitter;
import Core.Deserializer.StorerData.StorerDataCustomerDeserializer;
import Core.IDAble.IDAbleListener;
import Core.Serializer.StorerData.StorerDataCustomerSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = StorerDataCustomerSerializer.class)
@JsonDeserialize(using = StorerDataCustomerDeserializer.class)
public class StorerDataCustomer extends StorerDataOfEmitter<Customer, IDAbleListener<Customer>> {
    public StorerDataCustomer() {
        super("Customer");
    }

}
