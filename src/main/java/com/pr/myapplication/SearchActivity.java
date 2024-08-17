package com.pr.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private EditText editTextBloodGroup;
    private EditText editTextLocation;
    private Button buttonSearch;
    private Object Result_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editTextBloodGroup = findViewById(R.id.patient_blood_grp);
        editTextLocation = findViewById(R.id.patient_address);
        buttonSearch = findViewById(R.id.searchbtn);


        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bloodGroup = editTextBloodGroup.getText().toString().trim();
                String location = editTextLocation.getText().toString().trim();

                Intent intent = new Intent(SearchActivity.this,Result_list.class);
                startActivity(intent);
                // Perform the search and open the SearchResultsActivity
                //searchBloodGroupAndLocation(bloodGroup, location);
            }
        });
    }
/*
    private void searchBloodGroupAndLocation(String bloodGroup, String location) {
        // Execute the search query and pass the results to the SearchResultsActivity




        Query query = FirebaseDatabase.getInstance().getReference("userinfo")
                .orderByChild("bloodGroupLocation")
                .equalTo(bloodGroup + "_" + location);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> searchResults = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String name = snapshot.child("name").getValue(String.class);
                    Result_list.equals(name);
                }

                // Pass the search results to the SearchResultsActivity
                Intent intent = new Intent(SearchActivity.this, Result_list.class);
                Intent searchResults1;
                searchResults1 = intent.putStringArrayListExtra("searchResults",
                        (ArrayList<String>) Result_list);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors that occur
            }
        });

         */

}
