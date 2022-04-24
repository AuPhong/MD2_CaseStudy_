package view;

import java.text.ParseException;
import java.util.Scanner;

public class AdminMenu {

    public AdminMenu() throws ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("====================ADMIN MENU====================");
        System.out.println("1. Account");
        System.out.println("2. Manage room");
        System.out.println("3. Manage receipt");
        System.out.println("4. Logout");
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
