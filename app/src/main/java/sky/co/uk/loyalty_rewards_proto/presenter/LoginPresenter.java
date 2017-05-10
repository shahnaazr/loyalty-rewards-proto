package sky.co.uk.loyalty_rewards_proto.presenter;

import sky.co.uk.loyalty_rewards_proto.service.LoginService;
import sky.co.uk.loyalty_rewards_proto.model.Customer;
import sky.co.uk.loyalty_rewards_proto.view.ILoginView;

public class LoginPresenter {

    private ILoginView loginView;
    private LoginService loginService;
    private Customer customer;


    public LoginPresenter(ILoginView loginView) {

        this.loginView = loginView;
        loginService = new LoginService();
        customer = loginService.convertJsonToObject();
    }

    public boolean onLoginClicked() {

        if (loginView.getUsername().isEmpty()) {
            loginView.showUsernameError();
            return true;
        }
        if (loginView.getPassword().isEmpty()) {
            loginView.showPasswordError();
            return true;
        }
        validateLogin();
        return false;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void navigateTo() {
        loginView.navigateTo();
    }

    public Customer validateLogin() {
        loginService = new LoginService();
        return loginService.convertJsonToObject();
    }
}
