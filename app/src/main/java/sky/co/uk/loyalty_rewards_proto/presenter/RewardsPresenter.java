package sky.co.uk.loyalty_rewards_proto.presenter;

import com.google.gson.Gson;

import java.util.Arrays;

import sky.co.uk.loyalty_rewards_proto.service.RewardsService;
import sky.co.uk.loyalty_rewards_proto.model.Reward;
import sky.co.uk.loyalty_rewards_proto.view.IRewardsView;

public class RewardsPresenter {

    private IRewardsView rewardsView;
    private RewardsService rewardsService;


    public RewardsPresenter(IRewardsView rewardsView, RewardsService rewardsService) {
        this.rewardsView = rewardsView;
        this.rewardsService = rewardsService;
    }

    public void displayMessagebasedOnResponse() {
        switch (rewardsService.getStatusCode()) {
            case 200:
                if (Arrays.asList(new Gson().fromJson(rewardsService.getResponse(), Reward[].class)).size() > 0) {
                    rewardsView.setRewardsMessageText("we would like to reward your loyalty with these following rewards, ");
                    rewardsView.setRewardsList();
                } else if (Arrays.asList(new Gson().fromJson(rewardsService.getResponse(), Reward[].class)).size() == 0) {
                    rewardsView.setRewardsMessageText("we don't have any rewards for now");
                }
                break;
            case 400:
                rewardsView.setRewardsMessageText("The Supplied account number is invalid");
                break;
            case 500:
                rewardsView.setRewardsMessageText("Technical failure exception");
                break;
            default:
                rewardsView.setRewardsMessageText("we would like to reward your loyalty with these following rewards, ");
                break;
        }
    }

    public String[] convertJsonToObject() {
        if ((rewardsService.getStatusCode() == 200) && (Arrays.asList(new Gson().fromJson(rewardsService.getResponse(), Reward[].class)).size() > 0)) {
            Reward[] rewardArray = new Gson().fromJson(rewardsService.getResponse(), Reward[].class);
            String[] array = new String[rewardArray.length];
            for (int i = 0; i < rewardArray.length; i++) {
                array[i] = rewardArray[i].getReward();
            }
            return array;
        }
        return null;
    }

}
