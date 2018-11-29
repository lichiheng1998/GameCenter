package fall2018.csc207project.Views;

/**
 * Display the SignUp on screen.
 */
public interface SignUpView {

    /**
     * make a fail to sign-up Text.
     */
    void makeSignUpFailedText();

    /**
     * Switch to the LocalGameCenterActivity view
     * for the current user that just signs up.
     */
    void localCenter();
}
