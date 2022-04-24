package view;

import config.ConfigLogin;
import config.ConfigReadAndWrite;
import controller.ReceiptController;
import controller.UserController;
import model.Role;
import model.User;
import service.staffService.UserServiceIMPL;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Account {
    List<User> userList = new UserServiceIMPL().userList;
    String PATH = "src/data/userData.txt";
    Scanner sc = new Scanner(System.in);
    String LOGINPATH = "src/data/userLoginData.txt";

//    static {
//        new ReceiptController().setRoomStt();
//    }

    public boolean checkUsername(String username) {
        boolean check = true;
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUserName().equals(username)) {
                check = false;
                return check;
            }
        } return check;
    }

    public void Register() throws ParseException {
        int id;
        if (userList.size() == 0) {
            id = 1;
        } else {
            id = userList.get(userList.size() - 1).getId() + 1;
        }
        String emailRegex = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
        String accountRegex = "^([a-z]|[0-9]){8,16}$";
        String phonenumberRegex = "^0[0-9]{8,9}$";
        String passwordRegex = "^[A-Za-z0-9]{8,16}$";

        System.out.println("Enter username (8-16 characters):");
        String username;
        boolean checkUsernameRegex;
        while (true) {
            username = sc.nextLine();
            boolean check = checkUsername(username);
            checkUsernameRegex = Pattern.matches(accountRegex, username);
            if (checkUsernameRegex && check) {
                break;
            } else {
                System.err.println("This user name is existed or wrong account pattern, enter again: ");
            }
        }

        System.out.println("Enter password (8-16 characters):");
        String password;
        boolean checkPasswordRegex;
        while (true) {
            password = sc.nextLine();
            checkPasswordRegex = Pattern.matches(passwordRegex, password);
            if (checkPasswordRegex) {
                break;
            } else {
                System.err.println("Wrong password pattern, enter again: ");
            }
        }
        System.out.println("Enter email (abc@def.xyz):");
        String email;
        boolean checkEmailRegex;
        while (true) {
            email = sc.nextLine();
            checkEmailRegex = Pattern.matches(emailRegex, email);
            if (checkEmailRegex) {
                break;
            } else {
                System.err.println("Wrong email pattern, enter again: ");
            }
        }

        System.out.println("Enter phonenumber (0xxxxxxxxx):");
        String phonenumber;
        boolean checkPhonenumberRegex;
        while (true) {
            phonenumber = sc.nextLine();
            checkPhonenumberRegex = Pattern.matches(phonenumberRegex, phonenumber);
            if (checkPhonenumberRegex) {
                break;
            } else {
                System.err.println("Wrong phonenumber pattern, enter again: ");
            }
        }

        //System.out.println("Enter roll: admin or staff ");
        //String roleSelect = sc.nextLine();
        Role role = null;
        if (id == 1) {
            role = Role.ADMIN;
        } else {
            role = Role.STAFF;
        }

        User staff = new User(id, username, password, email, phonenumber, role);
        userList.add(staff);
        new ConfigReadAndWrite<User>().writeToFile(PATH, userList);
        new Main();
    }


    public void Login() throws ParseException {
        System.out.println("Enter login username: ");
        String loginname = sc.nextLine();
        if (new UserServiceIMPL().findByUsername(loginname)==null){
            System.err.println("This account is not exist! Enter again.");
            new Account().Login();
        }else {
            for (int i = 0; i < userList.size(); i++) {
                if (loginname.equals(userList.get(i).getUserName())) {
                    System.out.println("Enter password: ");
                    String password;
                    while (true) {
                        password = sc.nextLine();
                        if (password.equals(userList.get(i).getPassWord())) {
                            break;
                        } else {
                            System.err.println("Wrong password, enter again: ");
                        }
                    }
                    User user = new UserServiceIMPL().findByUsername(loginname);
                    new ConfigLogin().writeToFile(LOGINPATH, user);
                    if (user.getRole() == Role.ADMIN) {
                        new AdminMenu();
                    } else if (user.getRole() == Role.STAFF) {
                        new StaffMenu();
                    }
                }
            }
        }
    }

    public void Logout() throws ParseException {
        new ConfigLogin().writeToFile(LOGINPATH, null);
        new Main();
    }
}


//System.out.println("Enter login username: ");
//        String loginname = sc.nextLine();
//        System.out.println("Enter password: ");
//        String loginpassword = sc.nextLine();
//        for (int i = 0; i < staffList.size(); i++) {
//        if (loginname.equals(staffList.get(i).getUserName()) && loginpassword.equals(staffList.get(i).getPassWord())) {
//        new Menu();
//        }
//        }


//       if (checkAccountRegex) {
//            if (checkPasswordRegex) {
//                if (checkEmailRegex){
//                    if (checkPhoneRegex){
//                        Staff staff = new Staff(id, username, password, email, phone);
//                        staffList.add(staff);
//                        new ConfigReadAndWrite<Staff>().writeFromFile(PATH, staffList);
//                        new Main();
//                    }else {
//                        System.out.println("Wrong phone pattern");
//                        new Account().Register();
//                    }
//                }else {
//                    System.out.println("Wrong email pattern");
//                    new Account().Register();
//                }
//            }
//            else {
//                System.out.println("Wrong password pattern");
//                new Account().Register();
//            }
//        }else {
//            System.out.println("Wrong account pattern");
//            new Account().Register();
//        }
