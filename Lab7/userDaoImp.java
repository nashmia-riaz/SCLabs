import java.util.List;

/**
 * Created by nashm on 12/04/2017.
 */
public class userDaoImp implements userDao{
    //list is working as a database
    List<user> users;

    @Override
    public void deleteUser(user user1) {
        users.remove(user1.getUserid());
        System.out.println("Student: Roll No " + user1.getUserid() + ", deleted from database");
    }

    //retrive list of students from the database
    @Override
    public List<user> getAllUsers() {
        return users;
    }

    @Override
    public user getUser(int userIndex) {
        return users.get(userIndex);
    }

    @Override
    public void updateUser(user user1) {

    }
}
