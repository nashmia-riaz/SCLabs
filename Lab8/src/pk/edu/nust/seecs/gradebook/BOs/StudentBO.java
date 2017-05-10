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
    public void addStudent(Student c){
        studentdao.addStudent(c);
    }

    public void updateStudent(Student c){
        studentdao.updateStudent(c);
    }

    public void deleteStudent(int id){
        studentdao.deleteStudent(id);
    }
}
