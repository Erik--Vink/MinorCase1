package data.repositories;

import data.domain.Course;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Erik on 8-10-2016.
 */
public class CourseRepository implements ICourseRepository {

    @Override
    public ArrayList<Course> getAll() {
        try {
            Connection connection = new DatabaseConnection().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM COURSES");
            ArrayList<Course> courses = new ArrayList<>();

            while (resultSet.next()) {
                courses.add(resultSetToCourse(resultSet));
            }
            resultSet.close();
            connection.close();
            return courses;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Course getById(int id) {
        try {
            Connection connection = new DatabaseConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM COURSES where ID = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Course course = null;
            while(resultSet.next()){
                course = resultSetToCourse(resultSet);
            }
            resultSet.close();
            connection.close();
            return course;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int create(Course course) {
        try {
            Connection connection = new DatabaseConnection().getConnection();
            CallableStatement preparedStatement = connection.prepareCall("BEGIN INSERT INTO COURSES (TITLE, CODE, STARTDATE, DURATION) VALUES (?,?,?,?) returning id into ?; END;");
            preparedStatement.setString(1, course.getTitle());
            preparedStatement.setString(2, course.getCode());
            preparedStatement.setDate(3, Date.valueOf(course.getStartDate()));
            preparedStatement.setInt(4, course.getDuration());
            preparedStatement.registerOutParameter(5, Types.INTEGER);
            preparedStatement.execute();

            return preparedStatement.getInt(5);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private Course resultSetToCourse(ResultSet resultSet) throws SQLException {
        Course course = new Course();
        course.setId(resultSet.getInt("ID"));
        course.setTitle(resultSet.getString("TITLE"));
        course.setCode(resultSet.getString("CODE"));
        course.setStartDate(resultSet.getDate("STARTDATE").toLocalDate());
        course.setDuration(resultSet.getInt("DURATION"));
        return course;
    }
}
