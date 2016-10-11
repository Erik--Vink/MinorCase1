package data.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by Erik on 11-10-2016.
 */
@NoArgsConstructor
@Builder
public class CompanyParticipant extends CourseParticipant{

    public CompanyParticipant(String email, String forename, String surname, String zipcode, String place, String street, String houseNumber, String accountNumber) {
        this.email = email;
        this.forename = forename;
        this.surname = surname;
        this.zipcode = zipcode;
        this.place = place;
        this.street = street;
        this.houseNumber = houseNumber;
        this.accountNumber = accountNumber;
        this.type = CourseParticipantType.COMPANY;
        this.courseParticipantList = new LinkedList<>();
    }

    @Override
    public boolean add(CourseParticipant courseParticipant) {
        courseParticipant.setParent(this);
        return this.courseParticipantList.add(courseParticipant);
    }

    @Override
    public ListIterator createListIterator() {
        ListIterator listIterator = this.courseParticipantList.listIterator();
        return listIterator;
    }
}
