package sky.co.uk.loyalty_rewards_proto.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import sky.co.uk.loyalty_rewards_proto.CampaignAdapter;
import sky.co.uk.loyalty_rewards_proto.R;
import sky.co.uk.loyalty_rewards_proto.presenter.RewardsPresenter;
import sky.co.uk.loyalty_rewards_proto.service.RewardsService;
import sky.co.uk.loyalty_rewards_proto.view.IRewardsView;

public class RewardsActivity extends AppCompatActivity implements IRewardsView {

    private TextView rewardsLabelTextView;
    private ListView rewardsListView;
    private RewardsPresenter rewardsPresenter;
    private RewardsService rewardsService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);

        //String accountNumber = getIntent().getStringExtra("accountNumber");
        //String channels = getIntent().getStringExtra("channels");
        //int accountNUmber = Integer.parseInt( accountNumber);
        //String[] channelList = new Gson().fromJson(channels, String[].class);

        rewardsLabelTextView = (TextView) findViewById(R.id.rewards_text_view);
        rewardsListView = (ListView) findViewById(R.id.rewards_list_view);

        //In real time this data need not be instantiated
        this.rewardsService = new RewardsService(200, "[{\"campaign\": \"CAMPAIGN001\", \"rewards\": [\"CHAMPIONS_LEAGUE_FINAL_TICKET\"]}, {\"campaign\": \"CAMPAIGN002\", \"rewards\": [\"MARIO_KART_\"]}]");
        //this.rewardsService = new RewardsService(200, "[{\"reward\": \"CHAMPIONS_LEAGUE_FINAL_TICKET\"},{ \"reward\": \"KARAOKE_PRO_MICROPHONE\"},{ \"reward\": \"PIRATES_OF_THE_CARIBBEAN_COLLECTION\"}]");
        //this.rewardsService = new RewardsService(200,"[]");
        //this.rewardsService = new RewardsService(400,"customer error");
        //this.rewardsService = new RewardsService(500, "server error");

        rewardsPresenter = new RewardsPresenter(this, rewardsService);
        rewardsPresenter.displayMessagebasedOnResponse();
    }

    @Override
    public void setRewardsMessageText(String message) {
        rewardsLabelTextView.setText(message);
    }

    @Override
    public void setRewardsList() {
        rewardsListView = (ListView) findViewById(R.id.rewards_list_view);
        rewardsListView.setAdapter(new CampaignAdapter(this, 0, rewardsPresenter.convertJsonToObject()));
    }

    @Override
    public String getRewardsMessageText() {
        return rewardsLabelTextView.getText().toString();
    }

    @Override
    public String getRewardsList() {
        return rewardsListView.toString();
    }
}
