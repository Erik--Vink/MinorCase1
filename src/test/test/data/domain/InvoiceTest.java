package data.domain;

import data.controllers.InvoiceController;
import data.controllers.SubscriptionController;
import data.repositories.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Created by Erik on 12-10-2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class InvoiceTest {

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Mock
    ICourseRepository courseRepository = new CourseRepository();

    @Mock
    ICourseParticipantRepository courseParticipantRepository = new CourseParticipantRepository();

    @Mock
    ISubscriptionRepository subscriptionRepository = new SubscriptionRepository();

    @InjectMocks
    InvoiceController invoiceController = new InvoiceController(courseParticipantRepository, courseRepository, subscriptionRepository);

    @Test
    public void getAllInvoicesWithCorrectYearAndWeeknumberShouldReturnAllInvoices(){

        int year = 2016;
        int weeknumber = 41;

        ArrayList<Course> courses = new ArrayList<>();
        courses.add(TestBuilders.getCourse().id(1).build());

        ArrayList<CourseParticipant> courseParticipants = new ArrayList<>();
        courseParticipants.add(TestBuilders.getSingleCourseParticipant().id(1).build());

        ArrayList<Subscription> subscriptions = new ArrayList<>();
        subscriptions.add(Subscription.builder().courseId(1).courseParticipantId(1).build());

        Invoice invoice = Invoice.builder().courseParticipant(courseParticipants.get(0)).courses(courses).build();

        when(courseRepository.getAll()).thenReturn(courses);
        when(courseRepository.getById(1)).thenReturn(courses.get(0));
        when(courseParticipantRepository.getAll()).thenReturn(courseParticipants);
        when(courseParticipantRepository.getById(1)).thenReturn(courseParticipants.get(0));
        when(subscriptionRepository.getAllByCourseId(1)).thenReturn(subscriptions);

        assertThat(invoiceController.getAllInvoices(year, weeknumber), containsInAnyOrder(invoice));

    }

}
