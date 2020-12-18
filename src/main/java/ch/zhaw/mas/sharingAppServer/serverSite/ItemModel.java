package ch.zhaw.mas.sharingAppServer.serverSite;

import java.io.Serializable;
import java.util.Date;

//@Data
//@Entity

public class ItemModel implements Serializable {

    private String itemName;
    private int itemID;
    private String itemOwner;
    //private String itemOwnerMail;
    //private Boolean itemAvailable;
    //private Double itemRating;
    //private String itemDescription;
    private Date itemCreateDate;
    private Date itemLentFrom;
    //private int itemRentTill;

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

        this.itemName = item.itemName;
        this.itemID = item.itemID;
        this.itemOwner = item.itemOwner;
      //  this.itemOwnerMail = item.itemOwnerMail;
      //  this.itemAvailable = item.itemAvailable;
      //  this.itemRating = item.itemRating;
      //  this.itemDescription = item.itemDescription;
        this.itemCreateDate = new Date();
        this.itemLentFrom = item.itemLentFrom;
      //  this.itemRentTill = 17;

    }

    public String getItemName() {
        return itemName;
    }

    public int getItemID() {
        return itemID;
    }

    public String getItemOwner() {
        return itemOwner;
    }

    public Date getItemLentFrom() {
        return itemLentFrom;
    }

    public Date getItemCreateDate() {
        return itemCreateDate;
    }


 /*   public void setId(String valueOf) {

       this.itemID = itemID;

    }

  */
}
