import org.junit.Test;
import static org.junit.Assert.*;

public class FrontEndDeveloperTests {
  /**
   * This test method will test the default data in the application. by default, the main page should
   * present basic instructions of the programs, such as command 'x' is esc in the main page and back
   * in other pages. The room list should be empty since no certain filtering keys has been selected.
   */
  @Test
  public void testFrontendDefault() {
    fail("The default mode failed.");
  }
  
  /**
   * This test method will test whether the program successfully direct the main page to price range 
   * page after users type 'p'. The price page will allow users to filter rooms by the price range from
   * low to high, such as from $100 to $200. The default range includes all range. 
   */
  @Test
  public void testFrontendPricePage() {
    fail("The price page failed.");
  }
  
  /**
   * This test method will test whether the program successfully direct the main page to room type page
   * after users type 't'. Room type page will allow users to filter rooms by type, such as private room,
   * entire home. The default selects all room types.
   */
  @Test
  public void testFrontendRoomTypePage() {
    fail("The default mode failed.");
  }
  
  /**
   * This test method will test whether the program successfully direct the main page to neighborhood page
   * after users type 'n'. Neighborhood page will allow users to select the area they intend to live
   * in, such as Brooklyn. The default mood does not select any neighborhood. Users must select at least
   * one neighborhood to enable program load rooms to the list.
   */
  @Test
  public void testFrontendNeighborhoodPage() {
    fail("The default mode failed.");
  }
  
  /**
   * In the default mood, the list does not contain any room since there is no neighborhood being selected.
   * This test method will test whether the program successfully present rooms to the list after users selected
   * neighborhood.
   */
  @Test
  public void testFrontendSelectingRooms() {
    fail("The default mode failed.");
  }
}

