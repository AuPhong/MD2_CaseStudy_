package config;

import model.User;

import java.io.*;

public class ConfigLogin {
    public User userLogin = new User();

    public User readFromFile(String path) {
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            userLogin = (User) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
        } catch (IOException e) {
            System.out.print("");
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
        }
        return userLogin;
    }

    public void writeToFile(String path, User userLogin){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(userLogin);
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
