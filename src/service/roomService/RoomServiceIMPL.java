package service.roomService;

import config.ConfigReadAndWrite;
import controller.ReceiptController;
import model.Receipt;
import model.Room;
import model.RoomStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoomServiceIMPL implements IRoomService {
    public static String ROOMPATH = "src/data/roomData.txt";
    public static List<Room> roomList = new ConfigReadAndWrite<Room>().readFromFile(ROOMPATH);
    public static String RECEIPTPATH = "src/data/receiptData.txt";
    public static ConfigReadAndWrite<Room> configReadAndWriteRoom = new ConfigReadAndWrite<>();
    public static ConfigReadAndWrite<Receipt> configReadAndWriteReceipt = new ConfigReadAndWrite<>();
    //public static List<Room> roomList = configReadAndWriteRoom.readFromFile(ROOMPATH);
    public static List<Receipt> receiptList = configReadAndWriteReceipt.readFromFile(RECEIPTPATH);

    @Override
    public List<Room> findAll() {
        new ReceiptController().setRoomStt();
        new ConfigReadAndWrite<Room>().writeToFile(ROOMPATH, roomList);
        return roomList;
    }

    @Override
    public void save(Room room) {
        roomList.add(room);
        new ConfigReadAndWrite<Room>().writeToFile(ROOMPATH,roomList);
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < roomList.size(); i++) {
            if (roomList.get(i).getRoomId()==id){
                roomList.remove(roomList.get(i));
            }
        }
    }

    @Override
    public Room findById(int id) {
        for (int i = 0; i < roomList.size(); i++) {
            if (roomList.get(i).getRoomId()==id){
                return roomList.get(i);
            }
        }
        return null;
    }

    @Override
    public void editById(Room room) {
        for (int i = 0; i < roomList.size(); i++) {
            if (room.getRoomId()==roomList.get(i).getRoomId()){
                if (room.getPrice()== -1){
                }else {
                    roomList.get(i).setPrice(room.getPrice());
                }
                if (room.getNumberOfBedroom()==-1){
                }else roomList.get(i).setNumberOfBedroom(room.getNumberOfBedroom());

                if (room.getNumberOfToilet()==-1){
                }else roomList.get(i).setNumberOfToilet(room.getNumberOfToilet());

                new ConfigReadAndWrite<Room>().writeToFile(ROOMPATH,roomList);
            }
        }
    }

    public List<Room> findAvailableRoom(){
        List<Room> availRoomList = new ArrayList<>();
        for (int i = 0; i < roomList.size(); i++) {
            if (roomList.get(i).getRoomStatus()== RoomStatus.AVAILABLE){
                availRoomList.add(roomList.get(i));
            }
        }
//        int roomId = 0;
//        Date date = new Date();
//        for (int i = 0; i < receiptList.size(); i++) {
//            if (receiptList.get(i).getCheckOut().compareTo(date) >= 0 && receiptList.get(i).getCheckIn().compareTo(date) <= 0) {
//                roomId = receiptList.get(i).getRoom().getRoomId();
//                for (int j = 0; j < roomList.size(); j++) {
//                    if (roomId == roomList.get(j).getRoomId()) {
//                        //System.out.println("Change status " +roomList.get(j) +" to UNAVAILABLE");
//                        roomList.get(j).setRoomStatus(RoomStatus.UNAVAILABLE);
//                        configReadAndWriteRoom.writeToFile(ROOMPATH, roomList);
//                    }
//                }
//            } else {
//
//                roomId = receiptList.get(i).getRoom().getRoomId();
//                for (int j = 0; j < roomList.size(); j++) {
//                    if (roomId == roomList.get(j).getRoomId()) {
//                        //System.out.println("Change status " +roomList.get(j) +" to AVAILABLE");
//                        roomList.get(j).setRoomStatus(RoomStatus.AVAILABLE);
//                        configReadAndWriteRoom.writeToFile(ROOMPATH, roomList);
//                    }
//                }
//            }
//        }
        return availRoomList;
    }

    public List<Room> findByRange(double min, double max){
        new ReceiptController().setRoomStt();
        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < roomList.size(); i++) {
            if (roomList.get(i).getPrice()>=min && roomList.get(i).getPrice()<=max){
                rooms.add(roomList.get(i));
            }
        }
        return rooms;
    }
}
