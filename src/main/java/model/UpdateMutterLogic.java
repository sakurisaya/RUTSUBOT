package model;
import dao.MutterDAO;

public class UpdateMutterLogic {
    public boolean execute(Mutter mutter) {
        MutterDAO dao = new MutterDAO();
        return dao.update(mutter);
    }
}
