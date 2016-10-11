package data.repositories;

import data.domain.Subscription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
