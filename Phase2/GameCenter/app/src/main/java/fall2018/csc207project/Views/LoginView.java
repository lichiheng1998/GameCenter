package fall2018.csc207project.Views;

/**
 * Display the Login on screen.
 */
public interface LoginView {

    /**
     * make a fail to login Text.
     */
    void makeLoginFailedText();

    /**
     * Switch to the LocalGameCenterActivity view
     * for the current user that just logs in.
     */
    void localCenter();
}
