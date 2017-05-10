package sky.co.uk.loyalty_rewards_proto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import sky.co.uk.loyalty_rewards_proto.model.Customer;
import sky.co.uk.loyalty_rewards_proto.service.LoginService;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

    @Mock
    private LoginService loginService;

    @Before
    public void setUp() throws Exception {
        loginService = new LoginService();
    }

    @Test
    public void testJsonToCustomerObjectConversion() {
        List<String> channels = new ArrayList<>();
        channels.add("SPORTS");
        channels.add("MUSIC");
        channels.add("MOVIES");

        Customer customer = new Customer(1234, channels);

        assertEquals(loginService.convertJsonToObject().getAccountNumber(), customer.getAccountNumber());
        assertArrayEquals(loginService.convertJsonToObject().getChannels().toArray(), customer.getChannels().toArray());

    }
}
