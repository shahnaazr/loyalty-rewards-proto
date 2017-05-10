package sky.co.uk.loyalty_rewards_proto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import sky.co.uk.loyalty_rewards_proto.presenter.RewardsPresenter;
import sky.co.uk.loyalty_rewards_proto.service.RewardsService;
import sky.co.uk.loyalty_rewards_proto.view.IRewardsView;

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

    @Test
    public void testSuccessResponseWithRewardEligibility() {
        when(rewardsService.getStatusCode()).thenReturn(200);
        when(rewardsService.getResponse()).thenReturn("[{\"reward\": \"CHAMPIONS_LEAGUE_FINAL_TICKET\"},{ \"reward\": \"KARAOKE_PRO_MICROPHONE\"},{ \"reward\": \"PIRATES_OF_THE_CARIBBEAN_COLLECTION\"}]");
        rewardsPresenter.displayMessagebasedOnResponse();
        verify(rewardsView).getRewardsMessageText().compareToIgnoreCase("we would like to reward your loyalty with these following rewards, ");
    }

    @Test
    public void testSuccessResponseWithNoRewardEligibility() {
        when(rewardsService.getStatusCode()).thenReturn(200);
        when(rewardsService.getResponse()).thenReturn("[]");
        rewardsPresenter.displayMessagebasedOnResponse();
        verify(rewardsView).getRewardsMessageText().compareToIgnoreCase("we don't have any rewards for now");
    }

    @Test
    public void testResponseWithCLientError() {
        when(rewardsService.getStatusCode()).thenReturn(400);
        when(rewardsService.getResponse()).thenReturn("customer error");
        rewardsPresenter.displayMessagebasedOnResponse();
        verify(rewardsView).getRewardsMessageText().compareToIgnoreCase("Technical failure exception");
    }

    @Test
    public void testResponseWithServerError() {
        when(rewardsService.getStatusCode()).thenReturn(500);
        when(rewardsService.getResponse()).thenReturn("server error");
        rewardsPresenter.displayMessagebasedOnResponse();
        verify(rewardsView).getRewardsMessageText().compareToIgnoreCase("we would like to reward your loyalty with these following rewards, ");
    }
}
