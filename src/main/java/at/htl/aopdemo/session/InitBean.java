package at.htl.aopdemo.session;

import at.htl.aopdemo.entity.Book;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * User: simonammer
 * Date: 28.02.16
 */
@Startup
@Singleton
public class InitBean {

  @PersistenceContext
  private EntityManager em;

  @PostConstruct
  private void init() {
    Book book1 = new Book("Der Herr der Ringe - Die Gefährten", "J. R. R. Tolkien", 464);
    em.persist(book1);
    Book book2 = new Book("Black*Out", "Andreas Eschbach", 608);
    em.persist(book2);
    Book book3 = new Book("Tom Turbo: Die Ritterburg am Meeresgrund", "Thomas C. Brezina", 80);
    em.persist(book3);
  }
}
