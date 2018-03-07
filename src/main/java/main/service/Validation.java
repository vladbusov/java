package main.service;

import main.data.UserList;
import main.models.Message;
import main.models.User;

public class Validation {
    public static Message checkId(Long id)
    {
        if (id == null) {
            return new Message<String>(false, "NOT_LOGINED");
        }
        User curUser = UserList.getById(id);
        if (curUser == null){
            return new Message<String>(false, "INVALID_SESSION_ID");
        }
        return null;
    }
}
