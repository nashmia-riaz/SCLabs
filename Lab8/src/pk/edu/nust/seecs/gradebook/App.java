package pk.edu.nust.seecs.gradebook;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import pk.edu.nust.seecs.gradebook.dao.CloDao;
import pk.edu.nust.seecs.gradebook.entity.Clo;
import pk.edu.nust.seecs.gradebook.BOs.CloBO;

/**
 * My main App. 
 * <p>
 This executes everything.
 */

public class App {

    public static void main(String[] args) {
        boolean exit = false;
        int option;

        System.out.print("Enter one of the following: \n"+
                "1. Add\n"+
                "2. Delete\n" +
                "3. Update\n");

        Scanner input = new Scanner(System.in);
        option = input.nextInt();

        System.out.print("Enter one of the following: \n"+
                "1. Clo\n"+
                "2. Content\n" +
                "3. Course\n" +
                "4. Grade\n" +
                "5. Student\n" +
                "6. Teacher\n");
        int option2 = input.nextInt();

        switch(option){
            case 1:
                switch(option2){
                    case 1:
                        CloBO clobo = new CloBO();
                        Clo cl = new Clo("1","hello","plo","bt");
                        clobo.addClo(cl);
                        break;

                    case 2:
                        break;

                    case 3:
                        break;

                    case 4:
                        break;

                    case 5:
                        break;

                    case 6:
                        break;
                }
                break;

            case 2:
                break;

            case 3:
                break;
        }
    }

}