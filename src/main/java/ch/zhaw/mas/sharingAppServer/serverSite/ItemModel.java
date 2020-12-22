package ch.zhaw.mas.sharingAppServer.serverSite;

//import ch.zhaw.mas.sharingAppServer.firstTestProjectSetup.controller.User;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

//@Data
//@Entity

public class ItemModel implements Serializable {

    private int id;
    private String name;
    private Date dateCreated;
    private String description;
    private boolean isLent;
    private Double rating;
    private UserModel owner;
    private LocalDate lentFrom;
    private LocalDate rentTill;

    public ItemModel() {
    }

    public ItemModel(ItemModel item) {

        int id; //number
        String name;
        Date dateCreated;
        String description;
        boolean isLent;
        Double rating;
        UserModel owner;
        LocalDate lentFrom;
        LocalDate rentTill;

        this.id = item.id;
        this.name = item.name;
        this.dateCreated = new Date();
        this.description = item.description;
        this.isLent = item.isLent;
        this.rating = item.rating;
        this.owner = item.owner;
        this.lentFrom = item.lentFrom;
        this.rentTill = item.rentTill;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDateCreated() {
        return dateCreated;
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

    public UserModel getOwner() {
        return owner;
    }

    public LocalDate getLentFrom() {
        return lentFrom;
    }

    public LocalDate getRentTill() {
        return rentTill;
    }

    public void setHighestId(int highestId) {
        this.id = highestId;
    }
}
