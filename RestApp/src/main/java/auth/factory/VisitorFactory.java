package auth.factory;

import auth.user.Admin;
import auth.user.User;
import auth.user.Visitor;

public class VisitorFactory implements UserFactory {
    @Override
    public User createUser(String userName, String passwordHash) {
        return new Visitor(userName, passwordHash);
    }
}