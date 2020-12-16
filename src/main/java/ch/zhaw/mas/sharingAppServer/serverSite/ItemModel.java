package ch.zhaw.mas.sharingAppServer.serverSite;

import java.io.Serializable;
import java.util.Date;

//@Data
//@Entity

public class ItemModel implements Serializable {

    private String itemName;
    private Integer itemID;
    private String itemOwner;
    private String itemOwnerMail;
    private Date itemCreateDate;
    private Boolean itemAvailable;
    private Double itemRating;
    private String itemDescription;
    private Date itemLentFrom;
    private Date itemRentTill;

    public ItemModel() {
    }

    public ItemModel(String itemName,
                     Integer itemID,
                     String itemOwner,
                     String itemOwnerMail,
                     Date itemCreateDate,
                     Boolean  itemAvailable,
                     Double itemRating,
                     String itemDescription,
                     Date itemLentFrom,
                     Date itemRentTill) {

        this.itemName = itemName;
        this.itemID = itemID;
        this.itemOwner = itemOwner;
        this.itemOwnerMail = itemOwnerMail;
        this.itemCreateDate = itemCreateDate;
        this.itemAvailable = itemAvailable;
        this.itemRating = itemRating;
        this.itemDescription = itemDescription;
        this.itemLentFrom = itemLentFrom;
        this.itemRentTill = itemRentTill;
    }

    public ItemModel(ItemModel item) {

        this.itemName = itemName;
        this.itemID = itemID;
        this.itemOwner = itemOwner;
        this.itemOwnerMail = itemOwnerMail;
        this.itemCreateDate = itemCreateDate;
        this.itemAvailable = itemAvailable;
        this.itemRating = itemRating;
        this.itemDescription = itemDescription;
        this.itemLentFrom = itemLentFrom;
        this.itemRentTill = itemRentTill;

    }

    public void setId(String valueOf) {

       this.itemID = itemID;

    }
}
