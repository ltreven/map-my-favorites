package com.example.mapmyfavorites;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.ref.WeakReference;

public class MarkerFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marker_form);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button saveButton = findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = getIntent();
                String latitude = i.getStringExtra("latitude");
                String longitude = i.getStringExtra("longitude");

                EditText title = findViewById((R.id.title));
                EditText description = findViewById((R.id.description));

                String message = "Lat: " + latitude
                        + ", longitude: " + longitude
                        + ". Title: " + title.getText()
                        + ", description: " + description.getText();

                Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Place place = new Place(title.getText().toString(),
                        description.getText().toString(),
                        Double.valueOf(latitude).doubleValue(),
                        Double.valueOf(longitude).doubleValue());


                //PlaceViewModel placeViewModel .insert(place);
                AgentAsyncTask background = new AgentAsyncTask(getBaseContext(), place);
                background.execute();
                finish();
            }
        });
    }

    private static class AgentAsyncTask extends AsyncTask<Void, Void, Integer> {

        private Place place;
        private Context ctx;

        public AgentAsyncTask(Context ctx, Place place) {
            this.ctx = ctx;
            this.place = place;
        }

        @Override
        protected Integer doInBackground(Void... params) {
            MapsDatabase.getDatabase(ctx).placeDao().insert(place);
            return null;
        }

    }

}

