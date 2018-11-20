package fall2018.csc207project.Memorization.model;

public class Memo {
    private int id;

    Memo(int id){
        this.id = id + 1;
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
