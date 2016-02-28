package at.htl.aopdemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * User: simonammer
 * Date: 28.02.16
 */
@Entity
@Table(name = "AD_BOOK")
@XmlRootElement
public class Book implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  @Version
  @Column(name = "version")
  private int version;

  @Column(name = "title")
  private String title;

  @Column(name = "author")
  private String author;

  @Column(name = "pages")
  private Integer pages;

  public Book() {
  }

  public Book(String title, String author, Integer pages) {
    this.title = title;
    this.author = author;
    this.pages = pages;
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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Integer getPages() {
    return pages;
  }

  public void setPages(Integer pages) {
    this.pages = pages;
  }
}