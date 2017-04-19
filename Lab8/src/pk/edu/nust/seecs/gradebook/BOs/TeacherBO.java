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
    public void addClo(Teacher c){
        teacherdao.addTeacher(c);
    }

    public void updateClo(Teacher c){
        teacherdao.updateTeacher(c);
    }

    public void deleteClo(int id){
        teacherdao.deleteTeacher(id);
    }
}
