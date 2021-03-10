
import java.util.List;
import java.io.StringReader;

import org.junit.Test;
import static org.junit.Assert.*;

public class BackEndDeveloperTests {
	
	/**
	 * This test method test initialization. 
	 * The process must not throw any error and it must return 0 hotel room with that selected
	 * zone
	 * */
	@Test
	public void testInitialize() {
		fail("Did not uncomment the work yet");
		/**
		* BackEndInterface backend = new Backend("hotel.csv");
		* assertTrue(backend.getNumberOfRooms() == 0);
	  	**/	  
	}

	/**
	 * This test method check that it return the list of nieghborehood and not null.
	 * The will ask for the backend to return the list of neighborehood on a specifically
	 * designed csv file to test whether it return all negihborehood or not.
	 * */
	@Test
	public void testNeighborhoods() {
		fail("Did not uncomment the work yet");
		/**
		 * BackEndInterface backend = new Backend("hotel.csv");
		 * List<String> neighbore = backend.getAllNeighborhoods();
		 * for(String it: neighbore) {
		 * 	assertNotNull(it);
		 * }
		 * assertFalse(neighbore.size() == 0);
		 */
	}

	/**
	 * This test test that at first get three movies it would return zero hotel.
	 * This will be done by alling the backend and calling getThreeRooms immediately.
	 */
	@Test
	public void testGetThreeRooms() {
		fail("Did not uncomment the work yet");
		/**
                 * BackEndInterface backend = new Backend("hotel.csv");
                 * List<HotelInterface> hotel = backend.getThreeRooms();
                 * assertTrue(hotel.size() == 0);
                 */ 	
	}

	/**
	 * This test will test that the list is return in the descending order of rating.
	 * This will be done by accessing the method all calling the first neightbore
	 * (Hopefully) it will have three or more hotel. Then, we wil call to check the first three whether it's
	 * in the proper order or not.
	 */
	@Test
	public void testSorted() {
		fail("Did not uncomment the work yet");
                /**
                 * BackEndInterface backend = new Backend("hotel.csv");
		 * List<String> neighbore = backend.getAllNeighborhoods();
		 * backend.selectNeighborhood(neghbore.get(0));
                 * List<HotelInterface> hotel = backend.getThreeRooms(0);
		 * for(int i=0;i<2;i++) {
		 * 	assertTrue(hotel.get(i).getRating() >= hotel.get(i+1).getRating());
                 * assertTrue(hotel.size() == 3);
                 */
	}

	/**
	 * This test will test get three hotels at two last position.
	 * It would follow the same step as last method but it would call at position that would expected to 
	 * return only 2 movies. Then, it would check the size and it must be true
	 */
	@Test
	public void testGetLastTwoHotels() {
                fail("Did not uncomment the work yet");
                /**
                 * BackEndInterface backend = new Backend("hotel.csv");
                 * List<String> neighbore = backend.getAllNeighborhoods();
                 * backend.selectNeighborhood(neghbore.get(0));
                 * List<HotelInterface> hotel = backend.getThreeRooms(backend.getNumberOfRooms()-2);
                 * for(int i=0;i<1;i++) {
                 *      assertTrue(hotel.get(i).getRating() >= hotel.get(i+1).getRating());
                 * assertTrue(hotel.size() == 2);
                 */
        }  
}
