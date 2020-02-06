/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse428.where2eat.backend.model;
import java.util.*;

import javax.persistence.*;

import java.util.*;

// line 4 "../../../../../../where2eat.ump"
@Entity
public class Where2Eat
{
  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Where2Eat Attributes
  @Id
  private int systemID;
	
  //Where2Eat Associations
  @OneToMany(cascade={CascadeType.ALL})
  private List<User> users;
  @OneToMany(cascade={CascadeType.ALL})
  private List<UserLogin> loginIDs;
  @OneToMany(cascade={CascadeType.ALL})
  private List<Group> groups;
  @OneToMany(cascade={CascadeType.ALL})
  private List<Restaurant> restaurants;
  @OneToMany(cascade={CascadeType.ALL})
  private List<Location> locations;
  @OneToMany(cascade={CascadeType.ALL})
  private List<Address> addresses;
  @OneToMany(cascade={CascadeType.ALL})
  private List<PhoneNumber> phoneNumbers;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Where2Eat()
  {
    users = new ArrayList<User>();
    loginIDs = new ArrayList<UserLogin>();
    groups = new ArrayList<Group>();
    restaurants = new ArrayList<Restaurant>();
    locations = new ArrayList<Location>();
    addresses = new ArrayList<Address>();
    phoneNumbers = new ArrayList<PhoneNumber>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  
  public boolean setSystemID(int aSystemID)
  {
    boolean wasSet = false;
    systemID = aSystemID;
    wasSet = true;
    return wasSet;
  }

  public User getUser(int index)
  {
    User aUser = users.get(index);
    return aUser;
  }

  public List<User> getUsers()
  {
    List<User> newUsers = Collections.unmodifiableList(users);
    return newUsers;
  }

  public int numberOfUsers()
  {
    int number = users.size();
    return number;
  }

  public boolean hasUsers()
  {
    boolean has = users.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = users.indexOf(aUser);
    return index;
  }
  
  public int getSystemID() {
	  return systemID;
  }

  public UserLogin getLoginID(int index)
  {
    UserLogin aLoginID = loginIDs.get(index);
    return aLoginID;
  }

  public List<UserLogin> getLoginIDs()
  {
    List<UserLogin> newLoginIDs = Collections.unmodifiableList(loginIDs);
    return newLoginIDs;
  }

  public int numberOfLoginIDs()
  {
    int number = loginIDs.size();
    return number;
  }

  public boolean hasLoginIDs()
  {
    boolean has = loginIDs.size() > 0;
    return has;
  }

  public int indexOfLoginID(UserLogin aLoginID)
  {
    int index = loginIDs.indexOf(aLoginID);
    return index;
  }

  public Group getGroup(int index)
  {
    Group aGroup = groups.get(index);
    return aGroup;
  }

  public List<Group> getGroups()
  {
    List<Group> newGroups = Collections.unmodifiableList(groups);
    return newGroups;
  }

  public int numberOfGroups()
  {
    int number = groups.size();
    return number;
  }

  public boolean hasGroups()
  {
    boolean has = groups.size() > 0;
    return has;
  }

  public int indexOfGroup(Group aGroup)
  {
    int index = groups.indexOf(aGroup);
    return index;
  }

  public Restaurant getRestaurant(int index)
  {
    Restaurant aRestaurant = restaurants.get(index);
    return aRestaurant;
  }

  public List<Restaurant> getRestaurants()
  {
    List<Restaurant> newRestaurants = Collections.unmodifiableList(restaurants);
    return newRestaurants;
  }

  public int numberOfRestaurants()
  {
    int number = restaurants.size();
    return number;
  }

  public boolean hasRestaurants()
  {
    boolean has = restaurants.size() > 0;
    return has;
  }

  public int indexOfRestaurant(Restaurant aRestaurant)
  {
    int index = restaurants.indexOf(aRestaurant);
    return index;
  }

  public Location getLocation(int index)
  {
    Location aLocation = locations.get(index);
    return aLocation;
  }

  public List<Location> getLocations()
  {
    List<Location> newLocations = Collections.unmodifiableList(locations);
    return newLocations;
  }

  public int numberOfLocations()
  {
    int number = locations.size();
    return number;
  }

  public boolean hasLocations()
  {
    boolean has = locations.size() > 0;
    return has;
  }

  public int indexOfLocation(Location aLocation)
  {
    int index = locations.indexOf(aLocation);
    return index;
  }

  public Address getAddress(int index)
  {
    Address aAddress = addresses.get(index);
    return aAddress;
  }

  public List<Address> getAddresses()
  {
    List<Address> newAddresses = Collections.unmodifiableList(addresses);
    return newAddresses;
  }

  public int numberOfAddresses()
  {
    int number = addresses.size();
    return number;
  }

  public boolean hasAddresses()
  {
    boolean has = addresses.size() > 0;
    return has;
  }

  public int indexOfAddress(Address aAddress)
  {
    int index = addresses.indexOf(aAddress);
    return index;
  }

  public PhoneNumber getPhoneNumber(int index)
  {
    PhoneNumber aPhoneNumber = phoneNumbers.get(index);
    return aPhoneNumber;
  }

  public List<PhoneNumber> getPhoneNumbers()
  {
    List<PhoneNumber> newPhoneNumbers = Collections.unmodifiableList(phoneNumbers);
    return newPhoneNumbers;
  }

  public int numberOfPhoneNumbers()
  {
    int number = phoneNumbers.size();
    return number;
  }

  public boolean hasPhoneNumbers()
  {
    boolean has = phoneNumbers.size() > 0;
    return has;
  }

  public int indexOfPhoneNumber(PhoneNumber aPhoneNumber)
  {
    int index = phoneNumbers.indexOf(aPhoneNumber);
    return index;
  }

  public static int minimumNumberOfUsers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public User addUser(UserRole aRole, String aFirstName, String aLastName, int aUserID, Set<Category> aCategories, UserLogin aUserLogin)
  {
    return new User(aRole, aFirstName, aLastName, aUserID, aCategories, this, aUserLogin);
  }

  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }
    Where2Eat existingWhere2Eat = aUser.getWhere2Eat();
    boolean isNewWhere2Eat = existingWhere2Eat != null && !this.equals(existingWhere2Eat);
    if (isNewWhere2Eat)
    {
      aUser.setWhere2Eat(this);
    }
    else
    {
      users.add(aUser);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    //Unable to remove aUser, as it must always have a where2Eat
    if (!this.equals(aUser.getWhere2Eat()))
    {
      users.remove(aUser);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addUserAt(User aUser, int index)
  {  
    boolean wasAdded = false;
    if(addUser(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(users.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfLoginIDs()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public UserLogin addLoginID(String aUserName, String aPassword)
  {
    return new UserLogin(aUserName, aPassword, this);
  }

  public boolean addLoginID(UserLogin aLoginID)
  {
    boolean wasAdded = false;
    if (loginIDs.contains(aLoginID)) { return false; }
    Where2Eat existingWhere2Eat = aLoginID.getWhere2Eat();
    boolean isNewWhere2Eat = existingWhere2Eat != null && !this.equals(existingWhere2Eat);
    if (isNewWhere2Eat)
    {
      aLoginID.setWhere2Eat(this);
    }
    else
    {
      loginIDs.add(aLoginID);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLoginID(UserLogin aLoginID)
  {
    boolean wasRemoved = false;
    //Unable to remove aLoginID, as it must always have a where2Eat
    if (!this.equals(aLoginID.getWhere2Eat()))
    {
      loginIDs.remove(aLoginID);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addLoginIDAt(UserLogin aLoginID, int index)
  {  
    boolean wasAdded = false;
    if(addLoginID(aLoginID))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLoginIDs()) { index = numberOfLoginIDs() - 1; }
      loginIDs.remove(aLoginID);
      loginIDs.add(index, aLoginID);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLoginIDAt(UserLogin aLoginID, int index)
  {
    boolean wasAdded = false;
    if(loginIDs.contains(aLoginID))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLoginIDs()) { index = numberOfLoginIDs() - 1; }
      loginIDs.remove(aLoginID);
      loginIDs.add(index, aLoginID);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLoginIDAt(aLoginID, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfGroups()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Group addGroup(String aName, String aGroupID, User... allUsers)
  {
    return new Group(aName, aGroupID, this, allUsers);
  }

  public boolean addGroup(Group aGroup)
  {
    boolean wasAdded = false;
    if (groups.contains(aGroup)) { return false; }
    Where2Eat existingWhere2Eat = aGroup.getWhere2Eat();
    boolean isNewWhere2Eat = existingWhere2Eat != null && !this.equals(existingWhere2Eat);
    if (isNewWhere2Eat)
    {
      aGroup.setWhere2Eat(this);
    }
    else
    {
      groups.add(aGroup);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGroup(Group aGroup)
  {
    boolean wasRemoved = false;
    //Unable to remove aGroup, as it must always have a where2Eat
    if (!this.equals(aGroup.getWhere2Eat()))
    {
      groups.remove(aGroup);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addGroupAt(Group aGroup, int index)
  {  
    boolean wasAdded = false;
    if(addGroup(aGroup))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGroups()) { index = numberOfGroups() - 1; }
      groups.remove(aGroup);
      groups.add(index, aGroup);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGroupAt(Group aGroup, int index)
  {
    boolean wasAdded = false;
    if(groups.contains(aGroup))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGroups()) { index = numberOfGroups() - 1; }
      groups.remove(aGroup);
      groups.add(index, aGroup);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGroupAt(aGroup, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfRestaurants()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Restaurant addRestaurant(String aName, Set<Category> aCategories, String aRestaurantID)
  {
    return new Restaurant(aName, aCategories, aRestaurantID, this);
  }

  public boolean addRestaurant(Restaurant aRestaurant)
  {
    boolean wasAdded = false;
    if (restaurants.contains(aRestaurant)) { return false; }
    Where2Eat existingWhere2Eat = aRestaurant.getWhere2Eat();
    boolean isNewWhere2Eat = existingWhere2Eat != null && !this.equals(existingWhere2Eat);
    if (isNewWhere2Eat)
    {
      aRestaurant.setWhere2Eat(this);
    }
    else
    {
      restaurants.add(aRestaurant);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRestaurant(Restaurant aRestaurant)
  {
    boolean wasRemoved = false;
    //Unable to remove aRestaurant, as it must always have a where2Eat
    if (!this.equals(aRestaurant.getWhere2Eat()))
    {
      restaurants.remove(aRestaurant);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addRestaurantAt(Restaurant aRestaurant, int index)
  {  
    boolean wasAdded = false;
    if(addRestaurant(aRestaurant))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRestaurants()) { index = numberOfRestaurants() - 1; }
      restaurants.remove(aRestaurant);
      restaurants.add(index, aRestaurant);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRestaurantAt(Restaurant aRestaurant, int index)
  {
    boolean wasAdded = false;
    if(restaurants.contains(aRestaurant))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRestaurants()) { index = numberOfRestaurants() - 1; }
      restaurants.remove(aRestaurant);
      restaurants.add(index, aRestaurant);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRestaurantAt(aRestaurant, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfLocations()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Location addLocation(double aXCoordinate, double aYCoordinate, int aLocationID)
  {
    return new Location(aXCoordinate, aYCoordinate, aLocationID, this);
  }

  public boolean addLocation(Location aLocation)
  {
    boolean wasAdded = false;
    if (locations.contains(aLocation)) { return false; }
    Where2Eat existingWhere2Eat = aLocation.getWhere2Eat();
    boolean isNewWhere2Eat = existingWhere2Eat != null && !this.equals(existingWhere2Eat);
    if (isNewWhere2Eat)
    {
      aLocation.setWhere2Eat(this);
    }
    else
    {
      locations.add(aLocation);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLocation(Location aLocation)
  {
    boolean wasRemoved = false;
    //Unable to remove aLocation, as it must always have a where2Eat
    if (!this.equals(aLocation.getWhere2Eat()))
    {
      locations.remove(aLocation);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addLocationAt(Location aLocation, int index)
  {  
    boolean wasAdded = false;
    if(addLocation(aLocation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLocations()) { index = numberOfLocations() - 1; }
      locations.remove(aLocation);
      locations.add(index, aLocation);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLocationAt(Location aLocation, int index)
  {
    boolean wasAdded = false;
    if(locations.contains(aLocation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLocations()) { index = numberOfLocations() - 1; }
      locations.remove(aLocation);
      locations.add(index, aLocation);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLocationAt(aLocation, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfAddresses()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Address addAddress(String aPostalCode, String aStreetNumber, String aStreetAddress, String aCity, int aAddressID)
  {
    return new Address(aPostalCode, aStreetNumber, aStreetAddress, aCity, aAddressID, this);
  }

  public boolean addAddress(Address aAddress)
  {
    boolean wasAdded = false;
    if (addresses.contains(aAddress)) { return false; }
    Where2Eat existingWhere2Eat = aAddress.getWhere2Eat();
    boolean isNewWhere2Eat = existingWhere2Eat != null && !this.equals(existingWhere2Eat);
    if (isNewWhere2Eat)
    {
      aAddress.setWhere2Eat(this);
    }
    else
    {
      addresses.add(aAddress);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAddress(Address aAddress)
  {
    boolean wasRemoved = false;
    //Unable to remove aAddress, as it must always have a where2Eat
    if (!this.equals(aAddress.getWhere2Eat()))
    {
      addresses.remove(aAddress);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addAddressAt(Address aAddress, int index)
  {  
    boolean wasAdded = false;
    if(addAddress(aAddress))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAddresses()) { index = numberOfAddresses() - 1; }
      addresses.remove(aAddress);
      addresses.add(index, aAddress);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAddressAt(Address aAddress, int index)
  {
    boolean wasAdded = false;
    if(addresses.contains(aAddress))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAddresses()) { index = numberOfAddresses() - 1; }
      addresses.remove(aAddress);
      addresses.add(index, aAddress);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAddressAt(aAddress, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfPhoneNumbers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public PhoneNumber addPhoneNumber(String aAreaCode, String aLocalNumber)
  {
    return new PhoneNumber(aAreaCode, aLocalNumber, this);
  }

  public boolean addPhoneNumber(PhoneNumber aPhoneNumber)
  {
    boolean wasAdded = false;
    if (phoneNumbers.contains(aPhoneNumber)) { return false; }
    Where2Eat existingWhere2Eat = aPhoneNumber.getWhere2Eat();
    boolean isNewWhere2Eat = existingWhere2Eat != null && !this.equals(existingWhere2Eat);
    if (isNewWhere2Eat)
    {
      aPhoneNumber.setWhere2Eat(this);
    }
    else
    {
      phoneNumbers.add(aPhoneNumber);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePhoneNumber(PhoneNumber aPhoneNumber)
  {
    boolean wasRemoved = false;
    //Unable to remove aPhoneNumber, as it must always have a where2Eat
    if (!this.equals(aPhoneNumber.getWhere2Eat()))
    {
      phoneNumbers.remove(aPhoneNumber);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addPhoneNumberAt(PhoneNumber aPhoneNumber, int index)
  {  
    boolean wasAdded = false;
    if(addPhoneNumber(aPhoneNumber))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPhoneNumbers()) { index = numberOfPhoneNumbers() - 1; }
      phoneNumbers.remove(aPhoneNumber);
      phoneNumbers.add(index, aPhoneNumber);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePhoneNumberAt(PhoneNumber aPhoneNumber, int index)
  {
    boolean wasAdded = false;
    if(phoneNumbers.contains(aPhoneNumber))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPhoneNumbers()) { index = numberOfPhoneNumbers() - 1; }
      phoneNumbers.remove(aPhoneNumber);
      phoneNumbers.add(index, aPhoneNumber);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPhoneNumberAt(aPhoneNumber, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (users.size() > 0)
    {
      User aUser = users.get(users.size() - 1);
      aUser.delete();
      users.remove(aUser);
    }
    
    while (loginIDs.size() > 0)
    {
      UserLogin aLoginID = loginIDs.get(loginIDs.size() - 1);
      aLoginID.delete();
      loginIDs.remove(aLoginID);
    }
    
    while (groups.size() > 0)
    {
      Group aGroup = groups.get(groups.size() - 1);
      aGroup.delete();
      groups.remove(aGroup);
    }
    
    while (restaurants.size() > 0)
    {
      Restaurant aRestaurant = restaurants.get(restaurants.size() - 1);
      aRestaurant.delete();
      restaurants.remove(aRestaurant);
    }
    
    while (locations.size() > 0)
    {
      Location aLocation = locations.get(locations.size() - 1);
      aLocation.delete();
      locations.remove(aLocation);
    }
    
    while (addresses.size() > 0)
    {
      Address aAddress = addresses.get(addresses.size() - 1);
      aAddress.delete();
      addresses.remove(aAddress);
    }
    
    while (phoneNumbers.size() > 0)
    {
      PhoneNumber aPhoneNumber = phoneNumbers.get(phoneNumbers.size() - 1);
      aPhoneNumber.delete();
      phoneNumbers.remove(aPhoneNumber);
    }
    
  }

}