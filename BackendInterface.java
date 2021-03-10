import java.util.List;

public interface BackendInterface {
  public void selectNeighborhood(String neighborhood);
  public void unselectNeighborhood(String neighborhood);
  public List<String> getNeighborhoods();
  public int getNumberOfRooms();
  public List<String> getAllNeighborhoods();
  public List<RoomInterface> getThreeRooms(int startingIndex);
  public void setPriceLowerBound(int price);
  public void setPriceUpperBound(int price);
  public List<Integer> getPriceRange(); //Return list of two integer. Highest and lowest 
                                        //This method search for whole list
                                        //**Not from current list**
  public void selectRoomType(String type);
  public void unselectRoomType(String type);
  public List<String> getAllRoomType();

  /**
  * Add new room to the RBT
  * @param room the room extent RoomInterface
  * @throws IllegalArgumentException if there's a exactly same room in the tree
  */
  public void addRoom(RoomInterface room) throws IllegalArgumentException;

}
