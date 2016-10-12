package data.repositories;

import data.domain.Course;
import data.domain.Subscription;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Erik on 8-10-2016.
 */
public interface ISubscriptionRepository {
    boolean create(Subscription subscription) throws SQLException;
    ArrayList<Subscription> getAllByCourseId(int id);
}
