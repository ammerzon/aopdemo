package at.htl.aopdemo.interfaces;

import at.htl.aopdemo.entity.Book;
import at.htl.aopdemo.entity.Reservation;

/**
 * User: simonammer
 * Date: 29.02.16
 */
public interface LoanService {
  Reservation borrowBook(Book book, String email, String name);

  void cancelReservation(Reservation reservation);
}
