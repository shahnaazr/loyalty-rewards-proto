package sky.co.uk.loyalty_rewards_proto.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import sky.co.uk.loyalty_rewards_proto.R;
import sky.co.uk.loyalty_rewards_proto.presenter.LoginPresenter;
import sky.co.uk.loyalty_rewards_proto.view.ILoginView;

public class MainActivity extends AppCompatActivity implements ILoginView {
    private TextView usernameTextView;
    private TextView passwordTextView;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MainActivity", "testing");
        usernameTextView = (TextView) findViewById(R.id.username_text_view);
        setUsernameLabel();
        passwordTextView = (TextView) findViewById(R.id.password_text_view);
        setPasswordLabel();
        usernameEditText = (EditText) findViewById(R.id.username_edit_text);
        passwordEditText = (EditText) findViewById(R.id.password_edit_text);

        Button submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setText("submit");

        loginPresenter = new LoginPresenter(this);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (submitButtonClicked() == false) {
                    navigateTo();
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void setUsernameLabel() {
        usernameTextView.setText("USERNAME: ");
    }

    @Override
    public void setPasswordLabel() {
        passwordTextView.setText("PASSWORD: ");
    }

    @Override
    public String getUsernameLabel() {
        return usernameTextView.getText().toString();
    }

    @Override
    public String getPasswordLabel() {
        return passwordTextView.getText().toString();
    }

    @Override
    public void setUsername(String username) {
        usernameEditText.setText(username);
    }

    @Override
    public void setPassword(String password) {
        passwordEditText.setText(password);
    }

    @Override
    public String getUsername() {
        return usernameEditText.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordEditText.getText().toString();
    }

    @Override
    public boolean submitButtonClicked() {
        return loginPresenter.onLoginClicked();
    }

    @Override
    public void showUsernameError() {
        usernameEditText.setError("username cannot be empty");
    }

    @Override
    public void showPasswordError() {
        usernameEditText.setError("password cannot be empty");
    }

    @Override
    public void navigateTo() {
        Intent intentForRewardActivity = new Intent(MainActivity.this, RewardsActivity.class);
        intentForRewardActivity.putExtra("accountNumber", Integer.toString(loginPresenter.getCustomer().getAccountNumber()));
        intentForRewardActivity.putExtra("channels", loginPresenter.getCustomer().getChannels().toArray().toString());
        startActivity(intentForRewardActivity);
    }
}
