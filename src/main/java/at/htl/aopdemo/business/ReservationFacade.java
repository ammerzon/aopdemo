package at.htl.aopdemo.business;

import at.htl.aopdemo.annotation.Logging;
import at.htl.aopdemo.entity.Reservation;

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
@Logging
public class ReservationFacade {

  @PersistenceContext
  private EntityManager em;

  public void create(Reservation entity) {
    em.persist(entity);
  }

  public void deleteById(Long id) {
    Reservation entity = em.find(Reservation.class, id);
    if (entity != null) {
      em.remove(entity);
    }
  }

  public Reservation findById(Long id) {
    return em.find(Reservation.class, id);
  }

  public Reservation update(Reservation entity) {
    return em.merge(entity);
  }

  public List<Reservation> listAll() {
    return listAll(null, null);
  }

  public List<Reservation> listAll(Integer startPosition, Integer maxResult) {
    TypedQuery<Reservation> findAllQuery = em.createQuery("SELECT DISTINCT r FROM Reservation r ORDER BY r.id", Reservation.class);
    if (startPosition != null) {
      findAllQuery.setFirstResult(startPosition);
    }
    if (maxResult != null) {
      findAllQuery.setMaxResults(maxResult);
    }
    return findAllQuery.getResultList();
  }
}
