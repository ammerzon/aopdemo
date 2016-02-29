package at.htl.aopdemo.business;

import at.htl.aopdemo.annotation.Logging;
import at.htl.aopdemo.entity.Book;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * User: simonammer
 * Date: 28.02.16
 */
@Stateless
@Logging(shouldLogToFile = true)
public class BookFacade {

  @PersistenceContext
  private EntityManager em;

  public void create(Book entity) {
    em.persist(entity);
  }

  public void deleteById(Long id) {
    Book entity = em.find(Book.class, id);
    if (entity != null) {
      em.remove(entity);
    }
  }

  public Book findById(Long id) {
    return em.find(Book.class, id);
  }

  public Book update(Book entity) {
    return em.merge(entity);
  }

  public List<Book> listAll() {
    return listAll(null, null);
  }

  public List<Book> listAll(Integer startPosition, Integer maxResult) {
    TypedQuery<Book> findAllQuery = em.createQuery("SELECT DISTINCT b FROM Book b ORDER BY b.id", Book.class);
    if (startPosition != null) {
      findAllQuery.setFirstResult(startPosition);
    }
    if (maxResult != null) {
      findAllQuery.setMaxResults(maxResult);
    }
    return findAllQuery.getResultList();
  }
}
