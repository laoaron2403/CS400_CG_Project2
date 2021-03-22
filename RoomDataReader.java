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
		boolean open = false;
		
		//Since the first line needs to be ignored,
			//read the stream through the first line
		while((char)inputFileReader.read() != '\n');
		
		//read through the remaining contents from the reader
        while((current = inputFileReader.read()) != -1)
        {
            if(!open && (char)current == '\"')
                open  = true;
            else if(open && (char)current == '\"')
                open = false;
            
            if(open && (char)current == '\n') {
                continue;
            }
        	//case 1: it is at the end of the line
        	if(!open && (char)current == '\n' )
        	{
        		//summarize the line by storing properties separated 
        			//by ", " into a list of Strings
    			String[] sp = roomInfo.split(",(\s)*");
    			ArrayList<String>[] raw_data = (ArrayList<String>[]) new ArrayList[7];
    			String[] complieData = new String[7];
                for(int i = 0; i < 7; ++i) raw_data[i] = new ArrayList<String>();
                int cur = 0;
                int len = sp.length;
                for(int i = 0; i < len; ++i){
                    if(cur > 6){
                        throw new DataFormatException("Too many columns");
                    }
                    if(!sp[i].isEmpty() && sp[i].charAt(0) == '\"'){
                        int j;
                        for(j = i; j < len && sp[j].charAt(sp[j].length()-1) != '\"'; ++j);
                        if(i == j){
                            raw_data[cur].add(sp[i].substring(1, sp[i].length()-1));
                        }else{
                            raw_data[cur].add(sp[i].substring(1));
                            for(int k = i+1; k < j; ++k){
                                raw_data[cur].add(sp[k]);
                            }
                            
                            raw_data[cur].add(sp[j].substring(0, sp[j].length()-1));
                        }
                        i = j;
                        ++cur;
                    }else{
                        raw_data[cur++].add(sp[i]);
                    }
                }
    			for(int i=0;i<7;i++) {
    			    complieData[i] = new String();
    			    for(String it: raw_data[i]) {
    			        complieData[i] = complieData[i] + it;
    			    }
    			}
    			//create 
    			Room oneRoom = new Room(complieData);
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
			roomWriter.write(newRoom[newRoom.length-1] + "\n");
			roomWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
