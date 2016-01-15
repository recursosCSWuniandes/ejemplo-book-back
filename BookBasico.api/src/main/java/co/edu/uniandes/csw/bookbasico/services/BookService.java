package co.edu.uniandes.csw.bookbasico.services;

import co.edu.uniandes.csw.bookbasico.api.IBookLogic;
import co.edu.uniandes.csw.bookbasico.converters.BookConverter;
import co.edu.uniandes.csw.bookbasico.dtos.BookDTO;
import co.edu.uniandes.csw.auth.provider.StatusCreated;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 
@Path("/books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookService {

    @Inject
    private IBookLogic bookLogic;

    /**
     * Se encarga de crear un book en la base de datos
     *
     * @param dto Objeto de BookDTO con los datos nuevos
     * @return Objeto de BookDTO con los datos nuevos y su ID.
     */
    @POST
    @StatusCreated
    public BookDTO createBook(BookDTO dto) {
        return BookConverter.basicEntity2DTO(bookLogic.createBook(BookConverter.basicDTO2Entity(dto)));
    }

    /**
     * Obtiene la lista de los registros de Book
     *
     * @return Colección de objetos de BookDTO cada uno con sus respectivos
     * Review
     */
    @GET
    public List<BookDTO> getBooks() {
        return BookConverter.listEntity2DTO(bookLogic.getBooks());
    }

    /**
     * Obtiene los datos de una instancia de Book a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de BookDTO con los datos del Book consultado y sus
     * Review
     */
    @GET
    @Path("{id: \\d+}")
    public BookDTO getBook(@PathParam("id") Long id) {
        return BookConverter.basicEntity2DTO(bookLogic.getBook(id));
    }

    /**
     * Actualiza la información de una instancia de Book
     *
     * @param id Identificador de la instancia de Book a modificar
     * @param dto Instancia de BookDTO con los nuevos datos.
     * @return Instancia de BookDTO con los datos actualizados.
     */
    @PUT
    @Path("{id: \\d+}")
    public BookDTO updateBook(@PathParam("id") Long id, BookDTO dto) {
        dto.setId(id);
        return BookConverter.basicEntity2DTO(bookLogic.updateBook(BookConverter.basicDTO2Entity(dto)));
    }

    /**
     * Elimina una instancia de Book de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteBook(@PathParam("id") Long id) {
        bookLogic.deleteBook(id);
    }

}
