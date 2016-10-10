package data.repositories;

import data.domain.Cursus;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * Created by Erik on 8-10-2016.
 */
public class CursusRepository implements ICursusRepository {

    private Connection connection;

    public CursusRepository(DatabaseConnection databaseConnection){
        this.connection = databaseConnection.getConnection();
    }

    @Override
    public ArrayList<Cursus> getAll() {
        return null;
    }

    @Override
    public Cursus getById(int id) {
        return null;
    }

    @Override
    public Cursus getByCode(String code) {
        return null;
    }

    @Override
    public int create(String code, String name, double price) {
        return 0;
    }

    @Override
    public int create(Cursus cursus) {
        return 0;
    }
}
