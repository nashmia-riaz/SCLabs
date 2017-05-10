package pk.edu.nust.seecs.gradebook.BOs;

import pk.edu.nust.seecs.gradebook.dao.TeacherDao;
import pk.edu.nust.seecs.gradebook.entity.Teacher;

/**
 * Created by nashm on 19/04/2017.
 */
public class TeacherBO {
    private TeacherDao teacherdao;
    public TeacherBO(){
        teacherdao = new TeacherDao();
    }
    public void addTeacher(Teacher c){
        teacherdao.addTeacher(c);
    }

    public void updateTeacher(Teacher c){
        teacherdao.updateTeacher(c);
    }

    public void deleteTeacher(int id){
        teacherdao.deleteTeacher(id);
    }
}
