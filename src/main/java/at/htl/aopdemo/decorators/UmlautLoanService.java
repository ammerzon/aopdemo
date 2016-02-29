package at.htl.aopdemo.decorators;

import at.htl.aopdemo.entity.Book;
import at.htl.aopdemo.entity.Reservation;
import at.htl.aopdemo.interfaces.LoanService;

import javax.annotation.Priority;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.interceptor.Interceptor;

/**
 * User: simonammer
 * Date: 29.02.16
 */
@Decorator
@Priority(Interceptor.Priority.APPLICATION + 300)
public abstract class UmlautLoanService implements LoanService {

  @Inject
  @Delegate
  @Any
  private LoanService loanService;

  @Override
  public Reservation borrowBook(Book book, String email, String name) {
    String replacedName = replaceUmlaut(name);
    return loanService.borrowBook(book, email, replacedName);
  }

  private static String replaceUmlaut(String input) {
    //replace all lower Umlauts
    String output = input.replace("ü", "ue")
                         .replace("ö", "oe")
                         .replace("ä", "ae")
                         .replace("ß", "ss");

    //first replace all capital umlaute in a non-capitalized context (e.g. Übung)
    output = output.replace("Ü(?=[a-zäöüß ])", "Ue")
                   .replace("Ö(?=[a-zäöüß ])", "Oe")
                   .replace("Ä(?=[a-zäöüß ])", "Ae");

    //now replace all the other capital umlaute
    output = output.replace("Ü", "UE")
                   .replace("Ö", "OE")
                   .replace("Ä", "AE");

    return output;
  }
}
