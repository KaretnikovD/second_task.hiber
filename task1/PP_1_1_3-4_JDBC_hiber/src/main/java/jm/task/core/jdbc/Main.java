package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("A", "A", (byte) 50);
        userService.saveUser("B", "B", (byte) 24);
        userService.saveUser("C", "C", (byte) 44);
        userService.saveUser("D", "D", (byte) 26);


        userService.createUsersTable();
        userService.dropUsersTable();


    }
}

