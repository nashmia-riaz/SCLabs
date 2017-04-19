package pk.edu.nust.seecs.gradebook.BOs;
import pk.edu.nust.seecs.gradebook.dao.CloDao;
import pk.edu.nust.seecs.gradebook.entity.Clo;

/**
 * Created by nashm on 19/04/2017.
 */
public class CloBO {
    private CloDao cloDAO;
    public CloBO(){
        cloDAO = new CloDao();
    }
    public void addClo(Clo clo){
        cloDAO.addClo(clo);
    }

    public void updateClo(Clo clo){
        cloDAO.updateClo(clo);
    }

    public void deleteClo(int id){
        cloDAO.deleteClo(id);
    }
}
