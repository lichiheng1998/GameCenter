package fall2018.csc207project.Models;

import java.io.Serializable;
import java.util.Date;

public class Save implements Serializable{
    public final Date date;
    public final Object data;

    public Save(Date date, Object data){
        this.data = data;
        this.date = date;
    }
}
