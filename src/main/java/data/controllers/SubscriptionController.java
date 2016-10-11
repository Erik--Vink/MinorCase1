package data.controllers;

import data.domain.Subscription;
import data.repositories.ICourseParticipantRepository;
import data.repositories.ICourseRepository;
import data.repositories.ISubscriptionRepository;

import java.sql.SQLException;

/**
 * Created by Erik on 11-10-2016.
 */
public class SubscriptionController {

    private ISubscriptionRepository subscriptionRepository;
    private ICourseRepository courseRepository;
    private ICourseParticipantRepository courseParticipantRepository;

    public SubscriptionController(ISubscriptionRepository subscriptionRepository, ICourseRepository courseRepository, ICourseParticipantRepository courseParticipantRepository){
        this.subscriptionRepository = subscriptionRepository;
        this.courseRepository = courseRepository;
        this.courseParticipantRepository = courseParticipantRepository;
    }

    public boolean createSubscription(Subscription subscription) throws Exception {

        if(this.courseRepository.getById(subscription.getCourseId()) == null){
            throw new Exception("Course does not exist.");
        }
        if(this.courseParticipantRepository.getById(subscription.getCourseParticipantId()) == null) {
            throw new Exception("Participant does not exist.");
        }

        try{ return this.subscriptionRepository.create(subscription); }
        catch (SQLException e){throw new Exception("Participant is already subscribed for this course.");}
    }

}
