package Core.Deserializer.Customer;

import Core.Customer.Customer;
import Core.Item.Bill.FixedBill.FixedBill;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.IntNode;
import lombok.SneakyThrows;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class CustomerDeserializerUtility {

    public CustomerDeserializerUtility() {
    }

    @SneakyThrows
    public Customer loadCustomer(JsonNode node) throws IOException {
        Integer id = (Integer) ((IntNode) node.get("id")).numberValue();
        Integer billId = null;
        if (node.get("billId") != null) {
            billId = (Integer) ((IntNode) node.get("billId")).numberValue();
        }

        JsonNode fixedBillJsonArray = node.get("history");
        ArrayList<FixedBill> fixedBill = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        if (fixedBillJsonArray.isArray()) {
            for (JsonNode jsonNode : fixedBillJsonArray) {
                fixedBill.add(objectMapper.treeToValue(jsonNode, FixedBill.class));
            }
        }

        Customer loadedCustomer = new Customer(id);

        Field billIdField = loadedCustomer.getClass().getDeclaredField("billId");
        Field historyField = loadedCustomer.getClass().getDeclaredField("history");

        billIdField.setAccessible(true);
        historyField.setAccessible(true);

        billIdField.set(loadedCustomer, billId);
        historyField.set(loadedCustomer, fixedBill);

        return loadedCustomer;


    }



}
