import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
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
	/**
	 * This method adds new room to the csv file
	 * @param roomWriter is the FileWriter that write new room data to the original csv file
	 * @param newRoom contains the seven properties of the new room
	 * @return
	 */
	@Override
	public boolean addRoom(FileWriter roomWriter, String[] newRoom)
	{
		//check if the format of the data is correct
		if(newRoom.length != 7)
			return false;
		try {
			//add every property of the room with comma and space to the csv file
			for(int i = 0; i < newRoom.length-1; i++)
			{
				roomWriter.write(newRoom[i] + ", ");
			}
			//add the last property (the price) to the file and a new line to the csv file
			roomWriter.write(newRoom[newRoom.length-1] + "\n ");
			roomWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
