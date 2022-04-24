package view;

import java.text.ParseException;
import java.util.Scanner;

public class AdminMenu {

    public AdminMenu() throws ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("====================ADMIN MENU====================");
        System.out.println("1. ACCOUNT");
        System.out.println("2. MANAGE ROOM");
        System.out.println("3. MANAGE RECEIPT");
        System.out.println("4. LOGOUT");
        System.out.println("==================================================");
        int choose = sc.nextInt();
        switch (choose) {
            case 1:
                new AdminAccount();
                break;
            case 2:
                new AdminRoomManage();
                break;
            case 3:
                new AdminReceiptManage();
                break;
            case 4:
                new Account().Logout();
                break;
        }
    }
}
