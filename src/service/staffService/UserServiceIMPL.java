package service.staffService;

import config.ConfigLogin;
import config.ConfigReadAndWrite;
import model.Role;
import model.User;

import java.util.List;

public class UserServiceIMPL implements IUserService {
    public static String PATH = "src/data/userData.txt";
    public static String LOGINPATH = "src/data/userLoginData.txt";
    public static ConfigLogin configLogin = new ConfigLogin();
    public static ConfigReadAndWrite<User> configReadAndWrite = new ConfigReadAndWrite<>();
    public static List<User> userList = configReadAndWrite.readFromFile(PATH);

    @Override
    public List<User> findAll() {
        configReadAndWrite.writeToFile(PATH, userList);
        return userList;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == id) {
                userList.remove(userList.get(i));
            }
        }
    }

    @Override
    public User findById(int id) {
        for (int i = 0; i < userList.size(); i++) {
            if (id == userList.get(i).getId()){
                return userList.get(i);
            }
        }
        return null;
    }

    @Override
    public void editById(User user) {
        int id = user.getId();
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == id) {
                if (user.getUserName().equals("")) {
                    userList.get(i).setUserName(userList.get(i).getUserName());
                } else {
                    userList.get(i).setUserName(user.getUserName());
                }
                if (user.getPassWord().equals("")) {
                    userList.get(i).setPassWord(userList.get(i).getPassWord());
                } else {
                    userList.get(i).setPassWord(user.getPassWord());
                }
                if (user.getEmail().equals("")) {
                    userList.get(i).setEmail(userList.get(i).getEmail());
                } else {
                    userList.get(i).setEmail(user.getEmail());
                }
                if (user.getPhone().equals("")) {
                    userList.get(i).setPhone(userList.get(i).getPhone());
                } else {
                    userList.get(i).setPhone(user.getPhone());
                }
                if (user.getRole().equals("")) {
                    userList.get(i).setRole(userList.get(i).getRole());
                } else {
                    userList.get(i).setRole(user.getRole());
                }
            }
        }
        configReadAndWrite.writeToFile(PATH, userList);
    }

    public User findByUsername(String username) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUserName().equals(username)) {
                return userList.get(i);
            }
        }
        return null;
    }

    public User showInfo() {
        User user = configLogin.readFromFile(LOGINPATH);
        return user;
    }

    public void editInfo(User userChange) {
        User user = configLogin.readFromFile(LOGINPATH);
        int id = user.getId();
        String username = userChange.getUserName();
        String password = userChange.getPassWord();
        String email = userChange.getEmail();
        String phone = userChange.getPhone();
        Role role = userChange.getRole();

        if (username.equals("")) {
            username = user.getUserName();
        }
        if (password.equals("")) {
            password = user.getPassWord();
        }
        if (email.equals("")) {
            email = user.getEmail();
        }
        if (phone.equals("")) {
            phone = user.getPhone();
        }
        if (role.equals("")) {
            role = user.getRole();
        }
        user = new User(id, username, password, email, phone, role);
        configLogin.writeToFile(LOGINPATH, user);

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == id) {
                if (username.equals("")) {
                    userList.get(i).setUserName(userList.get(i).getUserName());
                } else {
                    userList.get(i).setUserName(username);
                }
                if (password.equals("")) {
                    userList.get(i).setPassWord(userList.get(i).getPassWord());
                } else {
                    userList.get(i).setPassWord(password);
                }
                if (email.equals("")) {
                    userList.get(i).setEmail(userList.get(i).getEmail());
                } else {
                    userList.get(i).setEmail(email);
                }
                if (phone.equals("")) {
                    userList.get(i).setPhone(userList.get(i).getPhone());
                } else {
                    userList.get(i).setPhone(phone);
                }
                if (role.equals("")) {
                    userList.get(i).setRole(userList.get(i).getRole());
                } else {
                    userList.get(i).setRole(role);
                }
            }
        }
    }


}
