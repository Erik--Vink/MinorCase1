package data.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Erik on 11-10-2016.
 */
public class CourseParticipantFactory {

    public CourseParticipant resultSetToCourseParticipant(ResultSet resultSet) throws SQLException {
        CourseParticipant courseParticipant;

        CourseParticipantType type = CourseParticipantType.fromString(resultSet.getString("TYPE"));

        switch (type) {
            case SINGLE:
                courseParticipant = new SingleParticipant();
                break;
            case COMPANY:
                courseParticipant = new CompanyParticipant();
                break;
            default:
                throw new IllegalArgumentException("Invalid transaction type");
        }

        courseParticipant.setId(resultSet.getInt("ID"));
        courseParticipant.setForename(resultSet.getString("FORENAME"));
        courseParticipant.setSurname(resultSet.getString("SURNAME"));
        courseParticipant.setEmail(resultSet.getString("EMAIL"));
        courseParticipant.setZipcode(resultSet.getString("ZIPCODE"));
        courseParticipant.setPlace(resultSet.getString("PLACE"));
        courseParticipant.setStreet(resultSet.getString("STREET"));
        courseParticipant.setHouseNumber(resultSet.getString("HOUSENUMBER"));
        courseParticipant.setAccountNumber(resultSet.getString("ACCOUNTNUMBER"));
        courseParticipant.setType(type);
        courseParticipant.setParentId(resultSet.getInt("PARENT"));
        return courseParticipant;
    }
}
