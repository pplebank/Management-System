package com.pplebank.backend.validator;

import com.pplebank.backend.model.Reservation;

import java.util.Date;
import java.util.List;

public class ReservationValidator {

    static public boolean checkIfDateIsInThePast(Reservation requested_reservation){
        Date now_date = new Date();
        Date requested_begin_date = requested_reservation.getStart_date();
        return requested_begin_date.after(now_date);
    };

    static public boolean checkIfBeginDateIsLaterThanEndDate(Reservation requested_reservation){
        Date requested_begin_date = requested_reservation.getStart_date();
        Date requested_end_date = requested_reservation.getEnd_date();
        return requested_begin_date.after(requested_end_date);
    };

    static public boolean checkIfReservationDateIsAvailable(List<Reservation> present_reservations, Reservation requested_reservation){
       Date requested_begin_date = requested_reservation.getStart_date();
       Date requested_end_date = requested_reservation.getEnd_date();

       for (Reservation present_reservation : present_reservations){
          Date present_begin_date = present_reservation.getStart_date();
          Date present_end_date = present_reservation.getEnd_date();

          if (requested_begin_date.before(present_begin_date) & requested_end_date.after(present_begin_date))
              return false;
           if (requested_begin_date.before(present_end_date) & requested_end_date.after(present_end_date))
               return false;
           if (requested_begin_date.after(present_begin_date) & requested_begin_date.before(present_end_date))
               return false;
           if (requested_begin_date.before(present_begin_date) & requested_end_date.after(present_end_date))
               return false;
       }
       return true;
    };
}
