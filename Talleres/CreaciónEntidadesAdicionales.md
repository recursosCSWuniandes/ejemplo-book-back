## Creación entidades adicionales
Se agregaran 3 nuevas entidades: Author, Editorial y Review.
En la siguiente imagen se muestra las relaciones entre estas nuevas entidades y la entidad Book.

![Sin titulo](https://www.dropbox.com/s/26b2jxc3z9yatk5/BookBasico.png?dl=1)

Para agregar las nuevas entidades  debemos modificar los dos proyectos creados anteriormente.
-  [Proyecto Lógica]( #bookbasico-logic)
-  [Proyecto Api]( #bookbasico-api)

#### BookBasico Logic
##### Entidades
Se deben crean las nuevas entidades en el paquete “entities” con sus respectivos atributos y sus respectivas relaciones entre las entidades. Para explicar los nuevos conceptos vamos a realizar el ejemplo con “BookEntity”.

**Un Book tiene muchos Reviews y un Review tiene un Book**

Ya que un Book tiene muchas Reviews, se debe crear una lista de tipo “ReviewEntity” la cual debe estar anotada de la siguiente manera para su correcto funcionamiento:

```
@OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
private List<ReviewEntity> reviews;
```
El parámetro “mappedBy” indica que no es una relación nueva, sino que corresponde a una relación ya existente desde “ReviewEntity”, el parámetro “cascade” indica que todas las operaciones realizadas sobre “BookEntity” deben propagarse a los elementos de la relación, y por último el parámetro “orphanRemoval” indica que se debe eliminar toda instancia de “ReviewEntity” que no pertenezca a esta relación.

**Un Book tiene muchos Authors y un Author tiene muchos Books**

Un Book tiene muchos Authors, por lo tanto se debe crear una lista de “AuthorEntity” con la siguiente anotación:
```
@ManyToMany
private List<AuthorEntity> authors;
```
En JPA existe el concepto de "dueño de la relación". Es decir, cuando hay una relación bidireccional (navegable en ambos sentidos) se establece un dueño de la relación (quien crea la relación) y en la otra entidad se define una relación que depende de la ya existente a través de mappedBy.
En este caso, BookEntity es dueño de la anotación, por lo que no se asigna el parámetro mappedBy pero se debe definir en el otro extremo, es decir en la clase “AuthorEntity”.

**Un Book tiene una Editorial y una Editorial tiene muchos Books**

Ya que un Book solo puede tener una Editorial se debe crear un objeto de tipo “EditorialEntity” con su respectiva anotación.

```
@ManyToOne
private EditorialEntity editorial;
```

Este tipo de relación crea en la tabla “BookEntity” una llave foránea hacia “EditorialEntity”.


En el siguiente enlace se muestra como debe quedar la clase.
(enlace)

##### Persistencia 
Para la persistencia se debe hacer exactamente lo que se hizo en el anterior paso, solo que con las nuevas entidades.
##### Excepción
Se debe crear un nuevo paquete llamado “exceptions”, el cual tendrá una excepción personalizada la cual va a ser usada en métodos en los cuales existan más de una operación de persistencia, esta excepción básicamente funciona para hacer roll back en el caso de que algún error se presente.

##### Interface 
En el paquete “api” creamos las nuevas interfaces correspondientes a las nuevas entidades creadas.
En este caso se agregan nuevos métodos según las diferentes relaciones que tenga la entidad. En el caso de Book se agregan los siguientes métodos:

```
public AuthorEntity addAuthor(Long authorId, Long bookId) throws BusinessLogicException;    
public void removeAuthor(Long authorId, Long bookId);    
public List<AuthorEntity> replaceAuthors(List<AuthorEntity> authors, Long bookId) throws BusinessLogicException;    
public List<AuthorEntity> getAuthors(Long bookId);    
public AuthorEntity getAuthor(Long bookId, Long authorId);
```
Se puede ver que no se agregan métodos para su otras dos relaciones con Editorial y Review, esto se debe a que Editorial es un solo objeto, y ya viene dentro del objeto Book, en el caso de la lista de Review se considera que este se compone del objeto Book por lo tanto ya viene dentro de este.

En el siguiente enlace se muestra como debe quedar la interface.
(enlace)

##### Lógica
En el paquete “ejbs”, crearemos las nuevas clases correspondientes a las nuevas entidades agregadas y que deben implementar su respectivas interfaces para que así los nuevos métodos funciones e interactúen correctamente con la base de datos.
En el siguiente enlace se muestra como debe quedar la clase “BookLogic”.
(enlace)

#### BookBasico Api
##### DTOs
Se deben crean las nuevas clases en el paquete “dtos” con sus respectivos atributos y sus respectivas relaciones entre las entidades. 

En el siguiente enlace se muestra como debe quedar la clase “BookDTO”.
(enlace)
##### Converters
En el paquete “converters” se deben agregar las nuevas clases como se hizo en el paso anterior, con la diferencia que ahora se deben agregar las relaciones a los respectivos métodos.
En el siguiente enlace se muestra como debe quedar la clase “BookConverter”. 
(enlace)
##### Servicios
En el paquete “services” se deben agregar las nuevas clases con sus respectivos métodos como se realizó en el paso anterior, con la diferencia que se deben agregar los nuevos métodos de acuerdo a las relaciones existentes en cada entidad. Por ejemplo:
```
@POST
    @Path("{bookId: \\d+}/authors/{authorId: \\d+}")
    public AuthorDTO addAuthor(@PathParam("bookId") Long bookId, @PathParam("authorId") Long authorId) {
        try {
            return AuthorConverter.basicEntity2DTO(bookLogic.addAuthor(authorId, bookId));
        } catch (BusinessLogicException ex) {
            Logger.getLogger(BookService.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(ex, 409);
        }
    }
```
En el siguiente enlace se muestra como debe quedar la clase “BookService”.
(enlace)

