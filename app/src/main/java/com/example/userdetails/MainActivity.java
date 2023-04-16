package com.example.userdetails;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Define the SQLite database variables
    SQLiteDatabase database;
    String databaseName = "UserDatabase";
    String tableName = "UserDetails";

    // Define the UI variables
    EditText nameEditText, emailEditText, contactEditText, addressEditText, dobEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the UI variables
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        contactEditText = findViewById(R.id.contactEditText);
        addressEditText = findViewById(R.id.addressEditText);
        dobEditText = findViewById(R.id.dobEditText);

        // Create the database and table if they do not already exist
        createDatabaseAndTable();

        // Set click listeners for the buttons
        Button insertButton = findViewById(R.id.insertButton);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

        Button updateButton = findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });

        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
            }
        });

        Button viewButton = findViewById(R.id.viewButton);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewData();
            }
        });
    }

    // Create the database and table if they do not already exist
    private void createDatabaseAndTable() {
        try {
            database = openOrCreateDatabase(databaseName, MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS " + tableName +
                    "(Name TEXT, Email TEXT, Contact TEXT, Address TEXT, DOB TEXT);");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Insert data into the database
    private void insertData() {
        try {
            String name = nameEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String contact = contactEditText.getText().toString();
            String address = addressEditText.getText().toString();
            String dob = dobEditText.getText().toString();
            if (name.isEmpty() || email.isEmpty() || contact.isEmpty() || address.isEmpty() || dob.isEmpty()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            } else {
                String query = "INSERT INTO " + tableName +
                        "(Name, Email, Contact, Address, DOB) VALUES('" +
                        name + "', '" + email + "', '" + contact + "', '" +
                        address + "', '" + dob + "');";
                database.execSQL(query);
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                clearEditTextFields();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Update data in the database
    // Update data in the database
    private void updateData() {
        try {
            String name = nameEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String contact = contactEditText.getText().toString();
            String address = addressEditText.getText().toString();
            String dob = dobEditText.getText().toString();
            if (name.isEmpty() || email.isEmpty() || contact.isEmpty() || address.isEmpty() || dob.isEmpty()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            } else {
                String query = "UPDATE " + tableName +
                        " SET Email='" + email + "', Contact='" + contact + "', Address='" +
                        address + "', DOB='" + dob + "' WHERE Name='" + name + "';";
                database.execSQL(query);
                Toast.makeText(this, "Data updated successfully", Toast.LENGTH_SHORT).show();
                clearEditTextFields();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete data from the database
    private void deleteData() {
        try {
            String name = nameEditText.getText().toString();
            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter name to delete", Toast.LENGTH_SHORT).show();
            } else {
                String query = "DELETE FROM " + tableName + " WHERE Name='" + name + "';";
                database.execSQL(query);
                Toast.makeText(this, "Data deleted successfully", Toast.LENGTH_SHORT).show();
                clearEditTextFields();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View data from the database
    private void viewData() {
        try {
            String query = "SELECT * FROM " + tableName + ";";
            Cursor cursor = database.rawQuery(query, null);
            if (cursor.getCount() == 0) {
                Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
            } else {
                StringBuilder data = new StringBuilder();
                while (cursor.moveToNext()) {
                    data.append("Name: ").append(cursor.getString(0)).append("\n");
                    data.append("Email: ").append(cursor.getString(1)).append("\n");
                    data.append("Contact: ").append(cursor.getString(2)).append("\n");
                    data.append("Address: ").append(cursor.getString(3)).append("\n");
                    data.append("DOB: ").append(cursor.getString(4)).append("\n\n");
                }
                Toast.makeText(this, data.toString(), Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Clear all edit text fields
    private void clearEditTextFields() {
        nameEditText.setText("");
        emailEditText.setText("");
        contactEditText.setText("");
        addressEditText.setText("");
        dobEditText.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.close();
    }
}
