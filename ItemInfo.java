/**
 *
 * The ItemInfo class is an object which stores the various information held by the
 * ItemInfoNode object. Among this information is the item's name, price, the rfid tag number,
 * the original location of the item as well as where the item's located. As well as this
 * information, the ItemInfo class holds all of the accessor and mutator methods pertaining
 * to these variables.
 *
 *
 * */

public class ItemInfo {


    /**
     * Here, we instantiate the prodName, prodPrice, rfidTagNumber,
     * originLocation, currLocation.
     */
    private String prodName;
    private double prodPrice;

    /*Check that it's 9 char long and if so convert to
    base 16. String length fixed at 9.
    */
    private String rfidTagNumber;

    /*
        First char is always s, so concatenate the user input.
        String length fixed at 6.
     */
    private String originLocation;

    /*
        May be a shelf position, cart (starts with a c and 3 digit number)
        or it may have been checked out, in which currLoc = out.
     */
    private String currLocation;

    /**
     * Default constructor of the ItemInfo class.
     */
    public ItemInfo() {

    }

    /**
     * The ItemInfo method takes in the name, price, rfid number, original and current location of the
     * item, and sets the variables to the given values.
     * @param name
     * @param price
     * @param rfid
     * @param original
     * @param curr
     */
    public ItemInfo(String name, double price, String rfid, String original, String curr) {
        this.prodName = name;
        this.prodPrice = price;
        this.rfidTagNumber = rfid;
        this.originLocation = original;
        this.currLocation = curr;
    }

    /**
     * The setProdName method takes in a value; prodName, and sets the prodName variable equal to
     * the given variable.
     * @param prodName
     */
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    /**
     * The setProdPrice method takes in a value; prodPrice, and sets the prodPrice variable equal
     * to the given variable.
     * @param prodPrice
     */
    public void setProdPrice(double prodPrice) {
        this.prodPrice = prodPrice;
    }

    /**
     * The setRfidTagNumber method takes in a value; rfidTagNumber, and sets the rfidTagNumber
     * variable equal to the given variable.
     * @param rfidTagNumber
     */
    public void setRfidTagNumber(String rfidTagNumber) {
        this.rfidTagNumber = rfidTagNumber;
    }

    /**
     * The setOriginLocation method takes in a value; originLocation, and sets the originLocation
     * variable equal to the given variable for later use.
     * @param originLocation
     */
    public void setOriginLocation(String originLocation) {
        this.originLocation = originLocation;
    }

    /**
     * The setCurrLocation method takes in a value; currLocation, and sets the currLocation
     * variable equal to the given variable for later use.
     * @param currLocation
     */
    public void setCurrLocation(String currLocation) {
        this.currLocation = currLocation;
    }

    /**
     * The getProdName method returns the previously set prodName variable.
     * @return
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * The getOriginLocation method returns the previously set originLocation variable.
     * @return
     */
    public String getOriginLocation() {
        return originLocation;
    }

    /**
     * The getProdPrice method returns the previously set prodPrice variable.
     * @return
     */
    public double getProdPrice() {
        return prodPrice;
    }

    /**
     * The getRfidTagNumber method returns the previously set rfidTagNumber variable.
     * @return
     */
    public String getRfidTagNumber() {
        return rfidTagNumber;
    }

    /**
     * The getCurrLocation method returns the previously set currLocation variable.
     * @return
     */
    public String getCurrLocation() {
        return currLocation;
    }
}
