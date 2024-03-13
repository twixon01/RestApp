package auth.factory;

import auth.user.Admin;
import auth.user.User;

public class AdminFactory implements UserFactory {
    @Override
    public User createUser(String userName, String passwordHash) {
        return new Admin(userName, passwordHash);
    }
}
