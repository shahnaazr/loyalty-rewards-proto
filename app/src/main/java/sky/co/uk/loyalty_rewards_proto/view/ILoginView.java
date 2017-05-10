package sky.co.uk.loyalty_rewards_proto.view;

public interface ILoginView {
    void setUsernameLabel();

    void setPasswordLabel();

    String getUsernameLabel();

    String getPasswordLabel();

    void setUsername(String username);

    void setPassword(String password);

    String getUsername();

    String getPassword();

    boolean submitButtonClicked();

    void showUsernameError();

    void showPasswordError();

    void navigateTo();
}
