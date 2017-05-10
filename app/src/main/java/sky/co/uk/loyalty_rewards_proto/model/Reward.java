package sky.co.uk.loyalty_rewards_proto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reward {
    @SerializedName("reward")
    @Expose
    private String reward;

    public Reward(String reward) {
        this.reward = reward;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

}
