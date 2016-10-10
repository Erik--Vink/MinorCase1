package data.repositories;

import data.domain.Course;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Erik on 10-10-2016.
 */
public class DummyCourseRepository implements ICourseRepository {

    private static DummyCourseRepository dummyCourseRepository;
    private int idCounter;
    private ArrayList<Course> courses;

    private DummyCourseRepository(){
        this.idCounter = 0;
        this.courses = new ArrayList<>();
    }

    public static DummyCourseRepository getInstance(){
        if(dummyCourseRepository == null){
            dummyCourseRepository = new DummyCourseRepository();
        }
        return dummyCourseRepository;
    }

    @Override
    public ArrayList<Course> getAll() {
        return this.courses;
    }

    @Override
    public Course getById(int id) {
        return this.courses.stream()
                .filter(c -> c.getId() == id)
                .findFirst().orElseGet(null);
    }

    @Override
    public int create(Course course) {
        this.idCounter++;
        course.setId(idCounter);
        courses.add(course);
        return course.getId();
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
        this.idCounter = 0;
    }
}
