package data.repositories;

import data.domain.*;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Erik on 11-10-2016.
 */
public class CourseParticipantRepository implements ICourseParticipantRepository{

    private CourseParticipantFactory courseParticipantFactory;

    public CourseParticipantRepository(){
        this.courseParticipantFactory = new CourseParticipantFactory();
    }

    @Override
    public ArrayList<CourseParticipant> getAll() {
        try {
            Connection connection = new DatabaseConnection().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM COURSEPARTICIPANTS ORDER BY PARENT DESC");
            ArrayList<CourseParticipant> courseParticipants = new ArrayList<>();

            while (resultSet.next()) {
                courseParticipants.add(this.courseParticipantFactory.resultSetToCourseParticipant(resultSet));
            }
            resultSet.close();
            connection.close();
            return courseParticipants;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CourseParticipant getById(int id) {
        try {
            Connection connection = new DatabaseConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM COURSEPARTICIPANTS where ID = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            CourseParticipant courseParticipant = null;
            while(resultSet.next()){
                courseParticipant = this.courseParticipantFactory.resultSetToCourseParticipant(resultSet);
            }
            resultSet.close();
            connection.close();
            return courseParticipant;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<CourseParticipant> getAllByParentId(int id) {
        try {
            Connection connection = new DatabaseConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM COURSEPARTICIPANTS WHERE PARENT = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<CourseParticipant> courseParticipants = new ArrayList<>();

            while (resultSet.next()) {
                courseParticipants.add(this.courseParticipantFactory.resultSetToCourseParticipant(resultSet));
            }
            resultSet.close();
            connection.close();
            return courseParticipants;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int create(CourseParticipant courseParticipant) {
        return 0;
    }
}
