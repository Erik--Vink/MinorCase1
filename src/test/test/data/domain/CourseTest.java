package data.domain;

import data.controllers.CourseController;
import data.helpers.CourseReader;
import data.repositories.DummyCourseRepository;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.containsInAnyOrder;

/**
 * Created by Erik on 10-10-2016.
 */
public class CourseTest {

    @Rule
    public ExpectedException thrown= ExpectedException.none();


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

    @Test
    public void importValidCourseFileReturnsSingleCourse() throws IOException {

        String text = Files.toString(new File("src/test/resources/correctSingleCourse.txt"), Charsets.UTF_8);
        ArrayList<Course> courses = CourseReader.stringToCourses(text);
        Course course = TestBuilders.getCourse().title("C# Programmeren").code("CNETIN").startDate(LocalDate.parse("2013-10-14")).duration(5).build();

        assertThat(courses, containsInAnyOrder(course));
    }

    @Test
    public void importValidCourseFileReturnsMultipleCourses() throws IOException {
        String text = Files.toString(new File("src/test/resources/correctMultipleCourses.txt"), Charsets.UTF_8);
        ArrayList<Course> courses = CourseReader.stringToCourses(text);
        Course course1 = TestBuilders.getCourse().title("C# Programmeren").code("CNETIN").startDate(LocalDate.parse("2013-10-14")).duration(5).build();
        Course course2 = TestBuilders.getCourse().title("C# Programmeren").code("CNETIN").startDate(LocalDate.parse("2013-10-21")).duration(5).build();
        Course course3 = TestBuilders.getCourse().title("Advanced C#").code("ADCSB").startDate(LocalDate.parse("2013-10-21")).duration(2).build();
        Course course4 = TestBuilders.getCourse().title("Advanced C#").code("ADCSB").startDate(LocalDate.parse("2013-10-14")).duration(2).build();
        Course course5 = TestBuilders.getCourse().title("C# Programmeren").code("CNETIN").startDate(LocalDate.parse("2013-10-14")).duration(5).build();

        assertThat(courses, containsInAnyOrder(course1, course2, course3, course4, course5));
    }

    @Test
    public void importCourseFileWithMissingFieldsReturnsError() throws IOException {
        String text = Files.toString(new File("src/test/resources/missingFields.txt"), Charsets.UTF_8);

        thrown.expectMessage("missing fields");

        CourseReader.stringToCourses(text);
    }

    @Test
    public void importCourseFileWithMissingNewLinesReturnsError() throws IOException {
        String text = Files.toString(new File("src/test/resources/missingNewLines.txt"), Charsets.UTF_8);

        thrown.expectMessage("missing new lines");

        CourseReader.stringToCourses(text);
    }

    @Test
    public void importCourseFileWithWrongDateFormatReturnsError() throws IOException {
        String text = Files.toString(new File("src/test/resources/wrongDateFormat.txt"), Charsets.UTF_8);

        thrown.expectMessage("Invalid date format");

        CourseReader.stringToCourses(text);
    }

    @Test
    public void importCourseFileWithWrongDurationFormatReturnsError() throws IOException {
        String text = Files.toString(new File("src/test/resources/wrongDurationFormat.txt"), Charsets.UTF_8);

        thrown.expectMessage("Invalid duration format");

        CourseReader.stringToCourses(text);
    }

    @Test
    public void importCourseFileWithWrongOrderReturnsError() throws IOException {
        String text = Files.toString(new File("src/test/resources/wrongOrder.txt"), Charsets.UTF_8);

        thrown.expectMessage("Invalid order format");

        CourseReader.stringToCourses(text);
    }
}