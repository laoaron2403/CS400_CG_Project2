// --== CS400 File Header Information ==--
// Name: Xizheng Yu
// Email: xyu354@wisc.edu
// Team: CG
// TA: Xi Chen
// Lecturer: Gary Dahl
// Notes to Grader: None

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class runs the whole program
 * @author yuxizheng
 */
public class Frontend {
    //Preparation of Backend
    int mode;
    BackendInterface backend;
    //Frontend lists
    List<String> neighborhoods; // List of name of all neighborhoods
    List<String> types; // List of name of all room types
    List<Boolean> nSelect; // List of boolean whether neighborhood is selected or not
    List<Boolean> tSelect; // List of boolean whether room types is selected or not
    // Other stuff
    Scanner in; // Scanner to be used from user
    boolean changePage = true; // Detect whether we loop to the same page or different one
    int position = 1; // Keep position of main page
    
    
    // This part is for debugging.
    public static void main(String[] args) {
	Object frontend =  new Frontend();
	try {
	    FileReader inputFileReader = new FileReader("airbnb.csv");
	    ((Frontend)frontend).run(new Backend(inputFileReader));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    /**
     * The construtor. This will do nothing but initialized all value with null. It
     * will set those value again when you run the program.
     */
    public Frontend() {

    }
    
    /**
     * This method is taken from outside source. This would clear the terminal
     */
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    /**
     * This method runs this program and loads data
     * @param backend
     */
    public void run(BackendInterface backend) {
        mode = 0; // Set in base mode
        this.backend = backend;
        neighborhoods = backend.getAllNeighborhoods(); // Get all possible Neighborhoods
        types = backend.getAllRoomType(); // Get all possible Room types
        
        //load selection array
        nSelect = new ArrayList<Boolean>();
        for (int i = 0; i < neighborhoods.size(); i++)
            nSelect.add(false);
        tSelect = new ArrayList<Boolean>(); 
        for (int i = 0; i < types.size(); i++) 
            tSelect.add(false);
        in = new Scanner(System.in);
        run();
    }
    
    /**
     * This a helper method to run the program. It will look up which page it should
     * navigated to then calling the method associated to that page.
     */
    private void run() {
        clearScreen(); // Reset the screen to blank
        if (mode == 0) // Main page
            mainPage();
        else if (mode == 1) // Neighborhood Page
            neighborhoodPage();
        else if (mode == 2) // Price range Page
            priceRangePage();
        else if (mode == 3) // Room type Page
            roomTypePage();
        else if (mode == 4)
            roomAdderPage();
    }
    
    /**
     * This is the main page of the program, include functions of:
     * presenting top three rooms that user chose
     * navigate to price range page/neighborhood page/room type page
     * and terminate program
     */
    private void mainPage() {
        //If come from other page, it will reset the position
        if (changePage)
            position = 1;
        changePage = false;
        boolean correctInput = false; //A boolean checking whether input is correct or not
        
        //Welcome Message
        System.out.println("---------Welcome to Airbnb System Manager by CG Red group!---------");
        System.out.println("-----------------------This is main page---------------------------");
        System.out.println("The current selection of rooms with neighborhoods, price ranges, and room types is here.");
        System.out.println("The total number of rooms with selected neighborhoods or price ranges is " + backend.getNumberOfRooms());
        List<RoomInterface> curList = backend.getThreeRooms(position - 1); //Asking backend for list of rooms
        System.out.println();
        
        if (!(curList == null || backend.getNumberOfRooms() == 0 || curList.size() == 0)) {
            for (int i = 0; i < curList.size(); i++) {
                RoomInterface room = curList.get(i); //Get ith room
                System.out.println("(" + (position + i) + ") " + room.getRoomId() + ", " +
                	room.getName() + ", "+ room.getHostId() + ", "+ room.getHostName() + ", "+ 
                	room.getNeighborhoodName() + ", "+ room.getRoomType() + ", "+ room.getPrice());
            }
        } else {
          //Error message in case it return null list or list with 0 members.
          System.out.println("There is no room that have neighborhoods/price/types you selected");
        }
        System.out.println();
        
        //Display the possible command
        System.out.println("Select the ranking by inputing the number");
        System.out.println("You can naviagate to Neighborhoods selection by press n.");
        System.out.println("Or navigate to Price Ranges selection by press p.");
        System.out.println("Or navigate to Room Types selection by press t.");
        System.out.println("Or navigate to Room Adder page by press a.");
        System.out.println("Or terminate the program by press x.");
        System.out.println("Wrong input might leads to unexpected behevior.");
        
        //Loop until we get the correct input
        while (!correctInput) {
            correctInput = true; //Set to true. Will change to false if format is wrong
            System.out.println("Please input the command:");
            String input = in.nextLine();
            if (input.contains("x")) {
                //If contains x, then it would display bye bye message and return out
                System.out.println("Thank you for using program!");
                return;
            } else if (input.contains("n")) {
                //Navigate to the neighborhood page
                mode = 1;
                changePage = true; //To show that this come from different page
            } else if (input.contains("p")) {
                //Navigate to the price ranges page
                mode = 2;
                changePage = true; //To show that this come from different page
            } else if (input.contains("t")) {
                //Navigate to the room type page
                mode = 3;
                changePage = true; //To show that this come from different page
            } else if (input.contains("a")) {
        	//Navigate to the room adder page
                mode = 4;
                changePage = true; //To show that this come from different page
            } else {
                try {
                    int rank = Integer.parseInt(input); //Convert it to integer
                    position = rank;
                } catch (Exception e) {
                    //This means the output is not integer. It's wrong formatted!
                    System.out.println("The format is wrong!");
                    correctInput = false;
                }
            }
        }
        //Use run() to navigate to another/same page
        run();
    }
    
    /**
     * This page allows users to select neighborhoods that they intended to live in
     * The page will present all neighborhoods
     * Before neighborhood names is the number that user would use to choose neighborhoods
     * After neighborhood names are the status of selection
     * Type the number will change the status
     */
    private void neighborhoodPage() {
        boolean correctInput = false;
        //Welcome message for page
        System.out.println("Welcome to neighborhood selection page!");
        System.out.println("The following are the neighborhoods you can select/deselect");
        System.out.println("Please select the neighborhood by typing number according to neighborhood.");
        
        //Display neighborhoods either selected or not
        for (int i = 0; i < neighborhoods.size(); i++) {
            System.out.print("(" + (i + 1) + "): " + neighborhoods.get(i) + " (");
            if (!nSelect.get(i)) {
                System.out.print("not selected");
            } else {
                System.out.print("already selected");
            }
            System.out.println(")");
        }
        System.out.println("----------------------------------------------");
        System.out.println("Press x to navigate back to main page.");
        
        //Loop until getting correct input
        while (!correctInput) {
            correctInput = true;
            System.out.println("Please input the number for neighborhoods you want select. x back to main page");
            String input = in.nextLine();
            if (!input.contains("x")) {
                try {
                    int pos = Integer.parseInt(input); //Convert it to integer
                    pos--;
                    if (pos >= neighborhoods.size() || pos < 0) {
                        //This means the integer inputed is out of bound
                        correctInput = false;
                        System.out.println("Data Out of Range");
                    } else {
                        if (nSelect.get(pos) == false) {
                            nSelect.set(pos, true); //Set neighborhood to true
                            backend.selectNeighborhood(neighborhoods.get(pos)); //Add that neighborhood for backend
                        } else {
                            nSelect.set(pos, false); //Set this genre to false
                            backend.unselectNeighborhood(neighborhoods.get(pos)); //Remove that neighborhood from backend
                        }
                    }
                } catch (Exception e) {
                    //This means the input is not integer. Wrong formatted!
                    correctInput = false;
                    System.out.println("The format is wrong");
                }
            } else {
                mode = 0; //Goes back to main page if it contains x
            }
        }
        
        //Use run() to navigate to another/same page
        run();
    }
    
    /**
     * This page allows users to set price range that they are willing to pay
     * Press s to start input upper bound and lower bound
     */
    private void priceRangePage() {
        boolean correctInput = false;
        //Welcome message for page
        System.out.println("Welcome to price range selection page!");
        System.out.println("The following are the price ranges you can select/deselect");
        System.out.println("Please select the price range by typing number according to neighborhood.");
        
        //Display price ranges either selected or not
        System.out.println("The current price bound is: " + backend.getCurrentPriceRange().get(0) + "$ - " + backend.getCurrentPriceRange().get(1) + "$");
        System.out.println("----------------------------------------------");
        System.out.println("Press s to set upper bound and lower bound of price range.");
        System.out.println("Press x to navigate back to main page.");
        
        //Loop until getting correct input
        while (!correctInput) {
          correctInput = true;
          System.out.println("Please input the command:");
          String input = in.nextLine();
          if (input.contains("x")) {
              mode = 0; //Goes back to main page if it contains x
          } else if (input.contains("s")) {
              try {
                  System.out.print("Set upper bound: ");
                  String upper = in.nextLine();
                  int upperInt = Integer.parseInt(upper);
                  backend.setPriceUpperBound(upperInt);
                  System.out.print("Set lower bound: ");
                  String lower = in.nextLine();
                  int lowerInt = Integer.parseInt(lower);
                  backend.setPriceLowerBound(lowerInt);
              } catch (Exception e) {
                  //This means the input is not integer. Wrong formatted!
                  correctInput = false;
                  System.out.println("The format is wrong");
              } 
          } else {
              //This means the input is either s or x
              correctInput = false;
              System.out.println("The format is wrong");
          }
        }
        //Use run() to navigate to another/same page
        run();
    }
    
    /**
     * This page allows users to select room type that they intended to live in
     * The page will present all room types
     * Before room types is the number that user would use to choose room type
     * After room types are the status of selection
     * Type the number will change the status
     */
    private void roomTypePage() {
        boolean correctInput = false;
        //Welcome message for page
        System.out.println("Welcome to room type selection page!");
        System.out.println("The following are the room types you can select/deselect");
        System.out.println("Please select the room type by typing number according to the room types.");
        
        //Display room types either selected or not
        for (int i = 0; i < types.size(); i++) {
            System.out.print("(" + (i + 1) + "): " + types.get(i) + " (");
            if (!tSelect.get(i)) {
                System.out.print("not selected");
            } else {
                System.out.print("already selected");
            }
            System.out.println(")");
        }
        System.out.println("----------------------------------------------");
        System.out.println("Press x to navigate back to main page.");
        
        //Loop until getting correct input
        while (!correctInput) {
            correctInput = true;
            System.out.println("Please input the number for room type you want select. x back to main page");
            String input = in.nextLine();
            if (!input.contains("x")) {
                try {
                    int pos = Integer.parseInt(input); //Convert it to integer
                    pos--;
                    if (pos >= types.size() || pos < 0) {
                        //This means the integer inputed is out of bound
                        correctInput = false;
                        System.out.println("Data Out of Range");
                    } else {
                        if (tSelect.get(pos) == false) {
                            tSelect.set(pos, true); //Set room type to true
                            backend.selectRoomType(types.get(pos)); //Add that room type for backend
                        } else {
                            tSelect.set(pos, false); //Set rom type to false
                            backend.unselectRoomType(types.get(pos)); //Remove that room type from backend
                        }
                    }
                } catch (Exception e) {
                    //This means the input is not integer. Wrong formatted!
                    correctInput = false;
                    System.out.println("The format is wrong");
                }
            } else {
                mode = 0; //Goes back to main page if it contains x
            }
        }
        //Use run() to navigate to another/same page
        run();
    }
    
    /**
     * This page allows users to add new room
     * Press s to start input room information
     * new data will be load after restart the application
     * incorrect format will stop adding and back to this page
     */
    private void roomAdderPage() {
	boolean correctInput = false;
        //Welcome message for page
        System.out.println("Welcome to add room page!");
        //Loop until getting correct input
        while (!correctInput) {
            System.out.println("Press s to start add room, or press x to back to the main page");
            correctInput = true;
            String input = in.nextLine();
            if (!input.contains("x")) {
                if (input.contains("s")) {
                    try {
                	String[] toWrite = new String[7];
                	System.out.print("Room ID:");
                	toWrite[0] = in.nextLine();
                	Integer.parseInt(toWrite[0]); //test whether integer id
                	//toWrite[0] = "\n" + toWrite[0];
                	System.out.print("Name:");
                	toWrite[1] = in.nextLine();
                	System.out.print("Host ID:");
                	toWrite[2] = in.nextLine();
                	Integer.parseInt(toWrite[2]); //test whether integer input
                	System.out.print("Host Name:");
                	toWrite[3] = in.nextLine();
                	System.out.print("Neighborhood:");
                	toWrite[4] = in.nextLine();
                	System.out.print("Room Type:");
                	for (int i = 0; i < types.size(); i++) {
                            System.out.print("(" + (i + 1) + "): " + types.get(i) + " ");  
                        }
                	toWrite[5] = in.nextLine();
                	int temp = Integer.parseInt(toWrite[5]); //test whether input id
                	if (temp > types.size() || temp < 0) {
                	    throw new IllegalArgumentException("Incorrect Selection!"); 
                	}
                	toWrite[5] = types.get(temp-1);
                	System.out.print("Price");
                	toWrite[6] = in.nextLine();
                	Integer.parseInt(toWrite[6]); //test whether input input
                	RoomDataReader readerToWrite = new RoomDataReader();
                	FileWriter roomWriter = new FileWriter("airbnb.csv",true);
                	readerToWrite.addRoom(roomWriter, toWrite);
                	afterAddPage(toWrite);
		    } catch (Exception e) {
			correctInput = false;
	                System.out.println("The format is wrong");
		    }
        	} else {
        	    //This means the input either s or x. Wrong formatted!
                    correctInput = false;
                    System.out.println("The format is wrong");
        	}
            } else {
                mode = 0; //Goes back to main page if it contains x
            }
        }
        //Use run() to navigate to another/same page
        run();
    }
    
    /**
     * This page will print the information being added to the csv file
     * @param a The input data array
     */
    private void afterAddPage(String[] a) {
	clearScreen(); // Reset the screen to blank
	System.out.println("Successfully Added, The Room Information is:");
	System.out.println("Room ID: " + a[0]);
	System.out.println("Name: " + a[1]);
	System.out.println("Host ID: " + a[2]);
	System.out.println("Host Name: " + a[3]);
	System.out.println("Neighborhood: " + a[4]);
	System.out.println("Room Type: " + a[5]);
	System.out.println("Price: " + a[6]);
	System.out.println("Your new data will load after restart this application");
	System.out.println("Press anything to back to the main page");
	String input = in.nextLine();
	mode = 0;
	run();
    }
}
