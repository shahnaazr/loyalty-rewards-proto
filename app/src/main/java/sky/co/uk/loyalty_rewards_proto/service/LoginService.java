package sky.co.uk.loyalty_rewards_proto.service;

import com.google.gson.Gson;

import sky.co.uk.loyalty_rewards_proto.model.Customer;

public class LoginService {

    private static final String LOGIN_SERVICE = "{\"Account Number\": 1234,\"Channels\": [\"SPORTS\", \"MUSIC\", \"MOVIES\"]}";

    public LoginService() {
    }

    public Customer convertJsonToObject() {
        Gson gson = new Gson();
        return gson.fromJson(LOGIN_SERVICE, Customer.class);

    }

}
