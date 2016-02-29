package at.htl.aopdemo.business;

import at.htl.aopdemo.entity.Book;
import at.htl.aopdemo.entity.Reservation;
import at.htl.aopdemo.interfaces.LoanService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * User: simonammer
 * Date: 29.02.16
 */
@Stateless
public class LoanServiceImpl implements LoanService {

  @PersistenceContext
  private EntityManager em;

  @Override
  public Reservation borrowBook(Book book, String email, String name) {
    Reservation reservation = new Reservation(book, email, name);
    em.persist(reservation);
    return reservation;
  }

  @Override
  public void cancelReservation(Reservation reservation) {
    Reservation entity = em.find(Reservation.class, reservation.getId());
    if (entity != null) {
      em.remove(entity);
    }
  }
}
