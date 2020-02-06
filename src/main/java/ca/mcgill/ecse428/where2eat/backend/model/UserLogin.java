/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse428.where2eat.backend.model;

import javax.persistence.*;

// line 26 "../../../../../../where2eat.ump"
@Entity
public class UserLogin
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //UserLogin Attributes
  @Id
  private String userName;
  private String password;

  //UserLogin Associations
  @OneToOne(optional = true)
  private User user;
  @ManyToOne(optional = true)
  private Where2Eat where2Eat;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public UserLogin(String aUserName, String aPassword, Where2Eat aWhere2Eat)
  {
    userName = aUserName;
    password = aPassword;
    boolean didAddWhere2Eat = setWhere2Eat(aWhere2Eat);
    if (!didAddWhere2Eat)
    {
      throw new RuntimeException("Unable to create loginID due to where2Eat");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setUserName(String aUserName)
  {
    boolean wasSet = false;
    userName = aUserName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public String getUserName()
  {
    return userName;
  }

  public String getPassword()
  {
    return password;
  }

  public User getUser()
  {
    return user;
  }

  public boolean hasUser()
  {
    boolean has = user != null;
    return has;
  }

  public Where2Eat getWhere2Eat()
  {
    return where2Eat;
  }

  public boolean setUser(User aNewUser)
  {
    boolean wasSet = false;
    if (user != null && !user.equals(aNewUser) && equals(user.getUserLogin()))
    {
      //Unable to setUser, as existing user would become an orphan
      return wasSet;
    }

    user = aNewUser;
    UserLogin anOldUserLogin = aNewUser != null ? aNewUser.getUserLogin() : null;

    if (!this.equals(anOldUserLogin))
    {
      if (anOldUserLogin != null)
      {
        anOldUserLogin.user = null;
      }
      if (user != null)
      {
        user.setUserLogin(this);
      }
    }
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
      existingWhere2Eat.removeLoginID(this);
    }
    where2Eat.addLoginID(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    User existingUser = user;
    user = null;
    if (existingUser != null)
    {
      existingUser.delete();
      existingUser.setUserLogin(null);
    }
    Where2Eat placeholderWhere2Eat = where2Eat;
    this.where2Eat = null;
    if(placeholderWhere2Eat != null)
    {
      placeholderWhere2Eat.removeLoginID(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "userName" + ":" + getUserName()+ "," +
            "password" + ":" + getPassword()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "user = "+(getUser()!=null?Integer.toHexString(System.identityHashCode(getUser())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "where2Eat = "+(getWhere2Eat()!=null?Integer.toHexString(System.identityHashCode(getWhere2Eat())):"null");
  }
}