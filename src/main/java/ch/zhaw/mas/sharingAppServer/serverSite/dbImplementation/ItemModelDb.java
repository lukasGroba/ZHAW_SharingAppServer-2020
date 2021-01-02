package ch.zhaw.mas.sharingAppServer.serverSite.domain;

import ch.zhaw.mas.sharingAppServer.serverSite.dbImplementation.UserModelDb;
import springfox.documentation.service.ApiListing;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

//@Data
@Entity
@Table(name = "ITEM")
public class ItemModelDb {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;
    private LocalDate dateCreated;
    private String description;
    private boolean isLent;
    private Double rating;
    @ManyToOne
    private UserModelDb owner;
    //private String mail;
    private LocalDate lentFrom;
    private LocalDate rentTill;

    public ItemModelDb() {
    }

    public ItemModelDb(ItemModelDb item) {

        int id; //number
        String name;
        Date dateCreated;
        String description;
        boolean isLent;
        Double rating;
        UserModel owner;
        //String mail;
        LocalDate lentFrom;
        LocalDate rentTill;

        this.id = item.id;
        this.name = item.name;
        this.dateCreated = LocalDate.now();
        this.description = item.description;
        this.isLent = item.isLent;
        this.rating = item.rating;
        this.owner = item.owner;
        //this.mail = item.mail;
        this.lentFrom = item.lentFrom;
        this.rentTill = item.rentTill;

    }

    @Id
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsLent() {
        return isLent;
    }

    public Double getRating() {
        return rating;
    }

    public UserModelDb getOwner() {
        return owner;
    }

    /*
    public String getMail() {
        return mail;
    }
    */

    public LocalDate getLentFrom() {
        return lentFrom;
    }

    public LocalDate getRentTill() {
        return rentTill;
    }

    public void setHighestId(int highestId) {
        this.id = highestId;
    }

    public String getMailFromOwner(ItemModel item){
        UserModel user = item.getOwner();
        String mailFromOwner = user.getMail();
        return mailFromOwner;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }
}

