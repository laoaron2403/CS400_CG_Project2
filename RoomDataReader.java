import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

public class RoomDataReader implements RoomDataReaderInterface{

	/**
	 * This method read the data and form a List of Room objects based on the csv file
	 * @param inputFileReader that has read the file and read to be manipulated
	 * @return a list of Rooms interpreted from the csv file
	 */
	@Override
	public ArrayList<Room> readDataSet(Reader inputFileReader)
			throws FileNotFoundException, IOException, DataFormatException {
		//initialize the list to be returned
		ArrayList<Room> rooms = new ArrayList<Room>();
		//initialize the temporary storage of the properties of each movie at each line
		String roomInfo = "";
		//specify each character in the form of int (can be converted to char)
		int current;
		
		//Since the first line needs to be ignored,
			//read the stream through the first line
		while((char)inputFileReader.read() != '\n');
		
		//read through the remaining contents from the reader
        while((current = inputFileReader.read()) != -1)
        {
        	//case 1: it is at the end of the line
        	if((char)current == '\n' )
        	{
        		//summarize the line by storing properties separated 
        			//by ", " into a list of Strings
    			String[] properties = roomInfo.split(",(\\s)*");
    			//if the number of properties exceeds 7
    			if(properties.length > 7)
    				//exception will be thrown
    				throw new DataFormatException("Too many columns");
    			//create 
    			Room oneRoom = new Room(properties);
                rooms.add(oneRoom);
                //clear the room information to get ready for the new room 
                roomInfo = "";
            }
        	//case 2: it is reading at the middle/not at the end of the line
        	else
        	{
        		//add the information being read to the temporary container
        		roomInfo += (char)current;
        	}
        }
        //close the file reader
        inputFileReader.close();
        //return the list of rooms
        return rooms;
	}
}
