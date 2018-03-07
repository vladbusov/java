package main.service;

import main.data.UserList;
import main.models.Message;
import main.models.User;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

public class UserService {

        public static Message notFound(String request) {
                ArrayList<String> arrayList = new ArrayList<String>();
                arrayList.add("404");
                arrayList.add("PAGE " + request + " NOT FOUND");
                return new Message<ArrayList>(true, arrayList);
        }

        @NotNull
        public static Message registUser(User newbie) {
                if (!UserList.uniqueUser(newbie.getEmail())) {
                        return new Message<String>(false, "USER_ALREADY_EXISTS");
                }
                UserList.addUser(newbie);
                return new Message<String>(true, "USER_SUCCESSFULLY_REGISTERED");
        }

        @NotNull
        public static Message login(String email, String password, HttpSession session) {
                if (UserList.login(email, password)) {
                        session.setAttribute("userId", UserList.getId(email));
                        return new Message<String>(true, "USER_SUCCESSFULLY_LOGIN");
                }
                return new Message<String>(false, "WRONG_DATA_FOR_LOGIN");
        }

        @NotNull
        public static Message getUserData(HttpSession session) {
                final Long id = (Long) session.getAttribute("userId");
                if (Validation.checkId(id) != null) {
                        return Validation.checkId(id);
                }
                User curUser = UserList.getById(id);
                return new Message<User>(true, curUser);
        }

        @NotNull
        public static Message editUser(User user, HttpSession session) {
                final Long id = (Long) session.getAttribute("userId");
                if (Validation.checkId(id) != null) {
                        return Validation.checkId(id);
                }
                User curUser = UserList.getById(id);
                curUser.editUser(user.getEmail(), user.getLogin(), user.getPassword());
                return new Message<String>(true, "USER_SUCCESSFULLY_CHANGED");
        }

        @NotNull
        public static Message loguot(HttpSession session) {
                final Long id = (Long) session.getAttribute("userId");
                if (Validation.checkId(id) != null) {
                        return Validation.checkId(id);
                }
                session.invalidate();
                return new Message<String>(true, "USER_SUCCESSFULLY_UNLOGIN");
        }


}
