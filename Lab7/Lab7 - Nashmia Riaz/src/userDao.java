import java.util.List;

/**
 * Created by nashm on 12/04/2017.
 */
public interface userDao {
    public List<user> getAllUsers();
    public user getUser(int userIndex);
    public void updateUser(user user1);
    public void deleteUser(user user1);
}
