package br.com.studae.database.models;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Consumer;

/**
 * @param <O> the object that it represent
 * @param <P> the primary key in database
 */
public abstract class DatabaseModel<O, P> {

    protected final DataSource dataSource;

    protected final String tableName, saveCode, primaryName;

    public DatabaseModel(DataSource dataSource, String createQuery, String tableName, String saveCode, String primaryName) {
        this.dataSource = dataSource;
        this.tableName = tableName == null ? this.getClass().getSimpleName().toLowerCase() + "s" : tableName;
        this.saveCode = saveCode.replace("%table%", this.tableName);
        this.primaryName = primaryName == null ? "id" : primaryName;

        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(createQuery.replace("%table%", this.tableName))) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DatabaseModel(DataSource dataSource, String createQuery, String saveCode) {
        this(dataSource, createQuery, null, saveCode, null);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void save(O data) {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(saveCode);) {
            saveHandler(data).accept(stmt);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void bulkSave(Collection<O> data) {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(saveCode);) {
            for (O element : data) {
                saveHandler(element).accept(stmt);
                stmt.addBatch();
            }

            stmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<O> get(P primary) {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement("SELECT * FROM " + tableName + " WHERE " + primaryName + " = ?")) {
            setColumnValue(1, primary).accept(stmt);

            ResultSet set = stmt.executeQuery();
            if (!set.next()) return Optional.empty();

            return Optional.of(getHandler(set));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public List<O> findByColumn(String columnName, Object key) {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement("SELECT * FROM " + tableName + " WHERE " + columnName + " = ?")) {
            setColumnValue(1, key).accept(stmt);

            ResultSet set = stmt.executeQuery();
            List<O> list = new ArrayList<>();

            while (set.next()) list.add(getHandler(set));

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    public void delete(P primary) {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement("DELETE FROM " + tableName + " WHERE " + primaryName + " = ?")) {
            setColumnValue(1, primary).accept(stmt);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Consumer<PreparedStatement> setColumnValue(int columnIndex, Object value) {
        return stmt -> {
            try {
                Class<?> primaryClass = value.getClass();
                if (primaryClass == Integer.TYPE) stmt.setInt(columnIndex, (Integer) value);
                else if (primaryClass == Boolean.TYPE) stmt.setBoolean(columnIndex, (Boolean) value);
                else if (primaryClass == String.class) stmt.setString(columnIndex, (String) value);
                else stmt.setString(columnIndex, value.toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        };
    }

    protected abstract Consumer<PreparedStatement> saveHandler(O data);

    protected abstract O getHandler(ResultSet resultSet);
}
