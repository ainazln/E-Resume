package com.example.resume;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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

public class Curricular extends AppCompatActivity {

    curricularJava co;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
    }

    public void readData() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factor = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factor.newDocumentBuilder();
        Document doc = dBuilder.parse(getAssets().open("Resume.xml"));

        doc.getDocumentElement().normalize();

        NodeList curricular = doc.getElementsByTagName("act");
        String[] listCurricular = new String[curricular.getLength()];
        for (int i = 0; i < curricular.getLength(); i++) {
            listCurricular[i] = curricular.item(i).getTextContent();
        }

        co = new curricularJava(listCurricular);

        Spinner spinProject = (Spinner) findViewById(R.id.spinnerActivity);
        ArrayAdapter<String> projectAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, co.curricular);
        projectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinProject.setAdapter(projectAdapter);
    }

    public void buttonClick4(View view) throws ParserConfigurationException, IOException, SAXException {
        Button btn = (Button) findViewById(R.id.buttonViewActivity);
        readData();
    }

    public void buttonStoreFB3(View view)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Create a new user
        Map<String, Object> CurricularFB = new HashMap<>();
        CurricularFB.put("curricular", Arrays.toString(co.curricular));

        // Add a new document with a generated ID
        db.collection("curricular")
                .add(CurricularFB)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference)
                    {
                        Toast.makeText(Curricular.this, "Done", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(Curricular.this, "Not Done Yet", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void buttonRetrieveFB3(View view)
    {
        //retrieve the document
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("curricular")
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
                                Toast.makeText(Curricular.this, document.getId() + " => " + document.getData(), Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(Curricular.this, "Failed to Retrieve", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}