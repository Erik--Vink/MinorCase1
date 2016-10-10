package data.domain;

import data.controllers.CourseController;
import data.repositories.DummyCourseRepository;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.containsInAnyOrder;

/**
 * Created by Erik on 10-10-2016.
 */
public class CourseTest {

    @BeforeClass
    public static void initialize(){
    }

    @Test
    public void createCourseByObjectReferenceShouldReturnIncrementedId(){
        DummyCourseRepository.getInstance().setCourses(new ArrayList<>());
        CourseController courseController = new CourseController(DummyCourseRepository.getInstance());
        Course course = TestBuilders.getCourse().build();

        int id = courseController.createCourse(course);

        assertThat(id, is(1));
    }

    @Test
    public void getAllCoursesShouldReturnAllCourses(){
        ArrayList<Course> courses = new ArrayList<>();
        Course course1 = TestBuilders.getCourse().code("JAVA").build();
        Course course2 = TestBuilders.getCourse().code("C#").build();
        Course course3 = TestBuilders.getCourse().code("ORACLE").build();
        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
        DummyCourseRepository.getInstance().setCourses(courses);

        CourseController courseController = new CourseController(DummyCourseRepository.getInstance());

        assertThat(courseController.getAllCourses(), containsInAnyOrder(course1, course2, course3));
    }

    @Test
    public void getCourseByIdShouldReturnCourse(){
        ArrayList<Course> courses = new ArrayList<>();
        Course course1 = TestBuilders.getCourse().id(1).build();
        courses.add(course1);
        DummyCourseRepository.getInstance().setCourses(courses);

        CourseController courseController = new CourseController(DummyCourseRepository.getInstance());

        assertThat(courseController.getCourseById(1), is(course1));
    }

}