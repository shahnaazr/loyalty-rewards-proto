package sky.co.uk.loyalty_rewards_proto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Campaign {


        @SerializedName("campaign")
        @Expose
        private String campaign;
        @SerializedName("rewards")
        @Expose
        private List<String> rewards = null;

        public String getCampaign() {
            return campaign;
        }

        public void setCampaign(String campaign) {
            this.campaign = campaign;
        }

        public List<String> getRewards() {
            return rewards;
        }

        public void setRewards(List<String> rewards) {
            this.rewards = rewards;
        }

    }