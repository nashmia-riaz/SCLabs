package pk.edu.nust.seecs.gradebook.BOs;

import pk.edu.nust.seecs.gradebook.dao.CourseDao;
import pk.edu.nust.seecs.gradebook.entity.Course;

/**
 * Created by nashm on 19/04/2017.
 */
public class CourseBO {
    private CourseDao courseDAO;

    public CourseBO(){
        courseDAO = new CourseDao();
    }
    public void addClo(Course c){
        courseDAO.addCourse(c);
    }

    public void updateClo(Course c){
        courseDAO.updateCourse(c);
    }

    public void deleteClo(int id){
        courseDAO.deleteCourse(id);
    }
}
