package main.data;

import main.models.User;
import org.springframework.util.StringUtils;

import java.util.HashMap;

public class UserList {
    private static final HashMap<String, User> ARRAY = new HashMap<String, User>() {
        {
            final User first = new User("ivansport@gmail.com", "ivansport", "0");
            final User second = new User("vitaliycherkov@gmail.com", "vitaliycherkov", "1");
            final User third  = new User("vladbusov@gmail.com", "vladbusov", "2");

            put(first.getEmail(), first);
            put(second.getEmail(), second);
            put(third.getEmail(), third);
        }
    };

    public static void addUser(User newbie) {
        ARRAY.put(newbie.getEmail(), newbie);
    }

    public static void deleteUser(String email) {
        ARRAY.remove(email);
    }

    public static boolean login(String email, String password) {
        if (!StringUtils.isEmpty(email)) {
            User current = ARRAY.get(email);
            if (current != null) {
                return current.getPassword().equals(password);
            }
        }
        return false;
    }

    public static boolean uniqueUser(String email) {
        if (!ARRAY.containsKey(email)) {
            return false;
        }
        return true;
    }

    public static Long getId(String email) {
        if (!ARRAY.containsKey(email)) {
            return null;
        }
        return ARRAY.get(email).getId();
    }

    public static User getById(Long id) {
        for (User curUser : ARRAY.values()) {
            if (curUser.getId() == id) {
                return curUser;
            }
        }
        return null;
    }

}
