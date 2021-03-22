import java.util.List;

/**
 * BackendInterface for front end to use
 * @author nitit
 */
public interface BackendInterface {
  public void selectNeighborhood(String neighborhood); //Add neighborhood to considered list
  public void unselectNeighborhood(String neighborhood); //Remove neighborhood from considered list
  public List<String> getNeighborhoods(); //Return the list of current neighborhood
  public int getNumberOfRooms(); //Return the number of room in the list
  public List<String> getAllNeighborhoods(); //Return all type of neighborhood
  public List<RoomInterface> getThreeRooms(int startingIndex); //Return three top on the list
  public void setPriceLowerBound(int price); //Set lower bound
  public void setPriceUpperBound(int price); //Set upper bound
  public List<Integer> getPriceRange(); //Return list of two integer. Highest and lowest 
                                        //This method search for whole list
                                        //** Not from current list **
  public List<Integer> getCurrentPriceRange(); //Return list of two integer. Highest and lowest 
                                               //Current bound
  public void selectRoomType(String type); //Add RoomType to considered list
  public void unselectRoomType(String type); //Remove RoomType to considered list
  public List<String> getAllRoomType(); //Return all type of room

  /**
  * Add new room to the RBT
  * @param room the room extent RoomInterface
  * @throws IllegalArgumentException if there's a exactly same room in the tree
  */
  public void addRoom(RoomInterface room) throws IllegalArgumentException;

}
