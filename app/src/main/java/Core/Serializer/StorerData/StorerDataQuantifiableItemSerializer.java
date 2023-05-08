package Core.Serializer.StorerData;

import Core.DataStore.StorerData.StorerDataBill;
import Core.DataStore.StorerData.StorerDataQuantifiableItem;
import Core.Item.Bill.Bill;
import Core.Item.QuantifiableItem;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.SneakyThrows;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;

public class StorerDataQuantifiableItemSerializer extends StorerDataSerializer<StorerDataQuantifiableItem, QuantifiableItem> {
    public StorerDataQuantifiableItemSerializer() {
        super();
    }

    public StorerDataQuantifiableItemSerializer(Class<StorerDataQuantifiableItem> t) {
        super(t);
    }


    @Override
    @SneakyThrows
    public void serialize(StorerDataQuantifiableItem value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        System.out.println("Saving QItem " +  value.getStoredItemName());

        this.serializeStorerEmitter(value, jgen, provider);

        System.out.println("Finish saving " +  value.getStoredItemName());
    }
}
