package pk.edu.nust.seecs.gradebook.BOs;

import pk.edu.nust.seecs.gradebook.dao.StudentDao;
import pk.edu.nust.seecs.gradebook.entity.Student;

/**
 * Created by nashm on 19/04/2017.
 */
public class StudentBO {
    private StudentDao studentdao;
    public StudentBO(){
        studentdao = new StudentDao();
    }
    public void addClo(Student c){
        studentdao.addStudent(c);
    }

    public void updateClo(Student c){
        studentdao.updateStudent(c);
    }

    public void deleteClo(int id){
        studentdao.deleteStudent(id);
    }
}
