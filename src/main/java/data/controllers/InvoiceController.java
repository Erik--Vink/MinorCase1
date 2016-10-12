package data.controllers;

import data.domain.*;
import data.repositories.ICourseParticipantRepository;
import data.repositories.ICourseRepository;
import data.repositories.ISubscriptionRepository;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Created by Erik on 12-10-2016.
 */
public class InvoiceController {

    private ICourseParticipantRepository courseParticipantRepository;

    private ICourseRepository courseRepository;
    private ISubscriptionRepository subscriptionRepository;

    public InvoiceController(ICourseParticipantRepository courseParticipantRepository, ICourseRepository courseRepository, ISubscriptionRepository subscriptionRepository){
        this.courseParticipantRepository = courseParticipantRepository;
        this.courseRepository = courseRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public ArrayList<Invoice> getAllInvoices(int year, int weeknumber){

        ArrayList<Subscription> subscriptions = new ArrayList<>();
        ArrayList<CourseParticipant> courseParticipants = this.courseParticipantRepository.getAll();
        ArrayList<CourseParticipant> subscribedParticipants = new ArrayList<>();
        ArrayList<Invoice> invoices = new ArrayList<>();

        //get all courses and filter courses by year and weeknumber
        ArrayList<Course> courses = this.courseRepository.getAll().stream()
                .filter(c -> c.getStartDate().getYear() == year && getWeeknumberFromLocalDate(c.getStartDate()) == weeknumber).collect(Collectors.toCollection(ArrayList::new));


        //get all subscriptions
        for(Course course : courses){
            ArrayList<Subscription> courseSubscriptions = this.subscriptionRepository.getAllByCourseId(course.getId());

            for(Subscription subscription : courseSubscriptions){
                subscriptions.add(subscription);
            }
        }

        //get all participants
        for(Subscription subscription : subscriptions){
            subscribedParticipants.add(this.courseParticipantRepository.getById(subscription.getCourseParticipantId()));
        }

        List<CourseParticipant> companies = courseParticipants.stream()
                .filter(c -> c.getType() == CourseParticipantType.COMPANY)
                .collect(Collectors.toList());

        List<CourseParticipant> participants = subscribedParticipants.stream()
                .filter(c -> c.getType() == CourseParticipantType.SINGLE && c.getParentId() == 0)
                .collect(Collectors.toList());

        for(CourseParticipant company : companies){

            Invoice invoice = new Invoice();

            ArrayList<CourseParticipant> matchingParticipants = subscribedParticipants.stream().filter(p -> p.getParentId() == company.getId()).collect(Collectors.toCollection(ArrayList::new));

            ArrayList<Course> courseList = subscriptions.stream()
                    .filter(s -> matchingParticipants.stream().anyMatch(p -> p.getId() == s.getCourseParticipantId()))
                    .map(s -> this.courseRepository.getById(s.getCourseId())).collect(Collectors.toCollection(ArrayList::new));

            invoice.setCourses(courseList);
            invoice.setCourseParticipant(company);
            if(invoice.getCourses().size() >0){
                invoices.add(invoice);
            }
        }

        for(CourseParticipant participant : participants){
            Invoice invoice = new Invoice();
            invoice.setCourses(subscriptions.stream().filter(s -> s.getCourseParticipantId() == participant.getId()).map(s -> this.courseRepository.getById(s.getCourseId())).collect(Collectors.toCollection(ArrayList::new)));
            invoice.setCourseParticipant(participant);
            invoices.add(invoice);
        }

        return invoices;
    }

    private int getWeeknumberFromLocalDate(LocalDate date){
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        return date.get(weekFields.weekOfWeekBasedYear());
    }

}
