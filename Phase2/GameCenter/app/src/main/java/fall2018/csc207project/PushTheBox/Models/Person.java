package fall2018.csc207project.PushTheBox.Models;

import java.util.Observable;

import fall2018.csc207project.R;

/**
 * The person who will be pushing the boxes.
 */
public class Person extends Observable {

    /**
     * The position of the person.
     */
    private int position;

    /**
     * The ID of the image of the person.
     */
    private int imageId = R.drawable.person;

    /**
     * A new person standing on given position.
     * @param position initial position of this person
     */
    public Person(int position){
        this.position = position;
    }

    /**
     * The person walks to the new position.
     * @param posChange the changing of position to get new position
     */
    public void walk(int posChange) {
        position = position + posChange;
        faceTo(posChange);
        setChanged();
        notifyObservers();
    }

    /**
     * Change the direction the person facing to the direction he is going to.
     * @param posChange if it is -1, then face to the left. If it is 1, then face to the right. Otherwise, face forward.
     */
    private void faceTo(int posChange){
        if (posChange == -1){
            imageId = R.drawable.lperson;
        }else if (posChange == 1){
            imageId = R.drawable.rperson;
        }else if (posChange < 0 ){
            imageId = R.drawable.uperson;
        }else{
            imageId = R.drawable.person;
        }
    }

    /**
     * Return the position value of this person.
     * @return position of this person
     */
    public int getPosition(){
        return position;
    }

    /**
     * Return the image id of this person.
     * @return image id of this person
     */
    public int getImage(){
        return imageId;
    }

}
