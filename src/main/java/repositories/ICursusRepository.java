package repositories;

import data.Cursus;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Erik on 8-10-2016.
 */
public interface ICursusRepository {
    ArrayList<Cursus> getAll();
    Cursus getById(int id);
    Cursus getByCode(String code);
    int create(String code, String name, double price);
    int create(Cursus cursus);
}
