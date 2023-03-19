package dao;

import entity.Phone;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneDaoImpl implements PhoneDao{
    private static final String FILENAME = "phones.txt";

    @Override
    public List<Phone> findAll() {
        List<Phone> phones = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String brand = data[2];
                String type = data[3];
                double price = Double.parseDouble(data[4]);
                Phone phone = new Phone(id, name, brand, type, price);
                phones.add(phone);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phones;
    }

    @Override
    public Phone findById(int id) {
        Phone phone = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int employeeId = Integer.parseInt(data[0]);
                if (employeeId == id) {
                    String name = data[1];
                    String brand = data[2];
                    String type = data[3];
                    double price = Double.parseDouble(data[4]);
                    phone = new Phone(id, name, brand, type, price);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phone;
    }

    @Override
    public void save(Phone phone) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, true))) {
            writer.write(phone.getId() + "," + phone.getName() + "," + phone.getBrand() + ","
                    + phone.getType() + "," + phone.getPrice() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Phone phone) {
        List<Phone> phones = findAll();
        for (int i = 0; i < phones.size(); i++) {
            if (phones.get(i).getId() == phone.getId()) {
                phones.set(i, phone);
                break;
            }
        }
        saveEmployees(phones);
    }

    @Override
    public void delete(int id) {
        List<Phone> phones = findAll();
        phones.removeIf(employee -> employee.getId() == id);
        saveEmployees(phones);
    }

    private void saveEmployees(List<Phone> phones) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
            for (Phone phone : phones) {
                writer.write(phone.getId() + "," + phone.getName() + "," + phone.getBrand() + ","
                        + phone.getType() + "," + phone.getPrice() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
