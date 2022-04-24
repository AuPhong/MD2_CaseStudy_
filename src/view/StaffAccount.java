package view;

import config.ConfigLogin;
import controller.UserController;
import model.Role;
import model.User;

import java.text.ParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class StaffAccount {
    public void backToMenu() throws ParseException {
        System.err.println("Press any key to come back!");
        Scanner sc = new Scanner(System.in);
        String choose = sc.nextLine();
        switch (choose) {
            default:
                new StaffAccount();
        }
    }
    public StaffAccount() throws ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("===============ACCOUNT MANAGE===============");
        System.out.println("1. SHOW YOUR INFO");
        System.out.println("2. EDIT YOUR INFO");
        System.out.println("0. BACK TO MENU");
        System.out.println("============================================");
        //System.out.println("4. Logout");
        int choose = sc.nextInt();
        sc.nextLine();
        String emailRegex = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
        String accountRegex = "^([a-z]|[0-9]){8,16}$";
        String phonenumberRegex = "^0[0-9]{8,9}$";
        String passwordRegex = "^[A-Za-z0-9]{8,16}$";
        switch (choose){
            case 1:
                new UserController().showInfo();
                backToMenu();
            case 2:
                int id = new ConfigLogin().readFromFile("src/data/userLoginData.txt").getId();
                System.out.println("Enter username to change:");
                String username;
                boolean checkUsernameRegex;
                while (true) {
                    username = sc.nextLine();
                    checkUsernameRegex = Pattern.matches(accountRegex, username);
                    if (checkUsernameRegex || username.equals("")) {
                        break;
                    } else {
                        System.err.println("Wrong account pattern, enter again: ");
                    }
                }

                System.out.println("Enter password to change");
                String password;
                boolean checkPasswordRegex;
                while (true) {
                    password = sc.nextLine();
                    checkPasswordRegex = Pattern.matches(passwordRegex, password);
                    if (checkPasswordRegex || password.equals("")) {
                        break;
                    } else {
                        System.err.println("Wrong password pattern, enter again: ");
                    }
                }
                System.out.println("Enter email to change");
                String email;
                boolean checkEmailRegex;
                while (true) {
                    email = sc.nextLine();
                    checkEmailRegex = Pattern.matches(emailRegex, email);
                    if (checkEmailRegex || email.equals("")) {
                        break;
                    } else {
                        System.err.println("Wrong email pattern, enter again: ");
                    }
                }

                System.out.println("Enter phonenumber to change: ");
                String phonenumber;
                boolean checkPhonenumberRegex;
                while (true) {
                    phonenumber = sc.nextLine();
                    checkPhonenumberRegex = Pattern.matches(phonenumberRegex, phonenumber);
                    if (checkPhonenumberRegex || phonenumber.equals("")) {
                        break;
                    } else {
                        System.err.println("Wrong phonenumber pattern, enter again: ");
                    }
                }
                Role role = Role.STAFF;
                User user = new User(id, username, password, email, phonenumber, role);
                new UserController().editInfo(user);
                new UserController().showInfo();
                backToMenu();
            case 0:
                new StaffMenu();
                break;
            case 4:
                new Account().Logout();
                break;
        }
    }
}
