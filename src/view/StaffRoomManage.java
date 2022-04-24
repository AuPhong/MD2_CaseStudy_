package view;

import config.ConfigReadAndWrite;
import controller.RoomController;
import model.Room;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class StaffRoomManage {
    public static String ROOMPATH = "src/data/roomData.txt";
    public static List<Room> roomList = new ConfigReadAndWrite<Room>().readFromFile(ROOMPATH);

    public void backToMenu() throws ParseException {
        System.err.println("Press any key to come back!");
        Scanner sc = new Scanner(System.in);
        String choose = sc.nextLine();
        switch (choose) {
            default:
                new StaffRoomManage();
        }
    }

    public StaffRoomManage() throws ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("================ROOM MANAGE================");
        System.out.println("1. SHOW ROOM LIST");
        System.out.println("2. SHOW AVAILABLE ROOMS");
        System.out.println("3. FIND ROOM BY PRICE RANGE");
        System.out.println("4.FIND ROOM BY ID");
        System.out.println("0. BACK TO MENU");
        System.out.println("===========================================");
        int choose = sc.nextInt();
        switch (choose) {
            case 1:
                new RoomController().showList();
                backToMenu();
            case 2:
                new RoomController().findAvailableRoom();
                backToMenu();
            case 3:
                System.out.println("Enter min range: ");
                double min = sc.nextDouble();
                sc.nextLine();
                System.out.println("Enter max range: ");
                double max = sc.nextDouble();
                sc.nextLine();
                new RoomController().findByPrice(min, max);
                backToMenu();
            case 4:
                System.out.println("Enter room's id to find: ");
                int roomId = sc.nextInt();
                sc.nextLine();
                new RoomController().findRoomById(roomId);
                backToMenu();
            case 0:
                new StaffMenu();
        }
    }
}
