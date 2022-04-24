package view;

import config.ConfigLogin;
import config.ConfigReadAndWrite;
import controller.ReceiptController;
import model.Customer;
import model.Receipt;
import model.Room;
import model.User;
import service.receiptService.ReceiptServiceIMPL;
import service.roomService.RoomServiceIMPL;
import service.staffService.UserServiceIMPL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class StaffReceiptManage {
    public void backToMenu() throws ParseException {
        System.err.println("Press any key to come back!");
        Scanner sc = new Scanner(System.in);
        String choose = sc.nextLine();
        switch (choose) {
            default:
                new StaffReceiptManage();
        }
    }

    public StaffReceiptManage() throws ParseException {
        String emailRegex = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
        String phonenumberRegex = "^0[0-9]{8,9}$";
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        Scanner sc = new Scanner(System.in);
        String RECEIPTPATH = "data/receiptData.txt";
        List<Receipt> receiptList = new ConfigReadAndWrite<Receipt>().readFromFile(RECEIPTPATH);
        String dateRegex = "\\d{2}\\s\\d{1,2}\\s\\d{4}";
        String ageRegex = "\\d{1,3}";
        System.out.println("==============Receipt manage==============");
        System.out.println("1. Show your receipt list");
        System.out.println("2. Add receipt");
        System.out.println("3. Edit your receipt list");
        System.out.println("4. Find receipt by ID");
        System.out.println("0. Comeback menu");
        System.out.println("==========================================");
        int choose = sc.nextInt();
        sc.nextLine();
        switch (choose) {
            case 1:
                ArrayList<Room> arrayList = new ArrayList<>();
                User loginUser = new ConfigLogin().readFromFile("data/userLoginData.txt");
                List<Receipt> receipts = new ArrayList<>();
                if (receiptList.equals(arrayList)) {
                    System.err.println("There is no receipt data!");
                } else {
                    for (int i = 0; i < receiptList.size(); i++) {
                        if (receiptList.get(i).getStaff().getUserName().equals(loginUser.getUserName())){
                            receipts.add(receiptList.get(i));
                        }
                    }
                    System.out.println(receipts);
                }
                backToMenu();
            case 2:
                List<Room> availableRooms = new RoomServiceIMPL().findAvailableRoom();
                List list = new ArrayList();
                if (availableRooms.equals(list)) {
                    System.err.println("There is no available room now, press any key to return!");
                    backToMenu();
                } else {
                    int id = 0;
                    if (receiptList.size() == 0) {
                        id = 1;
                    } else {
                        id = receiptList.get(receiptList.size() - 1).getReceiptId() + 1;
                    }
                    System.out.println("Enter customer information. ");
                    System.out.println("Enter customer's name: ");
                    String name = sc.nextLine();
                    System.out.println("Enter customer's age: ");
                    String age;
                    while (true) {
                        age = sc.nextLine();
                        if (Pattern.matches(ageRegex, age)) {
                            break;
                        } else {
                            System.err.println("This not an age, enter again: ");
                        }
                    }
                    System.out.println("Enter customer's address: ");
                    String address = sc.nextLine();
                    System.out.println("Enter customer's phone number: ");
                    String phonenumber = "";
                    while (true) {
                        phonenumber = sc.nextLine();
                        if (Pattern.matches(phonenumberRegex, phonenumber)) {
                            break;
                        } else {
                            System.err.println("Wrong phonenumber pattern, enter again: ");
                        }
                    }
                    System.out.println("Enter customer's email: ");
                    String email = "";
                    while (true) {
                        email = sc.nextLine();
                        if (Pattern.matches(emailRegex, email)) {
                            break;
                        } else {
                            System.err.println("Wrong email pattern, enter again: ");
                        }
                    }
                    Customer customer = new Customer(name, age, address, phonenumber, email);
                    User staff = new ConfigLogin().readFromFile("src/data/userLoginData.txt");
                    System.out.println("Choose from available room: ");
                    System.out.println(availableRooms);
                    System.out.println("Enter room's id:");
                    Room room = null;
                    int roomId = sc.nextInt();
                    sc.nextLine();
                    for (int i = 0; i < availableRooms.size(); i++) {
                        if (roomId == availableRooms.get(i).getRoomId()) {
                            room = availableRooms.get(i);
                        }
                    }
                    System.out.println("Enter checkin date (dd MM yy): ");
                    String dateIn;
                    while (true) {
                        dateIn = sc.nextLine();
                        if (Pattern.matches(dateRegex, dateIn)) {
                            break;
                        } else {
                            System.err.println("Wrong date pattern, enter again: ");
                        }
                    }
                    System.out.println("Enter checkout date (dd MM yy): ");
                    String dateOut;
                    while (true) {
                        dateOut = sc.nextLine();
                        if (Pattern.matches(dateRegex, dateOut)) {
                            break;
                        } else {
                            System.err.println("Wrong date pattern, enter again: ");
                        }
                    }

                    Date checkIn = myFormat.parse(dateIn);
                    Date checkOut = myFormat.parse(dateOut);
                    long diff = checkOut.getTime() - checkIn.getTime();
                    long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                    double totalPrice = days * room.getPrice();
                    Receipt receipt = new Receipt(id, customer, staff, room, checkIn, checkOut, totalPrice);
                    new ReceiptController().addReceipt(receipt);
                    new ReceiptController().setRoomStt();
                    backToMenu();
                }
            case 3:
                ArrayList<Room> arrayList1 = new ArrayList<>();
                User loginUser1 = new ConfigLogin().readFromFile("src/data/userLoginData.txt");
                List<Receipt> receipts1 = new ArrayList<>();
                if (receiptList.equals(arrayList1)) {
                    System.err.println("There is no receipt data!");
                } else {
                    for (int i = 0; i < receiptList.size(); i++) {
                        if (receiptList.get(i).getStaff().getUserName().equals(loginUser1.getUserName())){
                            receipts1.add(receiptList.get(i));
                        }
                    }
                    System.out.println(receipts1);
                }
                System.out.println("");
                System.out.println("||||Enter receipt's id to edit:|||");
                int chooseReceiptId = Integer.parseInt(sc.nextLine());
                boolean check = false;
                for (int i = 0; i < receipts1.size(); i++) {
                    if (chooseReceiptId==receipts1.get(i).getReceiptId()){
                        check = true;
                        break;
                    }
                }
                if (check){
                    Receipt receiptChange = new ReceiptController().findById(chooseReceiptId);
                    Customer customerChange = receiptChange.getCustomer();
                    User staffChange = receiptChange.getStaff();
                    Room roomChange = receiptChange.getRoom();
                    Date checkInChange = receiptChange.getCheckIn();
                    Date checkOutChange = receiptChange.getCheckOut();
                    double totalPriceChange = receiptChange.getTotalPrice();
                    while (true) {
                        System.err.println("Press any key to continue edit, 0 to comeback!");
                        String chooseNext = sc.nextLine();

                        if (chooseNext.equals("0")) {
                            new StaffReceiptManage();
                        } else {
                            System.out.print("1. Edit customer's information:\t\t");
                            System.out.print("2. Edit room's information:\t\t");
                            System.out.print("3. Edit checkin date:\t\t");
                            System.out.print("4. Edit checkout date:\n");
                            String chooseEdit = sc.nextLine();
                            switch (chooseEdit) {
                                case "1":
                                    System.out.println("Enter customer's name: ");
                                    String nameChange = sc.nextLine();
                                    customerChange.setName(nameChange);
                                    System.out.println("Enter customer's age: ");
                                    String ageChange = sc.nextLine();
                                    customerChange.setAge(ageChange);
                                    System.out.println("Enter customer's address: ");
                                    String addressChange = sc.nextLine();
                                    customerChange.setAddress(addressChange);
                                    System.out.println("Enter customer's phone: ");
                                    String phoneChange = sc.nextLine();
                                    customerChange.setPhonenumber(phoneChange);
                                    System.out.println("Enter customer's email: ");
                                    String emailChange = sc.nextLine();
                                    customerChange.setAge(emailChange);
                                    Receipt receipt = new Receipt(chooseReceiptId, customerChange, staffChange, roomChange, checkInChange, checkOutChange, totalPriceChange);
                                    new ReceiptController().editById(receipt);
                                    break;
                                case "2":
                                    List<Room> availRoomToChange = new RoomServiceIMPL().findAvailableRoom();
                                    if (availRoomToChange.size() == 0) {
                                        System.err.println("There is no room to change!");
                                        break;
                                    } else {
                                        System.out.println("Enter room's id to change:");
                                        int roomIdChange = Integer.parseInt(sc.nextLine());
                                        for (int i = 0; i < new RoomServiceIMPL().findAll().size(); i++) {
                                            if (roomIdChange == new RoomServiceIMPL().findAll().get(i).getRoomId()) {
                                                roomChange = new RoomServiceIMPL().findAll().get(i);
                                            }
                                        }
                                    }
                                    receiptChange.setRoom(roomChange);
                                    Date checkIn1 = receiptChange.getCheckIn();
                                    Date checkOut1 = receiptChange.getCheckOut();
                                    long diff = checkOut1.getTime() - checkIn1.getTime();
                                    long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                                    double totalPrice = days * roomChange.getPrice();
                                    receiptChange.setTotalPrice(totalPrice);
                                    new ReceiptController().editById(receiptChange);
                                    break;

                                case "3":
                                    System.out.println("Enter new checkin date: ");
                                    String checkInChange1 = "";
                                    while (true) {
                                        checkInChange1 = sc.nextLine();
                                        if (Pattern.matches(dateRegex, checkInChange1)) {
                                            break;
                                        } else {
                                            System.err.println("Wrong date pattern, enter again: ");
                                        }
                                    }
                                    Date dateCheckIn = myFormat.parse(checkInChange1);
                                    receiptChange.setCheckIn(dateCheckIn);
                                    Date checkOut2 = receiptChange.getCheckOut();
                                    long diff1 = checkOut2.getTime() - dateCheckIn.getTime();
                                    long days1 = TimeUnit.DAYS.convert(diff1, TimeUnit.MILLISECONDS);
                                    double totalPrice1 = days1 * roomChange.getPrice();
                                    receiptChange.setTotalPrice(totalPrice1);
                                    new ReceiptController().editById(receiptChange);
                                    break;
                                case "4":
                                    System.out.println("Enter new checkout date: ");
                                    String checkOutChange1 = "";
                                    while (true) {
                                        checkOutChange1 = sc.nextLine();
                                        if (Pattern.matches(dateRegex, checkOutChange1)) {
                                            break;
                                        } else {
                                            System.err.println("Wrong date pattern, enter again: ");
                                        }
                                    }
                                    Date dateCheckOut = myFormat.parse(checkOutChange1);
                                    receiptChange.setCheckOut(dateCheckOut);
                                    Date checkIn = receiptChange.getCheckIn();
                                    long diff2 = dateCheckOut.getTime() - checkIn.getTime();
                                    long days2 = TimeUnit.DAYS.convert(diff2, TimeUnit.MILLISECONDS);
                                    double totalPrice2 = days2 * roomChange.getPrice();
                                    receiptChange.setTotalPrice(totalPrice2);
                                    new ReceiptController().editById(receiptChange);
                                    break;
                            }
                        }
                    }
                } else {
                    System.err.println("You cannot edit this receipt!");
                }
                backToMenu();
            case 0:
                new StaffMenu();

        }
    }
}
