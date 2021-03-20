
public class Room implements RoomInterface{
	private String[] roomData;
	// [0] = room_id, [1] = (room) name, [2] = host_id, [3] = host_name, [4] = neighborhood, [5] = room_type, [6] = price
	Room(String[] rooms)
	{
		roomData = rooms;
	}
	
	@Override
	public String getRoomId() {
		return this.roomData[0];
	}

	@Override
	public String getName() {
		return this.roomData[1];
	}

	@Override
	public String getHostId() {
		return this.roomData[2];
	}

	@Override
	public String getHostName() {
		return this.roomData[3];
	}

	@Override
	public String getNeighborhoodName() {
		return this.roomData[4];
	}

	@Override
	public String getRoomType() {
		return this.roomData[5];
	}

	@Override
	public int getPrice() {
		return Integer.parseInt(this.roomData[6]);
	}

	@Override
	public int compareTo(RoomInterface otherRoom) {
	    if(this.getPrice() != otherRoom.getPrice())
	        return this.getPrice()-otherRoom.getPrice();
	    return this.getName().compareTo(otherRoom.getName());
	}

	@Override
	public boolean equals(RoomInterface otherRoom) {
		if(this.getName().equals(otherRoom.getName()) && this.getName().equals(otherRoom.getName()))
			return true;
		return false;
	}
}
