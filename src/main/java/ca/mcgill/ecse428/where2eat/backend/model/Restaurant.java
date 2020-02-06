/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4262.30c9ffc7c modeling language!*/

package ca.mcgill.ecse428.where2eat.backend.model;
import java.util.*;

// line 42 "../../../../../../../../../src/main/java/where2eat.ump"
public class Restaurant
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Restaurant Attributes
  private String name;
  private Set<Category> categories;
  private String restaurantID;

  //Restaurant Associations
  private Where2Eat where2Eat;
  private Address restaurantAddress;
  private PhoneNumber phoneNumber;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Restaurant(String aName, Set<Category> aCategories, String aRestaurantID, Where2Eat aWhere2Eat)
  {
    name = aName;
    categories = aCategories;
    restaurantID = aRestaurantID;
    boolean didAddWhere2Eat = setWhere2Eat(aWhere2Eat);
    if (!didAddWhere2Eat)
    {
      throw new RuntimeException("Unable to create restaurant due to where2Eat");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setCategories(Set<Category> aCategories)
  {
    boolean wasSet = false;
    categories = aCategories;
    wasSet = true;
    return wasSet;
  }

  public boolean setRestaurantID(String aRestaurantID)
  {
    boolean wasSet = false;
    restaurantID = aRestaurantID;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public Set<Category> getCategories()
  {
    return categories;
  }

  public String getRestaurantID()
  {
    return restaurantID;
  }
  /* Code from template association_GetOne */
  public Where2Eat getWhere2Eat()
  {
    return where2Eat;
  }
  /* Code from template association_GetOne */
  public Address getRestaurantAddress()
  {
    return restaurantAddress;
  }

  public boolean hasRestaurantAddress()
  {
    boolean has = restaurantAddress != null;
    return has;
  }
  /* Code from template association_GetOne */
  public PhoneNumber getPhoneNumber()
  {
    return phoneNumber;
  }

  public boolean hasPhoneNumber()
  {
    boolean has = phoneNumber != null;
    return has;
  }
  /* Code from template association_SetOneToMany */
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
      existingWhere2Eat.removeRestaurant(this);
    }
    where2Eat.addRestaurant(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setRestaurantAddress(Address aNewRestaurantAddress)
  {
    boolean wasSet = false;
    restaurantAddress = aNewRestaurantAddress;
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setPhoneNumber(PhoneNumber aNewPhoneNumber)
  {
    boolean wasSet = false;
    phoneNumber = aNewPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Where2Eat placeholderWhere2Eat = where2Eat;
    this.where2Eat = null;
    if(placeholderWhere2Eat != null)
    {
      placeholderWhere2Eat.removeRestaurant(this);
    }
    restaurantAddress = null;
    phoneNumber = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "restaurantID" + ":" + getRestaurantID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "categories" + "=" + (getCategories() != null ? !getCategories().equals(this)  ? getCategories().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "where2Eat = "+(getWhere2Eat()!=null?Integer.toHexString(System.identityHashCode(getWhere2Eat())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "restaurantAddress = "+(getRestaurantAddress()!=null?Integer.toHexString(System.identityHashCode(getRestaurantAddress())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "phoneNumber = "+(getPhoneNumber()!=null?Integer.toHexString(System.identityHashCode(getPhoneNumber())):"null");
  }
}