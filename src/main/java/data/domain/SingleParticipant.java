package data.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ListIterator;

/**
 * Created by Erik on 11-10-2016.
 */
@NoArgsConstructor

public class SingleParticipant extends CourseParticipant{

    @Builder
    public SingleParticipant(int id, String email, String forename, String surname, String zipcode, String place, String street, String houseNumber, String accountNumber, CourseParticipantType type) {
        this.id = id;
        this.email = email;
        this.forename = forename;
        this.surname = surname;
        this.zipcode = zipcode;
        this.place = place;
        this.street = street;
        this.houseNumber = houseNumber;
        this.accountNumber = accountNumber;
        this.type = CourseParticipantType.SINGLE;
    }

    @Override
    public boolean add(CourseParticipant courseParticipant) {
        return false;
    }

    @Override
    public ListIterator createListIterator() {
        return null;
    }
}
