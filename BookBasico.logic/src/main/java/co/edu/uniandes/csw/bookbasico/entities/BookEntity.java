package co.edu.uniandes.csw.bookbasico.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class BookEntity extends BaseEntity implements Serializable {

    private String isbn;

    private String image;

    private String description;
    
    @Temporal(TemporalType.DATE)
    private Date publishDate;

   
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

   

    @Override
    public boolean equals(Object obj) {
        return this.getId().equals(((BookEntity)obj).getId()); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
   
    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
