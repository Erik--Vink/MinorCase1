package data.domain;

import data.controllers.SubscriptionController;
import data.repositories.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.SQLException;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Erik on 11-10-2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class CourseParticipantTest {

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Mock
    ICourseRepository courseRepository = new CourseRepository();

    @Mock
    ICourseParticipantRepository courseParticipantRepository = new CourseParticipantRepository();

    @Mock
    ISubscriptionRepository subscriptionRepository = new SubscriptionRepository();

    @InjectMocks
    SubscriptionController subscriptionController =  new SubscriptionController(subscriptionRepository, courseRepository, courseParticipantRepository);

    @Test
    public void subscribeWithValidCourseAndParticipant() throws Exception {

        Subscription subscription = TestBuilders.getSubscription().build();

        when(courseRepository.getById(anyInt())).thenReturn(TestBuilders.getCourse().build());
        when(courseParticipantRepository.getById(anyInt())).thenReturn(TestBuilders.getSingleCourseParticipant().build());

        subscriptionController.createSubscription(subscription);

        verify(subscriptionRepository, times(1)).create(subscription);
    }

    @Test
    public void subscribeWithInvalidCourseIdReturnsError() throws Exception {
        Subscription subscription = TestBuilders.getSubscription().build();

        when(courseRepository.getById(anyInt())).thenReturn(null);
        when(courseParticipantRepository.getById(anyInt())).thenReturn(TestBuilders.getSingleCourseParticipant().build());

        thrown.expectMessage("Course does not exist");

        subscriptionController.createSubscription(subscription);
    }

    @Test
    public void subscribeWithInvalidParticipantIdReturnsError() throws Exception {
        Subscription subscription = TestBuilders.getSubscription().build();

        when(courseRepository.getById(anyInt())).thenReturn(TestBuilders.getCourse().build());
        when(courseParticipantRepository.getById(anyInt())).thenReturn(null);

        thrown.expectMessage("Participant does not exist");

        subscriptionController.createSubscription(subscription);
    }


    @Test
    public void subscribeWithDuplicateSubscriptionReturnsError() throws Exception {
        Subscription subscription = TestBuilders.getSubscription().build();

        when(courseRepository.getById(anyInt())).thenReturn(TestBuilders.getCourse().build());
        when(courseParticipantRepository.getById(anyInt())).thenReturn(TestBuilders.getSingleCourseParticipant().build());
        when(subscriptionRepository.create(subscription)).thenThrow(new SQLException());

        thrown.expectMessage("Participant is already subscribed for this course");

        subscriptionController.createSubscription(subscription);
    }
}
