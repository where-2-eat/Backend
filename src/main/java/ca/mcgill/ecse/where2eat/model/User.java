/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse.where2eat.model;
import java.util.*;

// line 16 "../../../../../where2eat.ump"
public class User
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum UserRole { Admin, User }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private UserRole role;
  private String firstName;
  private String lastName;
  private int userID;
  private Set<Category> categories;

  //User Associations
  private Where2Eat where2Eat;
  private UserLogin userLogin;
  private List<Group> userGroups;
  private PhoneNumber phoneNumber;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(UserRole aRole, String aFirstName, String aLastName, int aUserID, Set<Category> aCategories, Where2Eat aWhere2Eat, UserLogin aUserLogin)
  {
    role = aRole;
    firstName = aFirstName;
    lastName = aLastName;
    userID = aUserID;
    categories = aCategories;
    boolean didAddWhere2Eat = setWhere2Eat(aWhere2Eat);
    if (!didAddWhere2Eat)
    {
      throw new RuntimeException("Unable to create user due to where2Eat");
    }
    boolean didAddUserLogin = setUserLogin(aUserLogin);
    if (!didAddUserLogin)
    {
      throw new RuntimeException("Unable to create user due to userLogin");
    }
    userGroups = new ArrayList<Group>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setRole(UserRole aRole)
  {
    boolean wasSet = false;
    role = aRole;
    wasSet = true;
    return wasSet;
  }

  public boolean setFirstName(String aFirstName)
  {
    boolean wasSet = false;
    firstName = aFirstName;
    wasSet = true;
    return wasSet;
  }

  public boolean setLastName(String aLastName)
  {
    boolean wasSet = false;
    lastName = aLastName;
    wasSet = true;
    return wasSet;
  }

  public boolean setUserID(int aUserID)
  {
    boolean wasSet = false;
    userID = aUserID;
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

  public UserRole getRole()
  {
    return role;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public int getUserID()
  {
    return userID;
  }

  public Set<Category> getCategories()
  {
    return categories;
  }

  public Where2Eat getWhere2Eat()
  {
    return where2Eat;
  }

  public UserLogin getUserLogin()
  {
    return userLogin;
  }

  public Group getUserGroup(int index)
  {
    Group aUserGroup = userGroups.get(index);
    return aUserGroup;
  }

  public List<Group> getUserGroups()
  {
    List<Group> newUserGroups = Collections.unmodifiableList(userGroups);
    return newUserGroups;
  }

  public int numberOfUserGroups()
  {
    int number = userGroups.size();
    return number;
  }

  public boolean hasUserGroups()
  {
    boolean has = userGroups.size() > 0;
    return has;
  }

  public int indexOfUserGroup(Group aUserGroup)
  {
    int index = userGroups.indexOf(aUserGroup);
    return index;
  }

  public PhoneNumber getPhoneNumber()
  {
    return phoneNumber;
  }

  public boolean hasPhoneNumber()
  {
    boolean has = phoneNumber != null;
    return has;
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
      existingWhere2Eat.removeUser(this);
    }
    where2Eat.addUser(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setUserLogin(UserLogin aNewUserLogin)
  {
    boolean wasSet = false;
    if (aNewUserLogin == null)
    {
      //Unable to setUserLogin to null, as user must always be associated to a userLogin
      return wasSet;
    }
    
    User existingUser = aNewUserLogin.getUser();
    if (existingUser != null && !equals(existingUser))
    {
      //Unable to setUserLogin, the current userLogin already has a user, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    UserLogin anOldUserLogin = userLogin;
    userLogin = aNewUserLogin;
    userLogin.setUser(this);

    if (anOldUserLogin != null)
    {
      anOldUserLogin.setUser(null);
    }
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfUserGroups()
  {
    return 0;
  }

  public boolean addUserGroup(Group aUserGroup)
  {
    boolean wasAdded = false;
    if (userGroups.contains(aUserGroup)) { return false; }
    userGroups.add(aUserGroup);
    if (aUserGroup.indexOfUser(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aUserGroup.addUser(this);
      if (!wasAdded)
      {
        userGroups.remove(aUserGroup);
      }
    }
    return wasAdded;
  }

  public boolean removeUserGroup(Group aUserGroup)
  {
    boolean wasRemoved = false;
    if (!userGroups.contains(aUserGroup))
    {
      return wasRemoved;
    }

    int oldIndex = userGroups.indexOf(aUserGroup);
    userGroups.remove(oldIndex);
    if (aUserGroup.indexOfUser(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aUserGroup.removeUser(this);
      if (!wasRemoved)
      {
        userGroups.add(oldIndex,aUserGroup);
      }
    }
    return wasRemoved;
  }

  public boolean addUserGroupAt(Group aUserGroup, int index)
  {  
    boolean wasAdded = false;
    if(addUserGroup(aUserGroup))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUserGroups()) { index = numberOfUserGroups() - 1; }
      userGroups.remove(aUserGroup);
      userGroups.add(index, aUserGroup);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserGroupAt(Group aUserGroup, int index)
  {
    boolean wasAdded = false;
    if(userGroups.contains(aUserGroup))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUserGroups()) { index = numberOfUserGroups() - 1; }
      userGroups.remove(aUserGroup);
      userGroups.add(index, aUserGroup);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserGroupAt(aUserGroup, index);
    }
    return wasAdded;
  }

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
      placeholderWhere2Eat.removeUser(this);
    }
    UserLogin existingUserLogin = userLogin;
    userLogin = null;
    if (existingUserLogin != null)
    {
      existingUserLogin.setUser(null);
    }
    ArrayList<Group> copyOfUserGroups = new ArrayList<Group>(userGroups);
    userGroups.clear();
    for(Group aUserGroup : copyOfUserGroups)
    {
      if (aUserGroup.numberOfUsers() <= Group.minimumNumberOfUsers())
      {
        aUserGroup.delete();
      }
      else
      {
        aUserGroup.removeUser(this);
      }
    }
    phoneNumber = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "firstName" + ":" + getFirstName()+ "," +
            "lastName" + ":" + getLastName()+ "," +
            "userID" + ":" + getUserID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "role" + "=" + (getRole() != null ? !getRole().equals(this)  ? getRole().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "categories" + "=" + (getCategories() != null ? !getCategories().equals(this)  ? getCategories().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "where2Eat = "+(getWhere2Eat()!=null?Integer.toHexString(System.identityHashCode(getWhere2Eat())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "userLogin = "+(getUserLogin()!=null?Integer.toHexString(System.identityHashCode(getUserLogin())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "phoneNumber = "+(getPhoneNumber()!=null?Integer.toHexString(System.identityHashCode(getPhoneNumber())):"null");
  }
}