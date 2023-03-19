import dao.PhoneDao;
import dao.PhoneDaoImpl;
import entity.Phone;
import service.PhoneService;
import service.PhoneServiceImpl;

import java.util.List;
import java.util.Scanner;

public class Application {
    private final PhoneService phoneService;

    public Application(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    public static void main(String[] args) {
        PhoneDao PhoneDao = new PhoneDaoImpl();
        PhoneService phoneService = new PhoneServiceImpl(PhoneDao);
        Application app = new Application(phoneService);
        app.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Welcome to the Phone Database");
            System.out.println("1. List all Phones");
            System.out.println("2. Get Phone by ID");
            System.out.println("3. Add Phone");
            System.out.println("4. Update Phone");
            System.out.println("5. Delete Phone");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listPhones();
                    break;
                case 2:
                    getPhoneById(scanner);
                    break;
                case 3:
                    addPhone(scanner);
                    break;
                case 4:
                    updatePhone(scanner);
                    break;
                case 5:
                    deletePhone(scanner);
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice, please try again");
                    break;
            }
        } while (choice != 0);
        scanner.close();
    }

    private void listPhones() {
        List<Phone> phones = phoneService.getAllPhones();
        if (phones.isEmpty()) {
            System.out.println("No Phones found");
        } else {
            System.out.printf("%-5s %-15s %-15s %-15s %-10s\n", "ID", "Name", "Brand", "Type", "Price");
            for (Phone phone : phones) {
                System.out.printf("%-5d %-15s %-15s %-15s %-10.2f\n", phone.getId(), phone.getName(),
                        phone.getBrand(), phone.getType(), phone.getPrice());
            }
        }
    }

    private void getPhoneById(Scanner scanner) {
        System.out.print("Enter Phone ID: ");
        int id = scanner.nextInt();
        Phone phone = phoneService.getPhoneById(id);
        if (phone == null) {
            System.out.println("Phone not found");
        } else {
            System.out.printf("%-5s %-15s %-15s %-15s %-10s\n", "ID", "Name", "Brand", "Type", "Price");
            System.out.printf("%-5d %-15s %-15s %-15s %-10.2f\n", phone.getId(), phone.getName(),
                    phone.getBrand(), phone.getType(), phone.getPrice());
        }
    }

    private void addPhone(Scanner scanner) {
        System.out.print("Enter Phone ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter Phone name: ");
        String name = scanner.next();
        System.out.print("Enter Phone brand: ");
        String brand = scanner.next();
        System.out.print("Enter Phone type: ");
        String type = scanner.next();
        System.out.print("Enter Phone price: ");
        double price = scanner.nextDouble();

        Phone phone = new Phone(id, name, brand, type, price);
        phoneService.addPhone(phone);
        System.out.println("Phone added successfully");
    }

    private void updatePhone(Scanner scanner) {
        System.out.print("Enter Phone ID: ");
        int id = scanner.nextInt();
        Phone phone = phoneService.getPhoneById(id);
        if (phone == null) {
            System.out.println("Phone not found");
        } else {
            System.out.printf("%-5s %-15s %-15s %-15s %-10s\n", "ID", "Name", "Brand", "Type", "Price");
            System.out.printf("%-5d %-15s %-15s %-15s %-10.2f\n", phone.getId(), phone.getName(),
                    phone.getBrand(), phone.getType(), phone.getPrice());
            System.out.print("Enter new Phone name (or enter to keep current value): ");
            String name = scanner.next();
            if (!name.equals("")) {
                phone.setName(name);
            }
            System.out.print("Enter new Phone brand (or enter to keep current value): ");
            String brand = scanner.next();
            if (!brand.equals("")) {
                phone.setBrand(brand);
            }
            System.out.print("Enter new Phone type (or enter to keep current value): ");
            String type = scanner.next();
            if (!type.equals("")) {
                phone.setType(type);
            }
            System.out.print("Enter new Phone price (or enter to keep current value): ");
            String priceString = scanner.next();
            if (!priceString.equals("")) {
                double price = Double.parseDouble(priceString);
                phone.setPrice(price);
            }

            phoneService.updatePhone(phone);
            System.out.println("Phone updated successfully");
        }
    }

    private void deletePhone(Scanner scanner) {
        System.out.print("Enter Phone ID: ");
        int id = scanner.nextInt();
        Phone phone = phoneService.getPhoneById(id);
        if (phone == null) {
            System.out.println("Phone not found");
        } else {
            phoneService.deletePhone(phone.getId());
            System.out.println("Phone deleted successfully");
        }
    }
}