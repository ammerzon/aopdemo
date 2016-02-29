package at.htl.aopdemo.rest;

import at.htl.aopdemo.annotation.Logging;
import at.htl.aopdemo.annotation.Monitoring;
import at.htl.aopdemo.business.BookFacade;
import at.htl.aopdemo.entity.Book;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import java.util.List;

/**
 * User: simonammer
 * Date: 28.02.16
 */
@Stateless
@Path("book")
@Monitoring
@Logging
public class BookEndpoint {

  @Inject
  private BookFacade bookFacade;

  @POST
  @Consumes({"application/json", "application/xml"})
  public Response create(Book entity) {
    bookFacade.create(entity);
    return Response.created(UriBuilder.fromResource(BookEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
  }

  @DELETE
  @Path("/{id:[0-9][0-9]*}")
  public Response deleteById(@PathParam("id") Long id) {
    Book entity = bookFacade.findById(id);
    if (entity == null) {
      return Response.status(Status.NOT_FOUND).build();
    }
    bookFacade.deleteById(id);
    return Response.noContent().build();
  }

  @GET
  @Path("/{id:[0-9][0-9]*}")
  @Produces({"application/json", "application/xml"})
  public Response findById(@PathParam("id") Long id) {
    Book entity = bookFacade.findById(id);
    if (entity == null) {
      return Response.status(Status.NOT_FOUND).build();
    }
    return Response.ok(entity).build();
  }

  @GET
  @Produces({"application/json", "application/xml"})
  public List<Book> listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult) {
    return bookFacade.listAll(startPosition, maxResult);
  }

  @PUT
  @Path("/{id:[0-9][0-9]*}")
  @Consumes({"application/json", "application/xml"})
  public Response update(@PathParam("id") Long id, Book entity) {
    if (entity == null) {
      return Response.status(Status.BAD_REQUEST).build();
    }
    if (id == null) {
      return Response.status(Status.BAD_REQUEST).build();
    }
    if (!id.equals(entity.getId())) {
      return Response.status(Status.CONFLICT).entity(entity).build();
    }
    if (bookFacade.findById(id) == null) {
      return Response.status(Status.NOT_FOUND).build();
    }
    try {
      entity = bookFacade.update(entity);
    } catch (OptimisticLockException e) {
      return Response.status(Response.Status.CONFLICT).entity(e.getEntity()).build();
    }

    return Response.noContent().build();
  }
}
