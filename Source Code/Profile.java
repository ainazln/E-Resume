package com.example.resume;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Profile extends AppCompatActivity {

    profileJava profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    public void readData() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factor = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factor.newDocumentBuilder();
        Document doc = dBuilder.parse(getAssets().open("Resume.xml"));

        doc.getDocumentElement().normalize();

        String name = doc.getElementsByTagName("name").item(0).getTextContent();
        String mobile = doc.getElementsByTagName("mobile").item(0).getTextContent();
        String email = doc.getElementsByTagName("email").item(0).getTextContent();
        String address = doc.getElementsByTagName("address").item(0).getTextContent();

        profile = new profileJava(name, mobile, email, address);

        TextView txtName = (TextView) findViewById(R.id.txtName);
        txtName.setText(profile.name);

        TextView txtMobile = (TextView) findViewById(R.id.txtMobile);
        txtMobile.setText(profile.mobile);

        TextView txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtEmail.setText(profile.email);

        TextView txtAddress = (TextView) findViewById(R.id.txtAddress);
        txtAddress.setText(profile.address);
    }

    public void buttonClick(View view) throws ParserConfigurationException, IOException, SAXException {
        Button btn = (Button) findViewById(R.id.buttonViewProfile);
        readData();
   }

    public void buttonStoreFB(View view)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Create a new user
        Map<String, Object> ProfileFB = new HashMap<>();
        ProfileFB.put("name", profile.name);
        ProfileFB.put("mobile", profile.mobile);
        ProfileFB.put("email", profile.email);
        ProfileFB.put("address", profile.address);

        // Add a new document with a generated ID
        db.collection("profile")
                .add(ProfileFB)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference)
                    {
                        Toast.makeText(Profile.this, "Done", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(Profile.this, "Not Done Yet", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void buttonRetrieveFB(View view)
    {
        //retrieve the document
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("profile")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
                {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if (task.isSuccessful())
                        {
                            for (QueryDocumentSnapshot document : task.getResult())
                            {
                                Toast.makeText(Profile.this, document.getId() + " => " + document.getData(), Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(Profile.this, "Failed to Retrieve", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }



}