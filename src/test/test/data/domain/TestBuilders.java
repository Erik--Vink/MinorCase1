package data.domain;

import java.time.LocalDate;

/**
 * Created by Erik on 10-10-2016.
 */
public class TestBuilders {

    public static Course.CourseBuilder getCourse(){
        LocalDate startDate = LocalDate.parse("2016-10-10");
        return Course.builder().title("C# Programmeren").code("CNETIN").startDate(startDate).duration(2);
    }
}
