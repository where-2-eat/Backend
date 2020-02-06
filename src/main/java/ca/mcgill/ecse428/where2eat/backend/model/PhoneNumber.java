/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4262.30c9ffc7c modeling language!*/

package ca.mcgill.ecse428.where2eat.backend.model;

// line 66 "../../../../../../../../../src/main/java/where2eat.ump"
public class PhoneNumber
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PhoneNumber Attributes
  private String areaCode;
  private String localNumber;

  //PhoneNumber Associations
  private Where2Eat where2Eat;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PhoneNumber(String aAreaCode, String aLocalNumber, Where2Eat aWhere2Eat)
  {
    areaCode = aAreaCode;
    localNumber = aLocalNumber;
    boolean didAddWhere2Eat = setWhere2Eat(aWhere2Eat);
    if (!didAddWhere2Eat)
    {
      throw new RuntimeException("Unable to create phoneNumber due to where2Eat");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAreaCode(String aAreaCode)
  {
    boolean wasSet = false;
    areaCode = aAreaCode;
    wasSet = true;
    return wasSet;
  }

  public boolean setLocalNumber(String aLocalNumber)
  {
    boolean wasSet = false;
    localNumber = aLocalNumber;
    wasSet = true;
    return wasSet;
  }

  public String getAreaCode()
  {
    return areaCode;
  }

  public String getLocalNumber()
  {
    return localNumber;
  }
  /* Code from template association_GetOne */
  public Where2Eat getWhere2Eat()
  {
    return where2Eat;
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
      existingWhere2Eat.removePhoneNumber(this);
    }
    where2Eat.addPhoneNumber(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Where2Eat placeholderWhere2Eat = where2Eat;
    this.where2Eat = null;
    if(placeholderWhere2Eat != null)
    {
      placeholderWhere2Eat.removePhoneNumber(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "areaCode" + ":" + getAreaCode()+ "," +
            "localNumber" + ":" + getLocalNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "where2Eat = "+(getWhere2Eat()!=null?Integer.toHexString(System.identityHashCode(getWhere2Eat())):"null");
  }
}