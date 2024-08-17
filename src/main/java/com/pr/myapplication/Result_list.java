package com.pr.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Result_list extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_list);

        // Initialize the ListView
        recyclerView = findViewById(R.id.resultList);

        // Initialize the Firebase Realtime Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("userinfo");

        // Get the search parameters from the previous activity or method
        String bloodGroup = getIntent().getStringExtra("bloodgroup");
        String location = getIntent().getStringExtra("address");

        // Execute the search query

        Query query1 = FirebaseDatabase.getInstance().getReference("userinfo").orderByChild("bloodgroup")
                .equalTo(bloodGroup);

        Query query2 = FirebaseDatabase.getInstance().getReference("userinfo").orderByChild("bloodgroup")
                .equalTo(location);

        Query query3 = FirebaseDatabase.getInstance().getReference("userinfo").limitToFirst(2);
        query3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> searchResults = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Assuming you have a "name" field in your data
                    String name = snapshot.getValue(String.class);
                    searchResults.add(name);
                }

                // Create an ArrayAdapter and set it to the ListView
                ArrayAdapter<String> adapter = new ArrayAdapter<>(Result_list.this,
                        android.R.layout.simple_list_item_1, searchResults);
                //recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors that occur
            }
        });
    }
}
