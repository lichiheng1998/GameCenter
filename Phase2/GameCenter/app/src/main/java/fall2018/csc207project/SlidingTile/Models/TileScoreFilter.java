package fall2018.csc207project.SlidingTile.Models;

        import java.util.ArrayList;
        import java.util.List;

public class TileScoreFilter {

    public List<TileScore> filter(ArrayList<TileScore> filterList, int filterLevel){
        List res = new ArrayList();
        for (TileScore score: filterList){
            if (score.complexity == filterLevel ) {
                res.add(score);
            }
        }
        return res;
    }
}
