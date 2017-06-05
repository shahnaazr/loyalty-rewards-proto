package sky.co.uk.loyalty_rewards_proto;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import sky.co.uk.loyalty_rewards_proto.model.Campaign;

public class CampaignAdapter  extends ArrayAdapter<Campaign> {
    public CampaignAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull Campaign[] campaigns) {
        super(context, 0, campaigns);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Campaign campaign = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_view, parent, false);
        }
        // Lookup view for data population
        TextView campaignName = (TextView) convertView.findViewById(R.id.campaignName);
        ListView innerRewards = (ListView) convertView.findViewById(R.id.innerrewards_list_view);
        // Populate the data into the template view using the data object
        campaignName.setText(campaign.getCampaign());
        innerRewards.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, campaign.getRewards()));
        // Return the completed view to render on screen
        return convertView;
    }

}
