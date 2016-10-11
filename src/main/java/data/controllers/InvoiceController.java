package data.controllers;

import data.domain.CourseParticipant;
import data.domain.Invoice;
import data.repositories.ICourseParticipantRepository;

import java.util.ArrayList;

/**
 * Created by Erik on 12-10-2016.
 */
public class InvoiceController {

    ICourseParticipantRepository courseParticipantRepository;

    public InvoiceController(ICourseParticipantRepository courseParticipantRepository){
        this.courseParticipantRepository = courseParticipantRepository;
    }

    public ArrayList<Invoice> getAllInvoices(int year, int weeknumber){

        ArrayList<CourseParticipant> participants = courseParticipantRepository.getAll();


        return null;
    }

}
