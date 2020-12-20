package ch.zhaw.mas.sharingAppServer.serverSite;

import ch.zhaw.mas.sharingAppServer.firstTestProjectSetup.controller.User;

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
    private User owner;
    private LocalDate lentFrom;
    private LocalDate rentTill;

    public ItemModel() {
    }

/*    public ItemModel(String itemName,
                     int itemID,
                     String itemOwner
                     //String itemOwnerMail,
                     //Boolean  itemAvailable,
                     //Double itemRating,
                     //String itemDescription,
                     //int itemCreateDate,
                     //int itemLentFrom,
                     //int itemRentTill
    ) {

        this.itemName = itemName;
        this.itemID = itemID;
        this.itemOwner = itemOwner;
        //this.itemOwnerMail = itemOwnerMail;
        //this.itemAvailable = itemAvailable;
        //this.itemRating = itemRating;
        //this.itemDescription = itemDescription;
        //this.itemCreateDate = itemCreateDate;
        //this.itemLentFrom = itemLentFrom;
        //this.itemRentTill = itemRentTill;
    }

 */

    public ItemModel(ItemModel item) {

        int id; //number
        String name;
        Date dateCreated;
        String description;
        boolean isLent;
        Double rating;
        User owner;
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

    public User getOwner() {
        return owner;
    }

    public LocalDate getLentFrom() {
        return lentFrom;
    }

    public LocalDate getRentTill() {
        return rentTill;
    }

}
