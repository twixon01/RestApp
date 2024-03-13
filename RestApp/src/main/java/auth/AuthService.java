package auth;

import auth.factory.AdminFactory;
import auth.factory.UserFactory;
import auth.factory.VisitorFactory;
import auth.user.User;
//import auth.user.UserType;
import service.handlers.AdminUIMenu;
import service.handlers.UIMenuEntity;
import service.handlers.VisitorUIMenu;
import service.util.UserUtil;
import service.modes.InfoMode;
import service.modes.UserMode;

import java.util.Objects;

public class AuthService {
    private UserFactory userFactory;

    public boolean registerUser(UserMode mode) {
        System.out.println("\nВведите данные для регистрации.\n❗Логин должен состоять из 4 и более символов и содержать латинский символ!\n"
        +"❗Пароль должен состоять из 8 и более символов!");
        String username = UserUtil.handleInfoInput(
                "Введите имя пользователя: ",
                "\uD83D\uDEAB Неверный логин!(Не менее 4 символов!)\n",
                InfoMode.USERNAME
        );

        if (username.isEmpty()) {
            return false;
        }

        if (UserDatabase.getAll().stream().anyMatch(user -> user.getUserName().equals(username))) {
            System.out.println("\uD83D\uDEAB Пользователь с таким именем уже существует.");
            return registerUser(mode);
        }

        String password = UserUtil.handleInfoInput(
                "Создайте пароль: ",
                "\uD83D\uDEAB Пароль введён некорректно!(Не менее 8 символов)\n",
                InfoMode.PASSWORD
        );
        String hashedPassword = UserUtil.sha256(password);

        User newUser;
        if (mode == UserMode.VISITOR) {
            userFactory = new VisitorFactory();
        } else {
            userFactory = new AdminFactory();
        }

        newUser = userFactory.createUser(username, hashedPassword);
        UserDatabase.addUser(newUser);
        System.out.println("\n\uD83D\uDC4D Пользователь \"" + username + "\" успешно зарегистрирован.\n");

        return true;
    }

    public UIMenuEntity authenticateUser() {
        System.out.println();
        String username = UserUtil.handleInfoInput(
                "Введите имя пользователя: ",
                "\uD83D\uDEAB Неверный логин!(Не менее 4 символов!)\n",
                InfoMode.USERNAME
        );

        if (username.isEmpty()) {
            return null;
        }

        User user = UserDatabase.getAll().stream()
                .filter(u -> u.getUserName().equals(username))
                .findFirst()
                .orElse(null);

        String password = UserUtil.handleInfoInput(
                "Введите пароль: ",
                "\uD83D\uDEAB Пароль введён некорректно!(Не менее 8 символов)\n",
                InfoMode.PASSWORD
        );
        String hashedPassword = UserUtil.sha256(password);

        if (user != null && user.getPassword().equals(hashedPassword)) {
            String userType = user.getUserType();

            if (Objects.equals(userType, "VISITOR")) {
                System.out.println("\n\uD83D\uDC4D Здравствуйте, " + user.getUserName() + "! (ПОСЕТИТЕЛЬ)\n");
                return new VisitorUIMenu();
            } else {
                System.out.println("\n\uD83D\uDC4D Здравствуйте, " + user.getUserName() + "! (АДМИН)");
                return new AdminUIMenu();
            }
        } else {
            System.out.println("\uD83D\uDEAB Неверный логин или пароль. Повторите еще раз.\n");
            return null;
        }
    }
}
