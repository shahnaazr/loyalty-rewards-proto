package sky.co.uk.loyalty_rewards_proto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Customer {

    @SerializedName("Account Number")
    @Expose
    private Integer accountNumber;
    @SerializedName("Channels")
    @Expose
    private List<String> channels = null;

    public Customer(Integer accountNumber, List<String> channels) {
        this.accountNumber = accountNumber;
        this.channels = channels;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<String> getChannels() {
        return channels;
    }

    public void setChannels(List<String> channels) {
        this.channels = channels;
    }
}
