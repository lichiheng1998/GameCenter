package fall2018.csc207project.Memorization.Models;

public class Memo {
    private int id;
    public static final 

    Memo(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(!(obj instanceof Memo)){
            return false;
        }
        Memo m = (Memo) obj;

        return Integer.compare(m.id, this.id) == 0;
    }
}
