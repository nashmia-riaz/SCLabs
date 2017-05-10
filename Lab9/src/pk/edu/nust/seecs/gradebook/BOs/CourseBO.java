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
    public void addCourse(Course c){
        courseDAO.addCourse(c);
    }

    public void updateCourse(Course c){
        courseDAO.updateCourse(c);
    }

    public void deleteCourse(int id){
        courseDAO.deleteCourse(id);
    }
}
