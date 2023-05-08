package Core.Serializer.StorerData;

import Core.Customer.Customer;
import Core.Customer.PremiumCustomer;
import Core.DataStore.StorerData.StorerDataBill;
import Core.DataStore.StorerData.StorerDataCustomer;
import Core.DataStore.StorerData.StorerDataPremiumCustomer;
import Core.DataStore.StorerData.StorerDataQuantifiableItem;
import Core.Item.QuantifiableItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.SneakyThrows;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;

public class StorerDataCustomerSerializer extends StorerDataSerializer<StorerDataCustomer, Customer> {

    public StorerDataCustomerSerializer() {
        super();
    }

    public StorerDataCustomerSerializer(Class<StorerDataCustomer> t) {
        super(t);
    }

    @SneakyThrows
    public void serialize(StorerDataCustomer value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        System.out.println("Saving " +  value.getStoredItemName());
        this.serializeStorerEmitter(value, jgen, provider);
        System.out.println("Finish saving " +  value.getStoredItemName());
    }
}
