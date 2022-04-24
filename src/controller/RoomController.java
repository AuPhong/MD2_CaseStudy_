package controller;

import model.Room;
import service.roomService.RoomServiceIMPL;

public class RoomController {
    public void showList() {
        System.out.println(new RoomServiceIMPL().findAll());
    }

    public void addRoom(Room room) {
        new RoomServiceIMPL().save(room);
    }

    public void deleteRoom(int id) {
        if (new RoomServiceIMPL().findById(id) == null) {
            System.err.println("This room is not exist.");
        } else {
            new RoomServiceIMPL().deleteById(id);
        }
    }

    public void findRoomById(int id) {
        Room room = new RoomServiceIMPL().findById(id);
        if (room == null) {
            System.err.println("This room is not exist.");
        } else {
            System.out.println(room);
        }
    }

    public void editById(Room room) {
        new RoomServiceIMPL().editById(room);
    }

    public void findAvailableRoom() {
        if (new RoomServiceIMPL().findAvailableRoom() == null) {
            System.err.println("There is no available room now!");
        } else {
            System.out.println(new RoomServiceIMPL().findAvailableRoom());
        }
    }

    public void findByPrice(double min, double max) {
        if (new RoomServiceIMPL().findByRange(min, max) == null) {
            System.err.println("There is no room between this price range!");
        } else {
            System.out.println(new RoomServiceIMPL().findByRange(min, max));
        }
    }
}
