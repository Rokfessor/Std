package Lab4;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private final List<Room> rooms;
    public Building() {
        rooms = new ArrayList<>();
    }
    public void addRoom(Room room) {
        rooms.add(room);
    }
    public void printInfo() {
        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            String type = "";
            if (room instanceof Office)
                type = "office";

            if (room instanceof Flat)
                type = "flat";

            System.err.println("On " + (i + 1) + " floor placed " + type + ". Info " + room.getInfo());
        }
    }
    public void printCountInfo() {
        int officeCount = 0;
        int flatCount = 0;

        for (Room room : rooms) {
            if (room instanceof Office)
                officeCount++;
            if (room instanceof Flat)
                flatCount++;
        }

        System.err.println(
                "Flats count: " + flatCount + "\n" +
                "Offices count: " + officeCount + "\n" +
                "Common count " + rooms.size());
    }
}
