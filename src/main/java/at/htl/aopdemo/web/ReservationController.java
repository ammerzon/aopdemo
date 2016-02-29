package at.htl.aopdemo.web;

import at.htl.aopdemo.annotation.Logging;
import at.htl.aopdemo.annotation.Monitoring;
import at.htl.aopdemo.business.BookFacade;
import at.htl.aopdemo.business.ReservationFacade;
import at.htl.aopdemo.entity.Book;
import at.htl.aopdemo.entity.Reservation;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * User: simonammer
 * Date: 28.02.16
 */
@Named
@Monitoring
@ViewScoped
public class ReservationController implements Serializable {

  private List<Book> books;
  private Book selectedBook;
  private String email;

  @Inject
  private BookFacade bookFacade;

  @Inject
  private ReservationFacade reservationFacade;

  @PostConstruct
  private void init() {
    books = bookFacade.listAll();
  }

  public List<Book> getBooks() {
    return books;
  }

  public Book getSelectedBook() {
    return selectedBook;
  }

  public void setSelectedBook(Book selectedBook) {
    this.selectedBook = selectedBook;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void onRowSelect(SelectEvent event) {
    Book book = (Book) event.getObject();
    FacesMessage msg = new FacesMessage("Selected book", book.getTitle());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  @Logging
  public void createReservation() {
    Reservation reservation = new Reservation();
    reservation.setBook(selectedBook);
    reservation.setEmail(getEmail());
    reservationFacade.create(reservation);

    selectedBook = null;
    setEmail("");
    FacesMessage msg = new FacesMessage("Reservation saved");
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }
}
