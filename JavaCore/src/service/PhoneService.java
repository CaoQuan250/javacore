package service;

import entity.Phone;

import java.util.List;

public interface PhoneService {
    List<Phone> getAllPhones();

    Phone getPhoneById(int id);

    void addPhone(Phone phone);

    void updatePhone(Phone phone);

    void deletePhone(int id);

}
