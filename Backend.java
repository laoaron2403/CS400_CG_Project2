// --== CS400 File Header Information ==--
// Name: Nitit Jongsawatsataporn
// Email: jongsawatsat@wisc.edu
// Team: red
// Role: Backend Developer
// TA: Xi
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.Reader;
import java.util.zip.DataFormatException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class implements BackendInterface
 * @author nitit
 */
public class Backend implements BackendInterface {
    
    SortedCollectionInterface<RoomInterface> dataBlock; //The set of RoomInterface elements with order
    List<String> allNeighboreHood; //All neighborhood 
    List<String> currentNeighboreHood; //Current neighborhood in the list
    List<Integer> bound; //The lowest-highest price in the list
    List<Integer> currentBound; //The lower-upper bound set by user
    List<String> allRoomType; //All room type
    List<String> currentRoomType; //Room types chosen by user
    List<RoomInterface> currentList; //Current list complied from user

    /**
     * This method initialized the backend structure. It will initialized all list and set it with the value 
     * read from the file. These set may change in the future as user control it from the frontend, but
     * it would be manage in the later function.
     * @param file The file we want to read
     * @throws FileNotFoundException If the file is not found
     * @throws IOException If the file is not manage properly
     * @throws DataFormatException If the csv file is wrongly formatted
     */
    public Backend(Reader file) throws FileNotFoundException, IOException, DataFormatException {
        RoomDataReaderInterface reader = new RoomDataReader(); //Create data reader
        /** Initialized the list to avoid nullpointerexception **/
        dataBlock = new RedBlackTree<RoomInterface>();
        allNeighboreHood = new ArrayList<String>();
        currentNeighboreHood = new ArrayList<String>();
        currentRoomType = new ArrayList<String>();
        currentList = new ArrayList<RoomInterface>();
        allRoomType = new ArrayList<String>();
        bound = new ArrayList<Integer>(Arrays.asList(10000000,0)); //Set initial lower and upper bound
                                                                   //Lower bound need to be too high and upper bound need to be too low
        currentBound = new ArrayList<Integer>(Arrays.asList(0,10000000)); //Automatically set to too extreme value
        List<Room> list = reader.readDataSet(file); //Get the list of RoomInterface to process
        for(RoomInterface room: list) {
            //Iterate through the whole list
            dataBlock.insert(room); //Add to set
            if(!allNeighboreHood.contains(room.getNeighborhoodName())) //Check if neighborhood exists or not
                allNeighboreHood.add(room.getNeighborhoodName()); //Add neighborhood
            if(room.getPrice() < bound.get(0))
                bound.set(0,room.getPrice()); //Set lower bound 
            if(room.getPrice() > bound.get(1))
                bound.set(1,room.getPrice()); //Set upper bound
            if(!allRoomType.contains(room.getRoomType())) //Check if nroom type exists or not
                allRoomType.add(room.getRoomType()); //Add room type
        }
    }

    /**
     * This method add neightborhood to the list we working on. Then it would call update to update 
     * the list
     * @parem neighborhood the neighborhood to be added
     */
    public void selectNeighborhood(String neighborhood) {
        if(allNeighboreHood.contains(neighborhood) && !currentNeighboreHood.contains(neighborhood))
            currentNeighboreHood.add(neighborhood);
        update(); //Helper method to update the list
    }

    /**
     * This method remove neightborhood to the list we working on. Then it would call update to update 
     * the list
     * @parem neighborhood the neighborhood to remove
     */
    public void unselectNeighborhood(String neighborhood) {
        if(currentNeighboreHood.contains(neighborhood))
            currentNeighboreHood.remove(neighborhood);
        update(); //Helper method to update the list
    }

    /**
     * This method return the list of current neighborhood used to make currentList of Room
     * @return list of string of current neighborhood
     */
    public List<String> getNeighborhoods() {
        return currentNeighboreHood;
    }

    /**
     * This method return the list of all neighborhood in the data base
     * @return list of string of all neighborhood
     */
    public List<String> getAllNeighborhoods() {
        return allNeighboreHood;
    }

    /**
     * This method return lowest-highest price in the database
     * @return list of integer lowest at .get(0) highest at .get(1)
     */
    public List<Integer> getPriceRange() {
        return bound; //lowest at .get(0) highest at .get(1)
    }

    /**
     * This method update lower bound that we use for making currentList (list of room) to present to user
     * @parem price the lower bound we want to set
     */
    public void setPriceLowerBound(int price) {
        currentBound.set(0,price);
    }

    /**
     * This method update upper bound that we use for making currentList (list of room) to present to user
     * @parem price the upper bound we want to set
     */
    public void setPriceUpperBound(int price) {
        currentBound.set(1,price);
    }

    /**
     * This method add room type to the list we working on. Then it would call update to update 
     * the list
     * @parem type the room type to be added
     */
    public void selectRoomType(String type) {
        if(allRoomType.contains(type) && !currentRoomType.contains(type))
            currentRoomType.add(type);
        update(); //Helper method to update the list
    }
    
    /**
     * This method remove room type to the list we working on. Then it would call update to update 
     * the list
     * @parem type the room type to be removed
     */
    public void unselectRoomType(String type) {
        if(currentRoomType.contains(type))
            currentRoomType.remove(type);
        update(); //Helper method to update the list
    }
    
    /**
     * This method return the list of all room type in the data base
     * @return list of string of all room type
     */
    public List<String> getAllRoomType() {
        return allRoomType;
    }

    /**
     * This method return three rooms starting at index startingIndex. If there is not enough room, 
     * it will return until the end of the list
     * @parem startingIndex the index of starting point
     * @return List of three room in increasing price
     */
    public List<RoomInterface> getThreeRooms(int startingIndex) {
        List<RoomInterface> threeRoom = new ArrayList<RoomInterface>(); //List to be returned
        for(int i = startingIndex;i < Math.min(currentList.size(),startingIndex+3);i++) //Get three or until the end of the list
            threeRoom.add(currentList.get(i));
        return threeRoom;
    }

    /**
     * This method return the number of room in the list
     * @return the number of room
     */
    public int getNumberOfRooms() {
        return currentList.size();
    }

    /**
     * This method is a hesrt of this program.
     * It will clear the old list and use iterator to iterate through the whole data base. As it move,
     * all member will check whether it satisfy requirements or not. If it satisfy, it will be added to the list.
     * Since the set keep elements in increasing order of price, this method will generate a list
     * with increasing price.
     */
    public void update() {
        currentList.clear(); //Remove all room from current list, and I will build a whole new one
        for(RoomInterface room: dataBlock) {
            //Use iterator to iterate through the dataBlock.
            //This will return in "non-decreasing" order
            if(currentNeighboreHood.contains(room.getNeighborhoodName())) {
                boolean select = true;
                //Check whether it is good or not
                if(currentRoomType.size() != 0 && !currentRoomType.contains(room.getRoomType())) //Room type check
                    select = false;
                if(currentBound.get(0) > room.getPrice() || currentBound.get(1) < room.getPrice()) //Price check
                    select = false;
                
                if(select) //No false check occur
                    currentList.add(room); //Add to the list
            }
        }
    }
    
    /**
     * Add new room to the RBT
     * @param room the room extent RoomInterface
     * @throws IllegalArgumentException if there's a exactly same room in the tree
     */
     public void addRoom(RoomInterface room) throws IllegalArgumentException {
         if(!dataBlock.insert(room)) //Return false == same room exists
             throw new IllegalArgumentException("Same room already exists"); //Throws exception
         if(!allNeighboreHood.contains(room.getNeighborhoodName())) //Check neighborhood
             allNeighboreHood.add(room.getNeighborhoodName()); //Add if it's a new one
         if(!allRoomType.contains(room.getRoomType())) //Check room type
             allRoomType.add(room.getRoomType()); //Add if it's a new one
         bound.set(0, Math.min(bound.get(0), room.getPrice())); //Set lower bound
         bound.set(1, Math.max(bound.get(1), room.getPrice())); //Set upper bound
         update(); //Update the list
     }
}
