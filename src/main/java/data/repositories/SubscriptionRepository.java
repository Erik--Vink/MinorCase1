package data.repositories;

import data.domain.Course;
import data.domain.Subscription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Erik on 11-10-2016.
 */
public class SubscriptionRepository implements ISubscriptionRepository {

    @Override
    public boolean create(Subscription subscription) throws SQLException {

        Connection connection = new DatabaseConnection().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO SUBSCRIPTIONS (COURSEID, COURSEPARTICIPANTID) VALUES (?,?)");
        preparedStatement.setInt(1, subscription.getCourseId());
        preparedStatement.setInt(2, subscription.getCourseParticipantId());
        preparedStatement.executeUpdate();
        connection.close();

        return true;
    }

    @Override
    public ArrayList<Subscription> getAllByCourseId(int id) {
        ArrayList<Subscription> subscriptions = new ArrayList<>();

        try {
            Connection connection = new DatabaseConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM SUBSCRIPTIONS where COURSEID = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                subscriptions.add(resultSetToSubscription(resultSet));
            }
            resultSet.close();
            connection.close();
            return subscriptions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subscriptions;
    }

    private Subscription resultSetToSubscription(ResultSet resultSet) throws SQLException {
        Subscription subscription = new Subscription();
        subscription.setCourseId(resultSet.getInt("COURSEID"));
        subscription.setCourseParticipantId(resultSet.getInt("COURSEPARTICIPANTID"));
        return subscription;
    }
}
