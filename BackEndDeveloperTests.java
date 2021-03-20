// --== CS400 File Header Information ==--
// Name: Nitit Jongsawatsataporn
// Email: jongsawatsat@wisc.edu
// Team: red
// Role: Backend Developer
// TA: Xi
// Lecturer: Florian Heimerl
// Notes to Grader: The string reader is taken from DataWranglerTest

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
	    try {
	        BackendInterface backend = new Backend(new StringReader(
            "room_id, name, host_id, host_name, neighborhood, room_type, price\n"+
                "2539, Clean & quiet apt home by the park, 2787, John, Kensington, Private room, 149\n"+
                "2595, Skylit Midtown Castle, 2845, Jennifer, Midtown, Entire home/apt, 225\n"+
                "12048, LowerEastSide apt share shortterm 1, 7549, Ben, Lower East Side, Shared room, 40\n" ));
	        assertTrue(backend.getNumberOfRooms() == 0); 
	    }
	    catch (Exception e) {
	      fail(e.toString());
	    }
	  	  
	}

	/**
	 * This test method check that it return the list of neighborhood and not null.
	 * The will ask for the backend to return the list of neighborhood on a specifically
	 * designed stringreader to test whether it return all neighborhood or not.
	 * */
	@Test
	public void testNeighborhoods() {
	    try {
	        BackendInterface backend = new Backend(new StringReader(
	            "room_id, name, host_id, host_name, neighborhood, room_type, price\n"+
	            "2539, Clean & quiet apt home by the park, 2787, John, Kensington, Private room, 149\n"+
	            "2595, Skylit Midtown Castle, 2845, Jennifer, Midtown, Entire home/apt, 225\n"+
                "12048, LowerEastSide apt share shortterm 1, 7549, Ben, Lower East Side, Shared room, 40\n" ));
            List<String> neighbore = backend.getAllNeighborhoods();
            for(String room: neighbore)
                assertNotNull(room);
            assertTrue(neighbore.size() == 3);
        }
        catch (Exception e) {
            fail(e.toString());
        }
	}

	/**
	 * This test test that at first get three movies it would return zero hotel.
	 * This will be done by calling the backend and calling getThreeRooms immediately.
	 */
	@Test
	public void testGetThreeRooms() {
    	try {
            BackendInterface backend = new Backend(new StringReader(
                "room_id, name, host_id, host_name, neighborhood, room_type, price\n"+
                "2539, Clean & quiet apt home by the park, 2787, John, Kensington, Private room, 149\n"+
                "2595, Skylit Midtown Castle, 2845, Jennifer, Midtown, Entire home/apt, 225\n"+
                "12048, LowerEastSide apt share shortterm 1, 7549, Ben, Lower East Side, Shared room, 40\n" ));
            List<RoomInterface> rooms = backend.getThreeRooms(0);
            assertTrue(rooms.size() == 0);
        }
        catch (Exception e) {
            fail(e.toString());
        }	
	}

	/**
	 * This test will test that the list is return in the descending order of price.
	 * This will be done by accessing the method all calling the first neightbore
	 * (Hopefully) it will have three or more hotel. Then, we wil call to check the first three whether it's
	 * in the proper order or not.
	 */
	@Test
	public void testSorted() {
    	try {
            BackendInterface backend = new Backend(new StringReader(
                "room_id, name, host_id, host_name, neighborhood, room_type, price\n"+
                "2539, Clean & quiet apt home by the park, 2787, John, Kensington, Private room, 149\n"+
                "8888, Clean & quiet apt home by the park, 2787, John, Kensington, Private room, 182\n"+
                "9932, Clean & quiet apt home by the park, 2787, John, Kensington, Private room, 33\n"+
                "2595, Skylit Midtown Castle, 2845, Jennifer, Midtown, Entire home/apt, 225\n"+
                "12048, LowerEastSide apt share shortterm 1, 7549, Ben, Lower East Side, Shared room, 40\n" ));
            List<String> neighbore = backend.getAllNeighborhoods();
            for(String zone: neighbore) //Set all neighborhood
                backend.selectNeighborhood(zone);
            List<RoomInterface> rooms = backend.getThreeRooms(0); //This current should have 5 
                                                                  //but three room should have 3
            assertTrue(rooms.size() == 3);
            for(int i=0;i<2;i++) {
                assertTrue(rooms.get(i).getPrice() <= rooms.get(i+1).getPrice()); //Check order
            }
        }
        catch (Exception e) {
            fail(e.toString());
        }   
	}

	/**
	 * This test will test get three hotels at two last position.
	 * It would follow the same step as last method but it would call at position that would expected to 
	 * return only 2 movies. Then, it would check the size and it must be true
	 */
	@Test
	public void testGetLastTwoHotels() {
    	try {
            BackendInterface backend = new Backend(new StringReader(
                "room_id, name, host_id, host_name, neighborhood, room_type, price\n"+
                "2539, Clean & quiet apt home by the park, 2787, John, Kensington, Private room, 149\n"+
                "8888, Clean & quiet apt home by the park, 2787, John, Kensington, Private room, 182\n"+
                "9932, Clean & quiet apt home by the park, 2787, John, Kensington, Private room, 33\n"+
                "2595, Skylit Midtown Castle, 2845, Jennifer, Midtown, Entire home/apt, 225\n"+
                "12048, LowerEastSide apt share shortterm 1, 7549, Ben, Lower East Side, Shared room, 40\n" ));
            List<String> neighbore = backend.getAllNeighborhoods();
            for(String zone: neighbore)
                backend.selectNeighborhood(zone);
            List<RoomInterface> rooms = backend.getThreeRooms(3);
            assertTrue(rooms.size() == 2);
            for(int i=0;i<1;i++) {
                assertTrue(rooms.get(i).getPrice() <= rooms.get(i+1).getPrice());
            }
        }
        catch (Exception e) {
            fail(e.toString());
        }   
	}  
	
	/**
	 * This test check the update function and how would it work when call select and unselect neighborhood
	 * First, it will choose Kensington which have only 2 rooms
	 * Then, it would choose Midtown and now the list should have three room
	 */
	@Test
	public void testGetNeighborehood() {
    	try {
            BackendInterface backend = new Backend(new StringReader(
                "room_id, name, host_id, host_name, neighborhood, room_type, price\n"+
                "2539, Clean & quiet apt home by the park, 2787, John, Kensington, Private room, 149\n"+
                "9932, Clean & quiet apt home by the park, 2787, John, Kensington, Private room, 33\n"+
                "2595, Skylit Midtown Castle, 2845, Jennifer, Midtown, Entire home/apt, 225\n"+
                "12048, LowerEastSide apt share shortterm 1, 7549, Ben, Lower East Side, Shared room, 40\n" ));
            backend.selectNeighborhood("Kensington"); //Add Kensington
            List<RoomInterface> rooms = backend.getThreeRooms(0);
            assertTrue(rooms.size() == 2); //Should have 2
            backend.selectNeighborhood("Midtown"); //Add Midtown
            rooms = backend.getThreeRooms(0);
            assertTrue(rooms.size() == 3); //Should have 3 now
        }
        catch (Exception e) {
            fail(e.toString());
        } 
	}
	
	/**
	 * This test check the price function.
	 * It will set lower price to be a bit more than lowest, and highest to be a bit lower than highest
	 * This would make list have 2 rooms.
	 * Then, it would test extreme case, which is lower > higher. THis should make list have 0 elements.
	 */
	@Test
	public void testFixPriceRange() {
    	try {
            BackendInterface backend = new Backend(new StringReader(
                "room_id, name, host_id, host_name, neighborhood, room_type, price\n"+
                "2539, Clean & quiet apt home by the park, 2787, John, Kensington, Private room, 149\n"+
                "9932, Clean & quiet apt home by the park, 2787, John, Kensington, Private room, 33\n"+
                "2595, Skylit Midtown Castle, 2845, Jennifer, Midtown, Entire home/apt, 225\n"+
                "12048, LowerEastSide apt share shortterm 1, 7549, Ben, Lower East Side, Shared room, 40\n" ));
            backend.setPriceLowerBound(35); //Kick Kensington (lower one) out
            backend.setPriceUpperBound(224); //Kick Skylit Midtown Castle out
            List<String> neighbore = backend.getAllNeighborhoods();
            for(String zone: neighbore)
                backend.selectNeighborhood(zone);
            List<RoomInterface> rooms = backend.getThreeRooms(0);
            assertTrue(rooms.size() == 2); //Should have 2 rooms
            backend.setPriceLowerBound(500);
            rooms = backend.getThreeRooms(0);
            System.out.println(rooms.size());
            assertTrue(rooms.size() == 0); //Should have 0 rooms
        }
        catch (Exception e) {
            fail(e.toString());
        } 
	}
	
	/**
	 * This test test the newest method compared to last project.
	 * This will add the new room into the data base. It should update the value properly
	 * Adding same room again should results in IllegalArgumentException thrown
	 */
	@Test
	public void testAddRoom() {
        try {
            BackendInterface backend = new Backend(new StringReader(
                "room_id, name, host_id, host_name, neighborhood, room_type, price\n"+
                "2539, Clean & quiet apt home by the park, 2787, John, Kensington, Private room, 149\n"+
                "9932, Clean & quiet apt home by the park, 2787, John, Kensington, Private room, 33\n"+
                "2595, Skylit Midtown Castle, 2845, Jennifer, Midtown, Entire home/apt, 225\n"+
                "12048, LowerEastSide apt share shortterm 1, 7549, Ben, Lower East Side, Shared room, 40\n" ));
            //Add legit room
            String[] roomData = {"1111", "This is dummy room", "69420", "Harry", "Hogwarts", "Shared room", "9999"};
            RoomInterface room = new Room(roomData);
            try {
                backend.addRoom(room); //At room
                assertEquals(4, backend.getAllNeighborhoods().size()); //Neighborhood should be 1 bigger as "Hogward" is new one
                assertEquals(3, backend.getAllRoomType().size()); //Room type should keep the same as "Shared Room" is in the above one
                assertTrue(33 == backend.getPriceRange().get(0)); //The lowest should be the same
                assertTrue(9999 == backend.getPriceRange().get(1)); //Price for going to Hogwarts should be new highest
            }
            catch (Exception e) {
                fail("No Exception should be thrown");
            }
            try {
                backend.addRoom(room); //Add same room 
                //Expect IllegalArgumentException
                fail("IllegalArgumentException must be thrown");
            }
            catch (IllegalArgumentException e1) {
                //Correctly thrown
            }
            catch (Exception e) {
                fail("No Other type Exception should be thrown");
            }
        }
        catch (Exception e) {
            fail(e.toString());
        } 
    }
}
