/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse.where2eat.model;
import java.util.*;

// line 32 "../../../../../where2eat.ump"
public class Group
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Group Attributes
  private String name;
  private String groupID;

  //Group Associations
  private List<User> users;
  private Location selectedLocation;
  private Restaurant recommendation;
  private Where2Eat where2Eat;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Group(String aName, String aGroupID, Where2Eat aWhere2Eat, User... allUsers)
  {
    name = aName;
    groupID = aGroupID;
    users = new ArrayList<User>();
    boolean didAddUsers = setUsers(allUsers);
    if (!didAddUsers)
    {
      throw new RuntimeException("Unable to create Group, must have at least 1 users");
    }
    boolean didAddWhere2Eat = setWhere2Eat(aWhere2Eat);
    if (!didAddWhere2Eat)
    {
      throw new RuntimeException("Unable to create group due to where2Eat");
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

  public boolean setGroupID(String aGroupID)
  {
    boolean wasSet = false;
    groupID = aGroupID;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getGroupID()
  {
    return groupID;
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

  public Location getSelectedLocation()
  {
    return selectedLocation;
  }

  public boolean hasSelectedLocation()
  {
    boolean has = selectedLocation != null;
    return has;
  }

  public Restaurant getRecommendation()
  {
    return recommendation;
  }

  public boolean hasRecommendation()
  {
    boolean has = recommendation != null;
    return has;
  }

  public Where2Eat getWhere2Eat()
  {
    return where2Eat;
  }

  public boolean isNumberOfUsersValid()
  {
    boolean isValid = numberOfUsers() >= minimumNumberOfUsers();
    return isValid;
  }

  public static int minimumNumberOfUsers()
  {
    return 1;
  }

  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }
    users.add(aUser);
    if (aUser.indexOfUserGroup(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aUser.addUserGroup(this);
      if (!wasAdded)
      {
        users.remove(aUser);
      }
    }
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    if (!users.contains(aUser))
    {
      return wasRemoved;
    }

    if (numberOfUsers() <= minimumNumberOfUsers())
    {
      return wasRemoved;
    }

    int oldIndex = users.indexOf(aUser);
    users.remove(oldIndex);
    if (aUser.indexOfUserGroup(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aUser.removeUserGroup(this);
      if (!wasRemoved)
      {
        users.add(oldIndex,aUser);
      }
    }
    return wasRemoved;
  }

  public boolean setUsers(User... newUsers)
  {
    boolean wasSet = false;
    ArrayList<User> verifiedUsers = new ArrayList<User>();
    for (User aUser : newUsers)
    {
      if (verifiedUsers.contains(aUser))
      {
        continue;
      }
      verifiedUsers.add(aUser);
    }

    if (verifiedUsers.size() != newUsers.length || verifiedUsers.size() < minimumNumberOfUsers())
    {
      return wasSet;
    }

    ArrayList<User> oldUsers = new ArrayList<User>(users);
    users.clear();
    for (User aNewUser : verifiedUsers)
    {
      users.add(aNewUser);
      if (oldUsers.contains(aNewUser))
      {
        oldUsers.remove(aNewUser);
      }
      else
      {
        aNewUser.addUserGroup(this);
      }
    }

    for (User anOldUser : oldUsers)
    {
      anOldUser.removeUserGroup(this);
    }
    wasSet = true;
    return wasSet;
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

  public boolean setSelectedLocation(Location aNewSelectedLocation)
  {
    boolean wasSet = false;
    selectedLocation = aNewSelectedLocation;
    wasSet = true;
    return wasSet;
  }

  public boolean setRecommendation(Restaurant aNewRecommendation)
  {
    boolean wasSet = false;
    recommendation = aNewRecommendation;
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
      existingWhere2Eat.removeGroup(this);
    }
    where2Eat.addGroup(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<User> copyOfUsers = new ArrayList<User>(users);
    users.clear();
    for(User aUser : copyOfUsers)
    {
      aUser.removeUserGroup(this);
    }
    selectedLocation = null;
    recommendation = null;
    Where2Eat placeholderWhere2Eat = where2Eat;
    this.where2Eat = null;
    if(placeholderWhere2Eat != null)
    {
      placeholderWhere2Eat.removeGroup(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "groupID" + ":" + getGroupID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "selectedLocation = "+(getSelectedLocation()!=null?Integer.toHexString(System.identityHashCode(getSelectedLocation())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "recommendation = "+(getRecommendation()!=null?Integer.toHexString(System.identityHashCode(getRecommendation())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "where2Eat = "+(getWhere2Eat()!=null?Integer.toHexString(System.identityHashCode(getWhere2Eat())):"null");
  }
}