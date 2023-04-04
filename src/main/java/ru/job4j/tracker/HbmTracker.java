package ru.job4j.tracker;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    public static final Logger LOG  = Logger.getLogger(HbmTracker.class.getName());
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(item);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error("Exception in adding Item: " + item, e);
        } finally {
            session.close();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        Session session = sf.openSession();
        Transaction transaction = null;
        boolean result = false;
        try {
            transaction = session.beginTransaction();
            int rowsUpdated = session.createQuery(
                    "UPDATE Item SET name = :fName WHERE id = :fId")
                    .setParameter("fName", item.getName())
                    .setParameter("fId", id)
                    .executeUpdate();
            result = rowsUpdated > 0;
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error("Exception in replacing Item: " + item + " id: " + id, e);
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        Session session = sf.openSession();
        Transaction transaction = null;
        boolean result = false;
        try {
            transaction = session.beginTransaction();
            int rowsUpdated = session.createQuery("DELETE Item WHERE id = :fId")
                                .setParameter("fId", id).executeUpdate();
            result = rowsUpdated > 0;
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error("Exception in deleting Item by id: " + id, e);
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        Session session = sf.openSession();
        Transaction transaction = null;
        List<Item> items;
        try {
            transaction = session.beginTransaction();
            items = session.createQuery("FROM ru.job4j.tracker.Item", Item.class).list();
            transaction.commit();
            return items;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error("Exception in finding all Item ", e);
        } finally {
            session.close();
        }
        return Collections.emptyList();
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = sf.openSession();
        Transaction transaction = null;
        List<Item> items;
        try {
            transaction = session.beginTransaction();
            items = session.createQuery("FROM Item as i WHERE i.name = :fKey", Item.class)
                    .setParameter("fKey", key)
                    .list();
            transaction.commit();
            return items;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error("Exception in finding Item by name " + key, e);
        } finally {
            session.close();
        }
        return Collections.emptyList();
    }

    @Override
    public Item findById(int id) {
        Session session = sf.openSession();
        Transaction transaction = null;
        Item item = null;
        try {
            transaction = session.beginTransaction();
            Query<Item> query = session.createQuery(
                            "FROM Item as i WHERE i.id = :fId", Item.class)
                    .setParameter("fId", id);
            item = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error("Exception in finding User by id: " + id, e);
        } finally {
            session.close();
        }
        return item;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }

}
