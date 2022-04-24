package view;

import config.ConfigLogin;
import service.receiptService.ReceiptServiceIMPL;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class Main {



    public Main() throws ParseException {
        String LOGINPATH = "src/data/userLoginData.txt";
        new ConfigLogin().writeToFile(LOGINPATH,null);
        System.out.println("=================HOTEL MANAGEMENT=================");
        Scanner sc = new Scanner(System.in);
        System.out.println("1. REGISTER");
        System.out.println("2. LOGIN");
        System.out.println("==================================================");
        int choose = sc.nextInt();
        switch (choose){
            case 1: new Account().Register();
                break;
            case 2: new Account().Login();
                break;
            case 3: new Account().Logout();
        }
    }

    public static void main(String[] args) throws ParseException {
        new ReceiptServiceIMPL().setRoomStt();
        new Main();
    }
}
