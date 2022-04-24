package view;

import java.text.ParseException;
import java.util.Scanner;

public class StaffMenu {
    public StaffMenu() throws ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("====================STAFF MENU====================");
        System.out.println("1. ACCOUNT");
        System.out.println("2. MANAGE ROOOM");
        System.out.println("3. MANAGE RECEIPT");
        System.out.println("4. LOGOUT");
        System.out.println("==================================================");
        int choose = sc.nextInt();
        switch (choose) {
            case 1:
                new StaffAccount();
                break;
            case 2:
                new StaffRoomManage();
                break;
            case 3:
                new StaffReceiptManage();
                break;
            case 4:
                new Account().Logout();
                break;
        }
    }
}
