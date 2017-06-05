package sky.co.uk.loyalty_rewards_proto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import sky.co.uk.loyalty_rewards_proto.model.Campaign;
import sky.co.uk.loyalty_rewards_proto.presenter.RewardsPresenter;
import sky.co.uk.loyalty_rewards_proto.service.RewardsService;
import sky.co.uk.loyalty_rewards_proto.view.IRewardsView;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RewardsPresenterTest {
    @Mock
    private RewardsPresenter rewardsPresenter;

    @Mock
    private RewardsService rewardsService;

    @Mock
    private IRewardsView rewardsView;

    @Before
    public void setUp() throws Exception {
        rewardsPresenter = new RewardsPresenter(rewardsView, rewardsService);
    }

    @Captor
    ArgumentCaptor<String> stringCaptor;

    @Test
    public void testSuccessResponseWithRewardEligibility() {
        String expected = "we would like to reward your loyalty with these following rewards, ";

        when(rewardsService.getStatusCode()).thenReturn(200);
        when(rewardsService.getResponse()).thenReturn("[{\"campaign\": \"CAMPAIGN001\", \"rewards\": [\"CHAMPIONS_LEAGUE_FINAL_TICKET\"]}, {\"campaign\": \"CAMPAIGN002\", \"rewards\": [\"MARIO_KART_\"]}]");
        rewardsPresenter.displayMessagebasedOnResponse();
        verify(rewardsView).setRewardsMessageText(stringCaptor.capture());
        assertEquals(expected, stringCaptor.getValue());
    }

    @Test
    public void testSuccessResponseWithNoRewardEligibility() {
        String expected = "we don't have any rewards for now";

        when(rewardsService.getStatusCode()).thenReturn(200);
        when(rewardsService.getResponse()).thenReturn("[]");
        rewardsPresenter.displayMessagebasedOnResponse();
        verify(rewardsView).setRewardsMessageText(stringCaptor.capture());
        assertEquals(expected, stringCaptor.getValue());
    }

    @Test
    public void testResponseWithClientError() {
        String expected = "The Supplied account number is invalid";

        when(rewardsService.getStatusCode()).thenReturn(400);
        when(rewardsService.getResponse()).thenReturn("customer error");
        rewardsPresenter.displayMessagebasedOnResponse();
        verify(rewardsView).setRewardsMessageText(stringCaptor.capture());
        assertEquals(expected, stringCaptor.getValue());
    }

    @Test
    public void testResponseWithServerError() {
        String expected = "Technical failure exception";

        when(rewardsService.getStatusCode()).thenReturn(500);
        when(rewardsService.getResponse()).thenReturn("server error");
        rewardsPresenter.displayMessagebasedOnResponse();
        verify(rewardsView).setRewardsMessageText(stringCaptor.capture());
        assertEquals(expected, stringCaptor.getValue());
    }

    @Test
   public void testJsonToAObjectConversion() {
        Campaign campaign1 = new Campaign();
        campaign1.setCampaign("CAMPAIGN001");
        List<String> rewardsForCampaign1 = new ArrayList<String>();
        rewardsForCampaign1.add("CHAMPIONS_LEAGUE_FINAL_TICKET");
        campaign1.setRewards(rewardsForCampaign1);

        Campaign campaign2 = new Campaign();
        campaign1.setCampaign("CAMPAIGN001");
        List<String> rewardsForCampaign2 = new ArrayList<String>();
        rewardsForCampaign2.add("MARIO_KART_");
        campaign1.setRewards(rewardsForCampaign2);

         Campaign[] expectedArray = new Campaign[]{campaign1, campaign2};
        //List<Campaign> expectedArray = new ArrayList<>();
        //expectedArray.add(campaign1);
        //expectedArray.add(campaign2);

        when(rewardsService.getStatusCode()).thenReturn(200);
        when(rewardsService.getResponse()).thenReturn("[{\"campaign\": \"CAMPAIGN001\", \"rewards\": [\"CHAMPIONS_LEAGUE_FINAL_TICKET\"]}, {\"campaign\": \"CAMPAIGN002\", \"rewards\": [\"MARIO_KART_\"]}]");
       //assertEquals(expectedArray, rewardsPresenter.convertJsonToObject());
    }
}
