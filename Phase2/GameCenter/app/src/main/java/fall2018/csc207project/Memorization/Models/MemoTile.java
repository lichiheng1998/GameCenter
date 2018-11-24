package fall2018.csc207project.Memorization.Models;

import java.io.Serializable;

public class MemoTile implements Serializable{
    private int id;
    public static final int TYPEACTIVE = 1;
    public static final int TYPEFAKE = 0;

    public final int status;

    MemoTile(int id, int status){
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(!(obj instanceof MemoTile)){
            return false;
        }
        MemoTile m = (MemoTile) obj;

        return Integer.compare(m.id, this.id) == 0;
    }
}
