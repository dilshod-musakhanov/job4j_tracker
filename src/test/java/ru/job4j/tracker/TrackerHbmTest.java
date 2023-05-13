package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class TrackerHbmTest {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    @BeforeEach
    public void wipeTable() {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery("DELETE FROM Item").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            Item result = tracker.findById(item.getId());
            assertThat(result.getName(), is(item.getName()));
        }
    }

    @Test
    public void whenReplaceItemThenGetReplacedItem() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            Item item2 = new Item();
            item2.setName("test2");
            assertThat(tracker.replace(item.getId(), item2), is(true));
            Item result = tracker.findById(item.getId());
            assertThat(result.getName(), is(item2.getName()));
        }
    }

    @Test
    public void whenDeleteItemThenGetNull() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item("test1");
            tracker.add(item);
            int id = item.getId();
            tracker.delete(id);
            assertThat(tracker.findById(id), is(nullValue()));
        }
    }

    @Test
    public void whenFindAllItemsThenFindListOfItems() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item1 = new Item("test1");
            Item item2 = new Item("test2");
            tracker.add(item1);
            tracker.add(item2);
            assertThat(tracker.findAll(), is(List.of(item1, item2)));
        }
    }

    @Test
    public void whenFindByNameThenGetItem() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            assertThat(tracker.findByName("test1"), is(List.of(item)));
        }
    }

    @Test
    public void whenFindByIdThenGetItem() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item1 = new Item("item1");
            tracker.add(item1);
            int id  = item1.getId();
            assertThat(tracker.findById(id), is(item1));
        }
    }
}
