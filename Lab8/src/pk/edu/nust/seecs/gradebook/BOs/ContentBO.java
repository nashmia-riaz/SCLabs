package pk.edu.nust.seecs.gradebook.BOs;

import pk.edu.nust.seecs.gradebook.dao.ContentDao;
import pk.edu.nust.seecs.gradebook.entity.Content;

/**
 * Created by nashm on 19/04/2017.
 */
public class ContentBO {
    private ContentDao contentDAO;

    public ContentBO(){
        contentDAO = new ContentDao();
    }

    public void addClo(Content c){
        contentDAO.addContent(c);
    }

    public void updateClo(Content c){
        contentDAO.updateContent(c);
    }

    public void deleteClo(int id){
        contentDAO.deleteContent(id);
    }
}
