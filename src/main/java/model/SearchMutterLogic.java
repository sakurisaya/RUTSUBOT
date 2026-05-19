package model;
import java.util.List;
import dao.MutterDAO;

public class SearchMutterLogic {
    public List<Mutter> execute(String keyword) {
        MutterDAO dao = new MutterDAO();
        return dao.findWithFilter(keyword);
    }
}
