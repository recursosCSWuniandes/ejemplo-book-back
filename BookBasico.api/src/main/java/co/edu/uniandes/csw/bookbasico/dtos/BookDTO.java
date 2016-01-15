package co.edu.uniandes.csw.bookbasico.dtos;

import co.edu.uniandes.csw.auth.model.DateAdapter;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * @generated
 */
@XmlRootElement
public class BookDTO {

    private Long id;
    private String name;
    private String isbn;
    private String image;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date publishDate;
    private String description;

    
    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    
    public String getIsbn() {
        return isbn;
    }

    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    
    public String getImage() {
        return image;
    }

    
    public void setImage(String image) {
        this.image = image;
    }

    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
