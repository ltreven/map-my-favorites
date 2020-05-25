package com.example.mapmyfavorites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

public class ListPlaces extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_places);

        Button close = findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        final TextView view = findViewById(R.id.placesView);

        final PlaceViewModel placeViewModel = new PlaceViewModel(this.getApplication());

        placeViewModel.getAllPlaces().observe(this, new Observer<List<Place>>() {
            @Override
            public void onChanged(List<Place> places) {
                System.out.println("places: ");
                System.out.println(places);
                Iterator it = places.iterator();
                String str = "";
                while(it.hasNext()) {
                    Place place = (Place)it.next();
                    str = str + place.getTitle() + " - " + place.getDescription() + "\n\n";
                }
                view.setText(str);

            }
        });


    }


}
