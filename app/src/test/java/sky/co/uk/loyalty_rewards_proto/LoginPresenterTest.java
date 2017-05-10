package sky.co.uk.loyalty_rewards_proto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import sky.co.uk.loyalty_rewards_proto.presenter.LoginPresenter;
import sky.co.uk.loyalty_rewards_proto.view.ILoginView;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {

    @Mock
    private LoginPresenter loginPresenter;

    @Mock
    private ILoginView loginView;

    @Before
    public void setUp() throws Exception {
        loginPresenter = new LoginPresenter(loginView);
    }

    @Test
    public void testEmptyUserName() {
        when(loginView.getUsername()).thenReturn("");
        loginPresenter.onLoginClicked();
        verify(loginView).showUsernameError();
    }

    @Test
    public void testEmptyPassword() {
        when(loginView.getUsername()).thenReturn("SHAH");
        when(loginView.getPassword()).thenReturn("");
        loginPresenter.onLoginClicked();
        verify(loginView).showPasswordError();
    }
}