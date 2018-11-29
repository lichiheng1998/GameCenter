package fall2018.csc207project.Models;

import java.io.Serializable;
import java.util.Date;

/**
 * The class Save that implements Serializable.
 */
public class Save implements Serializable{

    /**
     * The current date.
     */
    public final Date date;

    /**
     * The data to be save.
     */
    public final Object data;

    /**
     * Construct a new Save by given a Date and an Object.
     *
     * @param date the current date
     * @param data the data to be save
     */
    Save(Date date, Object data){
        this.data = data;
        this.date = date;
    }
}
