package pk.edu.nust.seecs.gradebook.BOs;

import pk.edu.nust.seecs.gradebook.dao.GradeDao;
import pk.edu.nust.seecs.gradebook.entity.Grade;

/**
 * Created by nashm on 19/04/2017.
 */
public class GradeBO {
    private GradeDao gradedao;
    public GradeBO(){
        gradedao = new GradeDao();
    }
    public void addClo(Grade c){
        gradedao.addGrade(c);
    }

    public void updateClo(Grade c){
        gradedao.updateGrade(c);
    }

    public void deleteClo(int id){
        gradedao.deleteGrade(id);
    }
}
