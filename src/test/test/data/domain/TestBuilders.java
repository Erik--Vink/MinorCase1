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

    public static SingleParticipant.SingleParticipantBuilder getSingleCourseParticipant(){
        return SingleParticipant.builder()
                .forename("John")
                .surname("Doe")
                .email("johh@mail.com")
                .zipcode("5161SP")
                .place("Sprang-Capelle")
                .street("Goudvink")
                .houseNumber("500")
                .type(CourseParticipantType.SINGLE)
                .accountNumber("NL123");
    }

    public static Subscription.SubscriptionBuilder getSubscription(){
        return Subscription.builder().courseId(44).courseParticipantId(7);
    }
}
