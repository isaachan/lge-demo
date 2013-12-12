package com.lge.showcase;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SignupActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button signup = (Button) this.findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView message = (TextView) SignupActivity.this.findViewById(R.id.message);
                TextView username = (TextView) SignupActivity.this.findViewById(R.id.username);
                if (username.length() < 0) {
                    message.setText("Username could not be empty.");
                    return;
                }
                message.setText(String.format("Sign up %s successfully.", username.getText()));
            }
        });
    }
}
