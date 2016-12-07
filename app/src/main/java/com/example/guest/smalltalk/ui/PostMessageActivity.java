package com.example.guest.smalltalk.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.guest.smalltalk.Constants;
import com.example.guest.smalltalk.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PostMessageActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference mPostText;
    @Bind(R.id.postButton) Button mPostButton;
    @Bind(R.id.editPostText) EditText mEditPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPostText = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_POST_TEXT);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_message);
        ButterKnife.bind(this);

        mPostButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mPostButton) {
            String post = mEditPost.getText().toString();
            savePostToFirebase(post);
        }
    }

    private void savePostToFirebase(String post) {
        mPostText.push().setValue(post);
    }
}


