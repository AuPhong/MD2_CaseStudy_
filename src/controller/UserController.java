package controller;

import model.User;
import service.staffService.UserServiceIMPL;

public class UserController {
    public void showInfo(){
        System.out.println(new UserServiceIMPL().showInfo());
    }

    public void editInfo(User user){
        new UserServiceIMPL().editInfo(user);
    }

    public void showList(){
        System.out.println(new UserServiceIMPL().findAll());
    }

    public void deleteById(int id){
        new UserServiceIMPL().deleteById(id);
    }

    public void editById(User user){
        new UserServiceIMPL().editById(user);
    }
}
