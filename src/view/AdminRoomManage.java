package view;

import config.ConfigReadAndWrite;
import controller.ReceiptController;
import controller.RoomController;
import jdk.internal.dynalink.linker.LinkerServices;
import model.Room;
import model.RoomStatus;
import service.roomService.RoomServiceIMPL;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class AdminRoomManage {
    public static String ROOMPATH = "src/data/roomData.txt";
    //public static List<Room> roomList = new ConfigReadAndWrite<Room>().readFromFile(ROOMPATH);
    public void backToMenu() throws ParseException {
        System.err.println("Press any key to come back!");
        Scanner sc = new Scanner(System.in);
        String choose = sc.nextLine();
        switch (choose) {
            default:
                new AdminRoomManage();
        }
    }

    public AdminRoomManage() throws ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("================Room manage================");
        System.out.println("1. SHOW ROOM'S LIST");
        System.out.println("2. ADD ROOM");
        System.out.println("3. EDIT ROOM");
        System.out.println("4. SHOW AVAILABLE ROOM");
        System.out.println("5. FIND ROOM BY PRICE RANGE");
        System.out.println("6. FIND ROOM BY ID");
        System.out.println("7. DELETE ROOOM");
        System.out.println("0. BACK TO MENU");
        System.out.println("===========================================");

        int choose = sc.nextInt();
        switch (choose) {
            case 1:
                new RoomController().showList();
                backToMenu();
            case 2:
                int id = 0;
                if (new RoomServiceIMPL().findAll().size() == 0) {
                    id = 1;
                } else {
                    id = new RoomServiceIMPL().findAll().get(new RoomServiceIMPL().findAll().size() - 1).getRoomId() + 1;
                }
                System.out.println("Enter room price: ");
                double price = sc.nextDouble();
                sc.nextLine();
                RoomStatus roomStatus = RoomStatus.AVAILABLE;
                System.out.println("Enter number of Bedrooms: ");
                int numBedroom = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter number of Toilets: ");
                int numToilet = sc.nextInt();
                sc.nextLine();
                Room room = new Room(id, price, roomStatus, numBedroom, numToilet);
                new RoomController().addRoom(room);
                backToMenu();
            case 3:
                System.out.println("Enter room's id to edit");
                int editId = sc.nextInt();
                sc.nextLine();
                if (new RoomServiceIMPL().findById(editId) == null) {
                    System.err.println("This room is not exist!");
                    new AdminRoomManage();
                } else {
                    RoomStatus roomStatus1 = new RoomServiceIMPL().findById(editId).getRoomStatus();
                    System.out.println("Enter editing price: ");
                    double editPrice = sc.nextDouble();
                    sc.nextLine();
                    System.out.println("Enter number of bedrooms to edit: ");
                    int editNumBed = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter numbers of toilets to edit: ");
                    int editNumToil = sc.nextInt();
                    sc.nextLine();
                    Room room1 = new Room(editId, editPrice, roomStatus1, editNumBed, editNumToil);
                    new RoomController().editById(room1);
                    backToMenu();
                }
            case 4:
                new RoomController().findAvailableRoom();
                backToMenu();
            case 5:
                System.out.println("Enter min range: ");
                double min = sc.nextDouble();
                sc.nextLine();
                System.out.println("Enter max range: ");
                double max = sc.nextDouble();
                sc.nextLine();
                new RoomController().findByPrice(min, max);
                backToMenu();
            case 6:
                System.out.println("Enter room's id to find: ");
                int roomId = sc.nextInt();
                sc.nextLine();
                new RoomController().findRoomById(roomId);
                backToMenu();
            case 7:
                System.out.println("Enter room's id to delete: ");
                int deleteId = sc.nextInt();
                sc.nextLine();
                if (new RoomServiceIMPL().findById(deleteId)==null){
                    System.err.println("This room does not exist!");
                    backToMenu();
                }else {
                    new RoomController().deleteRoom(deleteId);
                    backToMenu();
                }

            case 0:
                new AdminMenu();
        }
    }
}
