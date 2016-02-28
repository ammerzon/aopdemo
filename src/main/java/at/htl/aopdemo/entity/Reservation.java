package at.htl.aopdemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * User: simonammer
 * Date: 28.02.16
 */
@Entity
@Table(name = "AD_RESERVATION")
@XmlRootElement
public class Reservation implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  @Version
  @Column(name = "version")
  private int version;

  @Column(name = "email")
  private String email;

  @ManyToOne
  private Book book;


  public Reservation() {
  }

  public Reservation(String email, Book book) {
    this.email = email;
    this.book = book;
  }

  public Long getId() {
    return this.id;
  }

  public int getVersion() {
    return this.version;
  }

  public void setVersion(final int version) {
    this.version = version;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }
}
