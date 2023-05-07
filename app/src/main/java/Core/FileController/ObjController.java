package Core.FileController;

import Core.DataStore.StorerData.*;
import Core.Settings.SaveFileType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ObjController extends ConcreteFileController {
    public ObjController(){
    }

    @Override
    public void saveImage(StorerDataImageWithID images) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(getFile("images"));
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(images);
        objectOut.close();
    }

    @Override
    public void saveCustomers(StorerDataCustomer customer) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(getFile("customer"));
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(customer);
        objectOut.close();
    }

    @Override
    public void savePremiumCustomers(StorerDataPremiumCustomer premiumCustomer) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(getFile("premiumCustomer"));
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(premiumCustomer);
        objectOut.close();
    }

    @Override
    public String getFileType() {
        return SaveFileType.OBJ.getName();
    }

    @Override
    public void saveItem(StorerDataQuantifiableItem item) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(getFile("item"));
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(item);
        objectOut.close();
    }

    @Override
    public void saveBill(StorerDataBill bills) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(getFile("bills"));
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(bills);
        objectOut.close();
    }

    @Override
    public StorerDataBill loadBill() throws IOException {
        FileInputStream fileIn = new FileInputStream(getFile("bills"));
        ObjectInputStream in = new ObjectInputStream(fileIn);
        StorerDataBill bill = null;
        try {
            bill = (StorerDataBill) in.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        in.close();
        fileIn.close();
        return bill;
    }

    @Override
    public StorerDataImageWithID loadImage() throws IOException {
        FileInputStream fileIn = new FileInputStream(getFile("images"));
        ObjectInputStream in = new ObjectInputStream(fileIn);
        StorerDataImageWithID image = null;
        try {
            image = (StorerDataImageWithID) in.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        in.close();
        fileIn.close();
        return image;
    }

    @Override
    public StorerDataQuantifiableItem loadItem() throws IOException {
        FileInputStream fileIn = new FileInputStream(getFile("item"));
        ObjectInputStream in = new ObjectInputStream(fileIn);
        StorerDataQuantifiableItem item = null;
        try {
            item = (StorerDataQuantifiableItem) in.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        in.close();
        fileIn.close();
        return item;
    }

    @Override
    public StorerDataCustomer loadCustomer() throws IOException {
        FileInputStream fileIn = new FileInputStream(getFile("customer"));
        ObjectInputStream in = new ObjectInputStream(fileIn);
        StorerDataCustomer customer = null;
        try {
            customer = (StorerDataCustomer) in.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        in.close();
        fileIn.close();
        return customer;
    }

    @Override
    public StorerDataPremiumCustomer loadPremiumCustomer() throws IOException {
        FileInputStream fileIn = new FileInputStream(getFile("premium_customer"));
        ObjectInputStream in = new ObjectInputStream(fileIn);
        StorerDataPremiumCustomer premiumCustomer = null;
        try {
            premiumCustomer = (StorerDataPremiumCustomer) in.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        in.close();
        fileIn.close();
        return premiumCustomer;
    }
}
