package java2.database;

import java2.models.Announcement;
import java2.models.User;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AnnouncementRealDatabase extends JDBCDatabase implements AnnouncementDatabase {
    private UserDatabase userDatabase;

    public AnnouncementRealDatabase(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    @Override
    public void add(Announcement announcement) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "insert into ANNOUNCEMENTS(id, title, description, user_idref, state_idref) values(default, ?, ?, ?, ?)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, announcement.getTitle());
            preparedStatement.setString(2, announcement.getDescription());
            preparedStatement.setInt(3, announcement.getCreator().getId());
            preparedStatement.setString(4, announcement.getState());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
                announcement.setId(rs.getInt(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute AnnouncementDAOImpl.save()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Announcement> findByUser(User user) {
        List<Announcement> announcements = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select * from ANNOUNCEMENTS where user_idref = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                announcements.add(getAnnouncementFromResultSet(resultSet));
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting announcement list AnnouncementDAOImpl.getByUser()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return announcements;
    }

    @Override
    public Optional<Announcement> findByTitle(String title) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select * from ANNOUNCEMENTS where title = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            Announcement announcement = null;
            if (resultSet.next()) {
                announcement = getAnnouncementFromResultSet(resultSet);
            }
            return Optional.ofNullable(announcement);
        } catch (Throwable e) {
            System.out.println("Exception while execute AnnouncementDAOImpl.getByTitle()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Announcement> getAllAnnouncements() {
        List<Announcement> announcements = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select * from ANNOUNCEMENTS";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                announcements.add(getAnnouncementFromResultSet(resultSet));
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting announcement list AnnouncementDAOImpl.getAll()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return announcements;
    }

    @Override
    public List<Announcement> getValidAnnouncements() {
        List<Announcement> announcements = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select * from ANNOUNCEMENTS a inner join USERS u on a.user_idref = u.id "+
                    "where a.state_idref <> ? and u.state_idref <> ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "BANNED");
            preparedStatement.setString(2, "BANNED");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                announcements.add(getAnnouncementFromResultSet(resultSet));
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting announcement list AnnouncementDAOImpl.getAllValid()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return announcements;
    }

    @Override
    public void banAnnouncement(Announcement announcement) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "update ANNOUNCEMENTS set state_idref = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "BANNED");
            preparedStatement.setInt(2, announcement.getId());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while updating announcement AnnouncementDAOImpl.ban()");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
    }

    private Announcement getAnnouncementFromResultSet(ResultSet resultSet) {
        Announcement announcement = new Announcement();
        try {
            announcement.setId(resultSet.getInt("id"));
            announcement.setTitle(resultSet.getString("title"));
            announcement.setDescription(resultSet.getString("description"));
            int creatorId = resultSet.getInt("user_idref");
            announcement.setCreator(userDatabase.findById(creatorId).get());
            announcement.setState(resultSet.getString("state_idref"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return announcement;
    }
}
