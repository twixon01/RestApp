# RestApp
## Консольное приложение для работы с заказами в ресторане.


## Меню приложения
В процессе программа предлагает пользователю всего три меню

### 1 - Меню авторизации:

```
        МЕНЮ АВТОРИЗАЦИИ
    1 - Вход в аккаунт
    2 - Создание нового аккаунта Посетителя
    3 - Создание нового аккаунта Админа
    0 - Выход

Введите число от 0 до 3: 
```

### 2 - Главное меню Посетителя

```
👍 Здравствуйте, user! (ПОСЕТИТЕЛЬ)

      ГЛАВНОЕ МЕНЮ      
    1 - Меню
    2 - Оформление заказа
    3 - Добавление блюда в заказ
    4 - Убрать блюдо из заказа
    5 - Заказы
    6 - Отмена заказа
    7 - Оплата заказа
    8 - Оставить отзыв на блюдо
    0 - Выход
Введите число от 0 до 8: 
```
 
###3 - Главное меню Администратора


```
👍 Здравствуйте, admin! (АДМИН)
      ГЛАВНОЕ МЕНЮ      
    1 - Меню
    2 - Добавление блюда в меню
    3 - Удаление блюда из меню
    4 - Обновление количества блюда
    5 - Обновление цены на блюдо
    6 - Обновление времени выполнения блюда
    7 - Список админов
    8 - Список посетителей
    9 - Средний рейтинг и выручка ресторана
    0 - Выход
Введите число от 0 до 9: 
```

Все последующие шаги интуитивно понятные для пользователя, программа сообщает о запрашиваемых данных в процессе.
При неверном вводе программа сообщает об ошибках.

```


# Шаблоны проектирования


## Паттерн Cостояние

В приложении состояние используется для объекта Order, чтобы удобно отслеживать его состояние приготовления (принят, готовится, готов).


```
Он упрощает структуру кода, разделяя код, связанный с различными состояниями, на отдельные классы.

Использование паттерна состояния позволяет избежать громоздких условных конструкций при работе с различными состояниями объекта.

Паттерн состояния соответствует принципу единственной ответственности.

Также он соблюдает принцип открытости/закрытости.
```

## Синглтон

В приложении используется паттерн Синглтон для классов UserDatabase, FoodMenu, OrderDatabase.

```
Он удобен, когда нужно обеспечить доступ к одним и тем же данным из разных частей кода.

В нашем случае он используется для хранения всех пользователей (UserDatabase), меню блюд (FoodMenu) и информации о текущих заказах (OrderDatabase). Эти данные извлекаются из файлов формата JSON при перезапуске программы.
```

## Фабричный метод

В приложении используется фабричный метод для создания двух типов пользователей: посетителя (Visitor) и администратора (Admin).

```
Этот подход помогает избежать прямой зависимости между создателем и конкретными типами пользователей.

Со временем код не усложняется, поскольку существует только два вида пользователей в системе.

Фабричный метод соответствует принципу единственной ответственности.

Также он соблюдает принцип открытости/закрытости.

Этот подход легко поддерживать при добавлении новых функций как для посетителей, так и для администраторов ресторана.
```


