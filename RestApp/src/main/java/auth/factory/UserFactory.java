package auth.factory;

import auth.user.User;

public interface UserFactory {
    User createUser(String userName, String passwordHash);
}

