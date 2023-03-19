package service;

import dao.PhoneDao;
import entity.Phone;

import java.util.List;

public class PhoneServiceImpl implements PhoneService {
    private final PhoneDao PhoneDao;

    public PhoneServiceImpl(PhoneDao PhoneDao) {
        this.PhoneDao = PhoneDao;
    }

    @Override
    public List<Phone> getAllPhones() {
        return PhoneDao.findAll();
    }

    @Override
    public Phone getPhoneById(int id) {
        return PhoneDao.findById(id);
    }

    @Override
    public void addPhone(Phone phone) {
        PhoneDao.save(phone);
    }

    @Override
    public void updatePhone(Phone phone) {
        PhoneDao.update(phone);
    }

    @Override
    public void deletePhone(int id) {
        PhoneDao.delete(id);
    }
}
