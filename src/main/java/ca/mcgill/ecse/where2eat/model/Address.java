/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse.where2eat.model;

// line 54 "../../../../../where2eat.ump"
public class Address
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Address Attributes
  private String postalCode;
  private String streetNumber;
  private String streetAddress;
  private String city;

  //Address Associations
  private Location restaurantLocation;
  private Where2Eat where2Eat;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Address(String aPostalCode, String aStreetNumber, String aStreetAddress, String aCity, Where2Eat aWhere2Eat)
  {
    postalCode = aPostalCode;
    streetNumber = aStreetNumber;
    streetAddress = aStreetAddress;
    city = aCity;
    boolean didAddWhere2Eat = setWhere2Eat(aWhere2Eat);
    if (!didAddWhere2Eat)
    {
      throw new RuntimeException("Unable to create address due to where2Eat");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPostalCode(String aPostalCode)
  {
    boolean wasSet = false;
    postalCode = aPostalCode;
    wasSet = true;
    return wasSet;
  }

  public boolean setStreetNumber(String aStreetNumber)
  {
    boolean wasSet = false;
    streetNumber = aStreetNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setStreetAddress(String aStreetAddress)
  {
    boolean wasSet = false;
    streetAddress = aStreetAddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setCity(String aCity)
  {
    boolean wasSet = false;
    city = aCity;
    wasSet = true;
    return wasSet;
  }

  public String getPostalCode()
  {
    return postalCode;
  }

  public String getStreetNumber()
  {
    return streetNumber;
  }

  public String getStreetAddress()
  {
    return streetAddress;
  }

  public String getCity()
  {
    return city;
  }

  public Location getRestaurantLocation()
  {
    return restaurantLocation;
  }

  public boolean hasRestaurantLocation()
  {
    boolean has = restaurantLocation != null;
    return has;
  }

  public Where2Eat getWhere2Eat()
  {
    return where2Eat;
  }

  public boolean setRestaurantLocation(Location aNewRestaurantLocation)
  {
    boolean wasSet = false;
    restaurantLocation = aNewRestaurantLocation;
    wasSet = true;
    return wasSet;
  }

  public boolean setWhere2Eat(Where2Eat aWhere2Eat)
  {
    boolean wasSet = false;
    if (aWhere2Eat == null)
    {
      return wasSet;
    }

    Where2Eat existingWhere2Eat = where2Eat;
    where2Eat = aWhere2Eat;
    if (existingWhere2Eat != null && !existingWhere2Eat.equals(aWhere2Eat))
    {
      existingWhere2Eat.removeAddress(this);
    }
    where2Eat.addAddress(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    restaurantLocation = null;
    Where2Eat placeholderWhere2Eat = where2Eat;
    this.where2Eat = null;
    if(placeholderWhere2Eat != null)
    {
      placeholderWhere2Eat.removeAddress(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "postalCode" + ":" + getPostalCode()+ "," +
            "streetNumber" + ":" + getStreetNumber()+ "," +
            "streetAddress" + ":" + getStreetAddress()+ "," +
            "city" + ":" + getCity()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "restaurantLocation = "+(getRestaurantLocation()!=null?Integer.toHexString(System.identityHashCode(getRestaurantLocation())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "where2Eat = "+(getWhere2Eat()!=null?Integer.toHexString(System.identityHashCode(getWhere2Eat())):"null");
  }
}