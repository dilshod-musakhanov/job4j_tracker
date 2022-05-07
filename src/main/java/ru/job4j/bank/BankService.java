package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс описывает работу банковского сервиса
 * добавление пользователя и счета
 * поиск по номеру паспорта или реквизита
 * перевод со счета на счет
 * @author Dilshod Musakhanov
 */
public class BankService {
    /**
     * Хранение данных пользователей и счетов осуществляет коллекция HashMap
     * где User это ключ, а значение это счета пользователя записанные в коллекции ArrayList
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод принимает на вход объект User
     * если такого пользователя ранее не было
     * то добавляет этого пользователя и создает коллекцию счетов ArrayList
     * @param user - пользователь
     * @see ru.job4j.bank.User
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<Account>());
    }

    /**
     * Метод добавляет новый счет.
     * Используя входные данные passport и account он проверяет
     * существует ли такой пользователь(при помощи метода {@link ru.job4j.bank.BankService#findByPassport(String)})
     * если такой пользователь есть, то далее проверяется наличие данного счета, если счета не было,
     * то новый счет добавляется
     * @param passport - номер паспорта
     * @param account - счет пользователя
     * @see ru.job4j.bank.Account
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null && !users.get(user).contains(account)) {
            users.get(user).add(account);
        }
    }

    /**
     * Метод принимает на вход номер паспорта и
     * возвращает объект пользователя или null если такого пользователя не существует
     * @param passport - номер паспорта
     * @return - объект User
     */
    public User findByPassport(String passport) {
        return users.keySet()
                .stream()
                .filter(u -> u.getPassport().equals(passport))
                .findFirst()
                .orElse(null);
    }

    /**
     * Метод принимает на вход номер паспорта и номер реквизита.
     * Возвращает объект Account или null, если такого пользователя или счета не существует
     * @param passport - номер паспорта
     * @param requisite - реквизит банковского счета
     * @return - объект счета
     */
    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            return users.get(user)
                    .stream()
                    .filter(a -> a.getRequisite().equals(requisite))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    /**
     * Метод реализует перевода банковских средств с одного счета на другой.
     * Сначала идет валидация счетов при помощи {@link ru.job4j.bank.BankService#findByRequisite(String, String)}
     * так же проверяется наличие достаточных средств на счете с которого будет перевод.
     * Если все условия соблюдаются, то перевод происходит, и счет с которого был перевод уменьшается на amount.
     * @param srcPassport - номер паспорта пользователя, со счета которого будет перевод
     * @param srcRequisite - номер счета, с которого будет перевод
     * @param destPassport - номер паспорта пользователя, на счет которого будет перевод
     * @param destRequisite - номер счета, на счет которого будет перевод
     * @param amount - сумма перевода
     * @return - возвращает true если перевод прошел успешно или false если хотя бы одно из условий не было соблюдено
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        Account src = findByRequisite(srcPassport, srcRequisite);
        Account dest = findByRequisite(destPassport, destRequisite);
        boolean rsl = false;
        if (src != null && dest != null && src.getBalance() <= amount) {
            src.setBalance(src.getBalance() - amount);
            dest.setBalance(dest.getBalance() + amount);
            rsl = true;
        }
        return rsl;
    }
}
