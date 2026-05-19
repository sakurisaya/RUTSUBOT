package model;
import dao.MutterDAO;

public class DeleteMutterLogic {
    public boolean execute(int id) {
        MutterDAO dao = new MutterDAO();
        return dao.delete(id);
    }
}
