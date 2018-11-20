package fall2018.csc207project.PushTheBox.Models;

import fall2018.csc207project.R;

/**
 * The box that is to be pushed by a person, to destination points.
 */
public class Box {

    /**
     * Position of the box.
     */
    private  int position;

    /**
     * The id of the image of when the box is not at a destination point.
     */
    private int normalImage = R.drawable.box;

    /**
     * The id of the image of when the box is at a destination point.
     */
    private int winningImage = R.drawable.winbox;

    /**
     * The id of current image of the box.
     */
    private int currImage = normalImage;

    /**
     * Whether the box is at a destination point.
     */
    private Boolean atDestination = false;


    /**
     * A new box with initial position.
     * @param position initial position
     */
    public Box(int position){
        this.position = position;
    }


    /**
     * Move the box to its new position.
     * @param position new position for the box
     */
    public void move(int position) {
        this.position = position;
    }

    /**
     * The box has arrived at a destination point.
     */
    public void arriveDestination(){
        atDestination = true;
        currImage = winningImage;
    }

    /**
     * The box have left a destination point.
     */
    public void  leaveDestination(){
        atDestination = false;
        currImage = normalImage;
    }


    /**
     * Returns whether this box is at a destination
     * @return whether this box is a a destination
     */
    public Boolean isAtDestination(){
        return atDestination;
    }


    /**
     * Return the position of this box.
     * @return the position of this box
     */
    public int getPosition(){
        return position;
    }

    /**
     * Return the image id of this box.
     * @return image id of this box
     */
    public int getImage(){
        return currImage;
    }



}
