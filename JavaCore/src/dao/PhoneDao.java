package dao;

import entity.Phone;
import java.util.List;

public interface PhoneDao {
    List<Phone> findAll();

    Phone findById(int id);

    void save(Phone phone);

    void update(Phone phone);

    void delete(int id);
}
