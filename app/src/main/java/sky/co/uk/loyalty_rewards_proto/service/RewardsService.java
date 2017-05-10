package sky.co.uk.loyalty_rewards_proto.service;

public class RewardsService {

    private int statusCode;
    private String response;

    public RewardsService(int statusCode, String response) {
        this.statusCode = statusCode;
        this.response = response;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getResponse() {
        return response;
    }


}
