package Core.Serializer.StorerData;

import Core.Customer.Customer;
import Core.Customer.PremiumCustomer;
import Core.DataStore.StorerData.StorerDataCustomer;
import Core.DataStore.StorerData.StorerDataPremiumCustomer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.SneakyThrows;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;

public class StorerDataPremiumCustomerSerializer extends StorerDataSerializer<StorerDataPremiumCustomer, PremiumCustomer> {
    public StorerDataPremiumCustomerSerializer() {
        super();
    }

    public StorerDataPremiumCustomerSerializer(Class<StorerDataPremiumCustomer> t) {
        super(t);
    }
}
