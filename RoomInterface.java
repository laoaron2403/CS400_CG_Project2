/**
 * The RoomInterface for Data
 * @author fangjunzhou
 */
public interface RoomInterface extends Comparable<RoomInterface>{
  public String getRoomId();
  public String getName();
  public String getHostId();
  public String getHostName();
  public String getNeighborhoodName();
  public String getRoomType();
  public int getPrice();

  // extend from Comparable
  @Override
  public int compareTo(RoomInterface otherRoom);
  public boolean equals(RoomInterface otherRoom);
}
