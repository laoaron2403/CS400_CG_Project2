// --== CS400 File Header Information ==--
// Name: Xizheng Yu
// Email: xyu354@wisc.edu
// Team: CG
// TA: Xi Chen
// Lecturer: Gary Dahl
// Notes to Grader: None

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringReader;

public class FrontEndDeveloperTests {
  /**
   * This test method will test the default data in the application. by default, the main page should
   * present basic instructions of the programs, such as command 'x' is esc in the main page and back
   * in other pages. The room list should be empty since no certain filtering keys has been selected.
   */
  @Test
  public void testFrontendDefault() {
      PrintStream standardOut = System.out;
      InputStream standardIn = System.in;
      try {
          // set the input stream to our input (with an x to test of the program exists)
          String input = "x";
          InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
          System.setIn(inputStreamSimulator);
          ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
          // set the output to the stream captor to read the output of the front end
          System.setOut(new PrintStream(outputStreamCaptor));
          // instantiate when front end is implemented
          Object frontend =  new Frontend();
          ((Frontend)frontend).run(new Backend(new StringReader(
			"room_id, name, host_id, host_name, neighborhood, room_type, price\n"+
			"2539, Clean & quiet apt home by the park, 2787, John, Kensington, Private room, 149\n"+
			"2595, Skylit Midtown Castle, 2845, Jennifer, Midtown, Entire home/apt, 225\n"+
			"12048, LowerEastSide apt share shortterm 1, 7549, Ben, Lower East Side, Shared room, 40\n" 
			)));
          // set the output back to standard out for running the test
          System.setOut(standardOut);
          // same for standard in
          System.setIn(standardIn);
          String appOutput = outputStreamCaptor.toString();
          //test default no room presented
	  if (frontend == null || appOutput.contains("2539") || 
		!appOutput.contains("There is no room that have neighborhoods/price/types you selected") ||
	  	!appOutput.contains("Thank you for using program!")) {
	      // test fails
	      fail("The default mode failed.");
	  } else {
	      //test passes
	  }
      } catch (Exception e) {
	  // make sure stdin and stdout are set correctly after we get exception in test
	  System.setOut(standardOut);
	  System.setIn(standardIn);
	  e.printStackTrace();
	  // test failed
	  fail("The default mode failed.");
      }
    
  }
  
  /**
   * This test method will test whether the program successfully direct the main page to price range 
   * page after users type 'p'. The price page will allow users to filter rooms by the price range from
   * low to high, such as from $100 to $200. The default range includes all range. 
   */
  @Test
  public void testFrontendPricePage() {
      PrintStream standardOut = System.out;
      InputStream standardIn = System.in;
      try {
          // set the input stream to our input (with an p to test price page)
	  String input = "p" + System.lineSeparator() + "s" + System.lineSeparator() + 
		  "200" + System.lineSeparator() + "100" + System.lineSeparator() + "x"
		  + System.lineSeparator() + "x";
          InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
          System.setIn(inputStreamSimulator);
          ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
          // set the output to the stream captor to read the output of the front end
          System.setOut(new PrintStream(outputStreamCaptor));
          // instantiate when front end is implemented
          Object frontend =  new Frontend();
          ((Frontend)frontend).run(new Backend(new StringReader(
			"room_id, name, host_id, host_name, neighborhood, room_type, price\n"+
			"2539, Clean & quiet apt home by the park, 2787, John, Kensington, Private room, 149\n"+
			"2595, Skylit Midtown Castle, 2845, Jennifer, Midtown, Entire home/apt, 225\n"+
			"12048, LowerEastSide apt share shortterm 1, 7549, Ben, Lower East Side, Shared room, 40\n" 
			)));
          // set the output back to standard out for running the test
          System.setOut(standardOut);
          // same for standard in
          System.setIn(standardIn);
          String appOutput = outputStreamCaptor.toString();
          //test price bound change
	  if (frontend == null || !appOutput.contains("The current price bound is: 100$ - 200$")) {
	      // test fails
	      fail("The price page failed.");
	  } else {
	      //test passes
	  }
      } catch (Exception e) {
	  // make sure stdin and stdout are set correctly after we get exception in test
	  System.setOut(standardOut);
	  System.setIn(standardIn);
	  e.printStackTrace();
	  // test failed
	  fail("The price page failed.");
      }
  }
  
  /**
   * This test method will test whether the program successfully direct the main page to room type page
   * after users type 't'. Room type page will allow users to filter rooms by type, such as private room,
   * entire home. The default selects all room types.
   */
  @Test
  public void testFrontendRoomTypePage() {
      PrintStream standardOut = System.out;
      InputStream standardIn = System.in;
      try {
          // set the input stream to our input (with an t to test room type page)
	  String input = "t" + System.lineSeparator() + "1" + System.lineSeparator() + 
		  "2" + System.lineSeparator() + "x" + System.lineSeparator() + "x";
          InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
          System.setIn(inputStreamSimulator);
          ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
          // set the output to the stream captor to read the output of the front end
          System.setOut(new PrintStream(outputStreamCaptor));
          // instantiate when front end is implemented
          Object frontend =  new Frontend();
          ((Frontend)frontend).run(new Backend(new StringReader(
			"room_id, name, host_id, host_name, neighborhood, room_type, price\n"+
			"2539, Clean & quiet apt home by the park, 2787, John, Kensington, Private room, 149\n"+
			"2595, Skylit Midtown Castle, 2845, Jennifer, Midtown, Entire home/apt, 225\n"+
			"12048, LowerEastSide apt share shortterm 1, 7549, Ben, Lower East Side, Shared room, 40\n" 
			)));
          // set the output back to standard out for running the test
          System.setOut(standardOut);
          // same for standard in
          System.setIn(standardIn);
          String appOutput = outputStreamCaptor.toString();
          //test private room and entire room select
	  if (frontend == null || !appOutput.contains("(1): Private room (already selected)") ||
		  !appOutput.contains("(2): Entire home/apt (already selected)") ||
		  !appOutput.contains("(3): Shared room (not selected)")) {
	      // test fails
	      fail("The room type selection failed.");
	  } else {
	      //test passes
	  }
      } catch (Exception e) {
	  // make sure stdin and stdout are set correctly after we get exception in test
	  System.setOut(standardOut);
	  System.setIn(standardIn);
	  e.printStackTrace();
	  // test failed
	  fail("The room type selection failed.");
      }
  }
  
  /**
   * This test method will test whether the program successfully direct the main page to neighborhood page
   * after users type 'n'. Neighborhood page will allow users to select the area they intend to live
   * in, such as Brooklyn. The default mood does not select any neighborhood. Users must select at least
   * one neighborhood to enable program load rooms to the list.
   */
  @Test
  public void testFrontendNeighborhoodPage() {
      PrintStream standardOut = System.out;
      InputStream standardIn = System.in;
      try {
          // set the input stream to our input (with an n to test room type page)
	  String input = "n" + System.lineSeparator() + "1" + System.lineSeparator() + 
		  "x" + System.lineSeparator() + "x";
          InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
          System.setIn(inputStreamSimulator);
          ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
          // set the output to the stream captor to read the output of the front end
          System.setOut(new PrintStream(outputStreamCaptor));
          // instantiate when front end is implemented
          Object frontend =  new Frontend();
          ((Frontend)frontend).run(new Backend(new StringReader(
			"room_id, name, host_id, host_name, neighborhood, room_type, price\n"+
			"2539, Clean & quiet apt home by the park, 2787, John, Kensington, Private room, 149\n"+
			"2595, Skylit Midtown Castle, 2845, Jennifer, Midtown, Entire home/apt, 225\n"+
			"12048, LowerEastSide apt share shortterm 1, 7549, Ben, Lower East Side, Shared room, 40\n" 
			)));
          // set the output back to standard out for running the test
          System.setOut(standardOut);
          // same for standard in
          System.setIn(standardIn);
          String appOutput = outputStreamCaptor.toString();
          //test first room select 
	  if (frontend == null || !appOutput.contains("(1): Kensington (already selected)") ||
		  !appOutput.contains("(1) 2539, Clean & quiet apt home by the park, 2787, John, Kensington, Private room, 149")) {
	      // test fails
	      fail("The neighborhood selection failed.");
	  } else {
	      //test passes
	      return;
	  }
      } catch (Exception e) {
	  // make sure stdin and stdout are set correctly after we get exception in test
	  System.setOut(standardOut);
	  System.setIn(standardIn);
	  e.printStackTrace();
	  // test failed
	  fail("The neighborhood selection failed.");
      }
  }
  
  /**
   * In the default mood, the list does not contain any room since there is no neighborhood being selected.
   * This test method will test whether the program successfully present rooms to the list after users selected
   * neighborhood.
   */
  @Test
  public void testFrontendSelectingRooms() {
      PrintStream standardOut = System.out;
      InputStream standardIn = System.in;
      try {
          // set the input stream to our input (with an t, p, n to test all functions)
	  String input = "n" + System.lineSeparator() + "1" + System.lineSeparator() + "2" 
		  + System.lineSeparator() + "3" + System.lineSeparator() + "x" + System.lineSeparator() 
		  + "t" + System.lineSeparator() + "2" + System.lineSeparator() + "3" + System.lineSeparator()
		  + "x" + System.lineSeparator() + "p"  + System.lineSeparator() + "s"  + System.lineSeparator() 
		  + "300" + System.lineSeparator() + "100" + System.lineSeparator() + "x"  + System.lineSeparator() 
		  + "x";
          InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
          System.setIn(inputStreamSimulator);
          ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
          // set the output to the stream captor to read the output of the front end
          System.setOut(new PrintStream(outputStreamCaptor));
          // instantiate when front end is implemented
          Object frontend =  new Frontend();
          ((Frontend)frontend).run(new Backend(new StringReader(
			"room_id, name, host_id, host_name, neighborhood, room_type, price\n"+
			"2539, Clean & quiet apt home by the park, 2787, John, Kensington, Private room, 149\n"+
			"2595, Skylit Midtown Castle, 2845, Jennifer, Midtown, Entire home/apt, 225\n"+
			"12048, LowerEastSide apt share shortterm 1, 7549, Ben, Lower East Side, Shared room, 40\n" 
			)));
          // set the output back to standard out for running the test
          System.setOut(standardOut);
          // same for standard in
          System.setIn(standardIn);
          String appOutput = outputStreamCaptor.toString();
          //test all functions
	  if (frontend == null || !appOutput.contains("(1): Private room (not selected)") ||
		  !appOutput.contains("The current price bound is: 100$ - 300$") ||
		  !appOutput.contains("(1) 2595, Skylit Midtown Castle, 2845, Jennifer, Midtown, Entire home/apt, 225")) {
	      // test fails
	      fail("The program failed.");
	  } else {
	      //test passes
	      return;
	  }
      } catch (Exception e) {
	  // make sure stdin and stdout are set correctly after we get exception in test
	  System.setOut(standardOut);
	  System.setIn(standardIn);
	  e.printStackTrace();
	  // test failed
	  fail("The program failed.");
      }
  }
}

