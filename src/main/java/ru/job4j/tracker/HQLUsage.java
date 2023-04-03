package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;

public class HQLUsage {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            Session session = sf.openSession();
            /* working with session */
            session.close();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static void unique(Session session) {
        Query query = session.createQuery(
                "from Item as i where i.id = 3", Item.class);
        System.out.println(((org.hibernate.query.Query<?>) query).uniqueResult());
    }

    public static void findById(Session session, int id) {
        Query query = session.createQuery(
                "from Item as i where i.id = :fId", Item.class);
        query.setParameter("fId", id);
        System.out.println(((org.hibernate.query.Query<?>) query).uniqueResult());
    }

    public static void update(Session session, int id) {
        try {
            session.beginTransaction();
            session.createQuery(
                            "UPDATE Item SET name = :fName WHERE id = :fId")
                    .setParameter("fName", "new name")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    public static void delete(Session session, int id) {
        try {
            session.beginTransaction();
            session.createQuery(
                            "DELETE Item WHERE id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }
    /**
     * 4. INSERT
     *
     * HQL поддерживает вставку только из другой таблицы, то есть запросы вида
     *
     * "INSERT INTO Item (name) SELECT i.name FROM ДругойОбъект as i"
     *
     * или
     *
     * Query query = session.createQuery("INSERT INTO Item(name)" +
     *         "SELECT name FROM Другая_таблица");
     * int rsl = query.executeUpdate();
     *
     * В последнем примере в переменную rsl будет записано количество вставленных записей.
     *
     * Запрос типа "INSERT ... VALUES ..." в HQL не поддерживается.
     *
     *
     * Для обычной вставки нужно использовать метод save(), который мы проходили в предыдущем уроке. В метод save() нужно передать объект Item (создаем в методе main() класса HQLUsage):
     *
     * Item item = new Item("name");
     *
     * Метод save():
     *
     * public static void insert(Session session, Item item) {
     *     try {
     *         session.beginTransaction();
     *         session.save(item);
     *         session.getTransaction().commit();
     *     } catch (Exception e) {
     *         session.getTransaction().rollback();
     *     }
     * }
     */
}
