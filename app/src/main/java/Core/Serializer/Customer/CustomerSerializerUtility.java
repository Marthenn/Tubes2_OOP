package Core.Serializer.Customer;

import Core.Customer.Customer;
import Core.Item.Bill.FixedBill.FixedBill;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;

public class CustomerSerializerUtility  {
    public void writeCustomerProperty(Customer value, JsonGenerator jgen) throws IOException {
        jgen.writeNumberField("id", value.getID());

        jgen.writeArrayFieldStart("history");
        for (FixedBill fixedBill : value.getHistory()) {
            jgen.writeObject(fixedBill);
        }
        jgen.writeEndArray();

        if (!value.isNoOngoingPurchase()) {
            jgen.writeNumberField("billId", value.getBillID());
        }
    }
}
