/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/

package ca.mcgill.ecse.where2eat.model;

// line 48 "../../../../../where2eat.ump"
public class Location
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Location Attributes
  private double xCoordinate;
  private double yCoordinate;

  //Location Associations
  private Where2Eat where2Eat;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Location(double aXCoordinate, double aYCoordinate, Where2Eat aWhere2Eat)
  {
    xCoordinate = aXCoordinate;
    yCoordinate = aYCoordinate;
    boolean didAddWhere2Eat = setWhere2Eat(aWhere2Eat);
    if (!didAddWhere2Eat)
    {
      throw new RuntimeException("Unable to create location due to where2Eat");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setXCoordinate(double aXCoordinate)
  {
    boolean wasSet = false;
    xCoordinate = aXCoordinate;
    wasSet = true;
    return wasSet;
  }

  public boolean setYCoordinate(double aYCoordinate)
  {
    boolean wasSet = false;
    yCoordinate = aYCoordinate;
    wasSet = true;
    return wasSet;
  }

  public double getXCoordinate()
  {
    return xCoordinate;
  }

  public double getYCoordinate()
  {
    return yCoordinate;
  }

  public Where2Eat getWhere2Eat()
  {
    return where2Eat;
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
      existingWhere2Eat.removeLocation(this);
    }
    where2Eat.addLocation(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Where2Eat placeholderWhere2Eat = where2Eat;
    this.where2Eat = null;
    if(placeholderWhere2Eat != null)
    {
      placeholderWhere2Eat.removeLocation(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "xCoordinate" + ":" + getXCoordinate()+ "," +
            "yCoordinate" + ":" + getYCoordinate()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "where2Eat = "+(getWhere2Eat()!=null?Integer.toHexString(System.identityHashCode(getWhere2Eat())):"null");
  }
}