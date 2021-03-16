import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Collections;
import java.util.List;
import java.util.zip.DataFormatException;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 * This class contains a set of tests for the RoomInterface and RoomDataReaderInterface
 * implementation of the Room Mapper project.
 */
public class DataWranglerTests {
	
	RoomDataReaderInterface readerToTest;
	/**
	 * This test reads in 3 rooms and tests whether the rooms
	 * return expected room ids
	 */
	@Test
	public void testGetRoomId()
	{
		
		try {
			RoomDataReader readerToTest = new RoomDataReader();
			List<Room> roomList = readerToTest.readDataSet(new StringReader(
					"room_id, name, host_id, host_name, neighborhood, room_type, price\n"+
						"2539, Clean & quiet apt home by the park, 2787, John, Kensington, Private room, 149\n"+
						"2595, Skylit Midtown Castle, 2845, Jennifer, Midtown, Entire home/apt, 225\n"+
						"12048, LowerEastSide apt share shortterm 1, 7549, Ben, Lower East Side, Shared room, 40\n" ));
		if(roomList == null)
			fail("The extracted list is null");
		String room_id1 = "2539";
		String room_id2 = "2595";
		String room_id3 = "12048";
		if(!roomList.get(0).getRoomId().equals(room_id1))
			fail("Does not pass testGetRoomId");
		if(!roomList.get(1).getRoomId().equals(room_id2))
			fail("Does not pass testGetRoomId");
		if(!roomList.get(2).getRoomId().equals(room_id3))
			fail("Does not pass testGetRoomId");
		} catch (Exception e) {
			e.printStackTrace();
			// test failed
			fail(e.getMessage());
		}
		
	}
	/**
	 * This test reads in 3 rooms and tests whether the rooms
	 * return expected rooms' names
	 */
	@Test
	public void testGetHostName()
	{
		try {
			RoomDataReader readerToTest = new RoomDataReader();
			List<Room> roomList = readerToTest.readDataSet(new StringReader(
					"room_id, name, host_id, host_name, neighborhood, room_type, price\n"+
						"2539, Clean & quiet apt home by the park, 2787, John, Kensington, Private room, 149\n"+
						"2595, Skylit Midtown Castle, 2845, Jennifer, Midtown, Entire home/apt, 225\n"+
						"12048, LowerEastSide apt share shortterm 1, 7549, Ben, Lower East Side, Shared room, 40\n" ));
		if(roomList == null)
			fail("The extracted list is null");
		String room_host1 = "John";
		String room_host2 = "Jennifer";
		String room_host3 = "Ben";
		if(!roomList.get(0).getHostName().equals(room_host1))
			fail("Does not pass testGetHostName");
		if(!roomList.get(1).getHostName().equals(room_host2))
			fail("Does not pass testGetHostName");
		if(!roomList.get(2).getHostName().equals(room_host3))
			fail("Does not pass testGetHostName");
		} catch (Exception e) {
			e.printStackTrace();
			// test failed
			fail(e.getMessage());
		}
		
	}
	/**
	 * This test reads in 3 rooms and tests whether the rooms
	 * return expected rooms' prices
	 */
	@Test
	public void testGetPrice()
	{
		try {
			RoomDataReader readerToTest = new RoomDataReader();
			List<Room> roomList = readerToTest.readDataSet(new StringReader(
					"room_id, name, host_id, host_name, neighborhood, room_type, price\n"+
						"2539, Clean & quiet apt home by the park, 2787, John, Kensington, Private room, 149\n"+
						"2595, Skylit Midtown Castle, 2845, Jennifer, Midtown, Entire home/apt, 225\n"+
						"12048, LowerEastSide apt share shortterm 1, 7549, Ben, Lower East Side, Shared room, 40\n" ));
		if(roomList == null)
			fail("The extracted list is null");
		int room_price1 = 149;
		int room_price2 = 225;
		int room_price3 = 40;
		if(roomList.get(0).getPrice() != room_price1)
			fail("Does not pass testGetPrice");
		if(roomList.get(1).getPrice() != room_price2)
			fail("Does not pass testGetPrice");
		if(roomList.get(2).getPrice() != room_price3)
			fail("Does not pass testGetPrice");
		} catch (Exception e) {
			e.printStackTrace();
			// test failed
			fail(e.getMessage());
		}
		
	}
	/**
	 * This test reads in 3 rooms and tests whether the rooms
	 * return expected rooms' types
	 */
	@Test
	public void testGetRoomType()
	{
		try {
			RoomDataReader readerToTest = new RoomDataReader();
			List<Room> roomList = readerToTest.readDataSet(new StringReader(
					"room_id, name, host_id, host_name, neighborhood, room_type, price\n"+
						"2539, Clean & quiet apt home by the park, 2787, John, Kensington, Private room, 149\n"+
						"2595, Skylit Midtown Castle, 2845, Jennifer, Midtown, Entire home/apt, 225\n"+
						"12048, LowerEastSide apt share shortterm 1, 7549, Ben, Lower East Side, Shared room, 40\n" ));
		if(roomList == null)
			fail("The extracted list is null");
		String room_type1 = "Private room";
		String room_type2 = "Entire home/apt";
		String room_type3 = "Shared room";
		if(!roomList.get(0).getRoomType().equals(room_type1))
			fail("Does not pass testGetRoomType");
		if(!roomList.get(1).getRoomType().equals(room_type2))
			fail("Does not pass testGetRoomType");
		if(!roomList.get(2).getRoomType().equals(room_type3))
			fail("Does not pass testGetRoomType");
		} catch (Exception e) {
			e.printStackTrace();
			// test failed
			fail(e.getMessage());
		}
		
	}
	/**
	 * This test reads in 3 rooms and tests whether the rooms
	 * return expected rooms' host ids
	 */
	@Test
	public void testHostId()
	{
		try {
			RoomDataReader readerToTest = new RoomDataReader();
			List<Room> roomList = readerToTest.readDataSet(new StringReader(
					"room_id, name, host_id, host_name, neighborhood, room_type, price\n"+
						"2539, Clean & quiet apt home by the park, 2787, John, Kensington, Private room, 149\n"+
						"2595, Skylit Midtown Castle, 2845, Jennifer, Midtown, Entire home/apt, 225\n"+
						"12048, LowerEastSide apt share shortterm 1, 7549, Ben, Lower East Side, Shared room, 40\n" ));
		if(roomList == null)
			fail("The extracted list is null");
		String room_hostID1 = "2787";
		String room_hostID2 = "2845";
		String room_hostID3 = "7549";
		if(!roomList.get(0).getHostId().equals(room_hostID1))
			fail("Does not pass testHostId");
		if(!roomList.get(1).getHostId().equals(room_hostID2))
			fail("Does not pass testHostId");
		if(!roomList.get(2).getHostId().equals(room_hostID3))
			fail("Does not pass testHostId");
		} catch (Exception e) {
			e.printStackTrace();
			// test failed
			fail(e.getMessage());
		}
		
	}
	/**
	 * This test method tests if the the RoomDataReader successfully add a new room with respective properties
	 */
	@Test
	public void testAdding()
	{
		//preparation phase
	    try {
			  FileWriter myWriter = new FileWriter("roomtemp.csv");
			  myWriter.write("room_id, name, host_id, host_name, neighborhood, room_type, price\n"+
				"2539, Clean & quiet apt home by the park, 2787, John, Kensington, Private room, 149\n"+
				"2595, Skylit Midtown Castle, 2845, Jennifer, Midtown, Entire home/apt, 225\n"+
				"12048, LowerEastSide apt share shortterm 1, 7549, Ben, Lower East Side, Shared room, 40\n");
			  myWriter.close();
			  System.out.println("Successfully wrote to the file.");
	    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
	    }

	    try { 
	    	//writing
		    RoomDataReader readerToTest = new RoomDataReader();
		    String[] newRoom = {"1145","Crib at the hood","1919","Lamar","Grove Street","Private room","81"};
		    FileWriter roomWriter = new FileWriter("roomtemp.csv",true);
		    readerToTest.addRoom(roomWriter, newRoom);
		    
	    	//checking
	    	Reader reader = new FileReader("roomtemp.csv");
			List<Room> roomList = readerToTest.readDataSet(reader);
			if(!roomList.get(3).getRoomId().equals("1145") ||
					!roomList.get(3).getName().equals("Crib at the hood") ||
					!roomList.get(3).getHostId().equals("1919") ||
					!roomList.get(3).getHostName().equals("Lamar") &&
					!roomList.get(3).getNeighborhoodName().equals("Grove Street") ||
					!roomList.get(3).getRoomType().equals("Private room") ||
					roomList.get(3).getPrice() != 81)
				fail("Fail to add the new room");
		} catch (DataFormatException e) {
			e.printStackTrace();
			fail("Fail to add the new room due to data format");
		}
	    catch (IOException e) {
			e.printStackTrace();
			fail("Fail to add the new room");
		} 
	}
}
