package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store, AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(SqlTracker.class.getName());

    private Connection cn;

    public SqlTracker() {

    }

    public SqlTracker(Connection connection) {
        this.cn = connection;
    }

    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );

        } catch (Exception e) {
            LOG.error(String.valueOf(e));
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        init();
        try (PreparedStatement ps = cn.prepareStatement(
                "insert into items(name, created) values (?, ?)",
                Statement.RETURN_GENERATED_KEYS
        )) {
            long millis = System.currentTimeMillis();
            Timestamp timestamp = new Timestamp(millis);
            LocalDateTime localDateTime = timestamp.toLocalDateTime();
            ps.setString(1, item.getName());
            ps.setTimestamp(2, Timestamp.valueOf(localDateTime));
            boolean flag = ps.executeUpdate() > 0;
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getInt(1));
                }
            }
            if (flag) {
                LOG.debug("Item added: {}", item.getName());
            }
        } catch (Exception e) {
            LOG.error(String.valueOf(e));
            throw new IllegalArgumentException(e);
        }
        return null;
    }

    @Override
    public boolean replace(int id, Item item) {
        init();
        boolean flag = false;
        try (PreparedStatement ps = cn.prepareStatement(
                "update items set name = ? where id = ?"
        )) {
            ps.setString(1, item.getName());
            ps.setInt(2, id);
            flag = ps.executeUpdate() > 0;
            if (!flag) {
                LOG.error("Item with id: {}, could not be replaced by new Item : {}. "
                        + "Check if id: {} exists", id, item, id);
            } else {
                LOG.debug("Item with id: {} has been replaced with new Item : {}", id, item);
            }
        } catch (Exception e) {
            LOG.error(String.valueOf(e));
            throw new IllegalArgumentException(e);
        }
        return flag;
    }

    @Override
    public boolean delete(int id) {
        init();
        boolean flag = false;
        try (PreparedStatement ps = cn.prepareStatement(
                "delete from items where id = ?"
        )) {
            ps.setInt(1, id);
            flag = ps.executeUpdate() > 0;
            if (!flag) {
                LOG.error("Item with id : {} could not be deleted. Check if id : {} exists", id, id);
            } else {
                LOG.debug("Item with id : {} has been deleted", id);
            }
        } catch (Exception e) {
            LOG.error(String.valueOf(e));
            throw new IllegalArgumentException(e);
        }
        return flag;
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        init();
        try (PreparedStatement ps = cn.prepareStatement(
                "select * from items"
        )) {
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    Timestamp timestampFromLDT = Timestamp.valueOf(String.valueOf(resultSet.getTimestamp("created")));
                    LocalDateTime localDateTime = timestampFromLDT.toLocalDateTime();
                    items.add(new Item(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            localDateTime
                    ));
                }
                if (items.isEmpty()) {
                    LOG.debug("Items are empty");
                } else {
                    LOG.debug(String.valueOf(items));
                }
            }
        } catch (Exception e) {
            LOG.error(String.valueOf(e));
            throw new IllegalArgumentException(e);
        }

        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        init();
        try (PreparedStatement ps = cn.prepareStatement(
                "select * from items where name = ?"
        )) {
            ps.setString(1, key);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    Timestamp timestampFromLDT = Timestamp.valueOf(String.valueOf(resultSet.getTimestamp("created")));
                    LocalDateTime localDateTime = timestampFromLDT.toLocalDateTime();
                    items.add(new Item(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            localDateTime
                    ));
                }
                if (items.isEmpty()) {
                    LOG.debug("Not found by name: {}", key);
                } else {
                    LOG.debug(String.valueOf(items));
                }
            }
        } catch (Exception e) {
            LOG.error(String.valueOf(e));
            throw new IllegalArgumentException(e);
        }

        return items;
    }

    @Override
    public Item findById(int id) {
        Item searchedItem = null;
        init();
        try (PreparedStatement ps = cn.prepareStatement(
                "select * from items where id = ?"
        )) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    Timestamp timestampFromLDT = Timestamp.valueOf(String.valueOf(resultSet.getTimestamp("created")));
                    LocalDateTime localDateTime = timestampFromLDT.toLocalDateTime();
                    searchedItem = new Item(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            localDateTime
                    );
                }
                if (searchedItem == null) {
                    LOG.debug("Not found by id : {}", id);
                } else {
                    LOG.debug(String.valueOf(searchedItem));
                }
            }
        } catch (Exception e) {
            LOG.error(String.valueOf(e));
            throw new IllegalArgumentException(e);
        }
        return searchedItem;
    }

    public static void main(String[] args) {
        SqlTracker sqlTracker = new SqlTracker();
        sqlTracker.add(new Item("one"));
        sqlTracker.add(new Item("two"));
        sqlTracker.replace(2, new Item("three"));
        sqlTracker.findAll();
        sqlTracker.findByName("one");
        sqlTracker.findById(2);
        sqlTracker.delete(2);
    }
}
