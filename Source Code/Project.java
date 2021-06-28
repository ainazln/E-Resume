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
import com.google.firebase.firestore.CollectionReference;
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

import javax.net.ssl.SSLSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Project extends AppCompatActivity {

    projectJava project;
    //private CollectionReference projectRef = FirebaseFirestore.getInstance().collection("project");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
    }

    public void readData() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factor = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factor.newDocumentBuilder();
        Document doc = dBuilder.parse(getAssets().open("Resume.xml"));

        doc.getDocumentElement().normalize();

        NodeList sysdev = doc.getElementsByTagName("proj");
        String[] listSysdev = new String[sysdev.getLength()];
        for (int i = 0; i < sysdev.getLength(); i++) {
            listSysdev[i] = sysdev.item(i).getTextContent();
        }

        project = new projectJava(listSysdev);

        Spinner spinProject = (Spinner) findViewById(R.id.spinnerProject);
        ArrayAdapter<String> projectAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, project.sysdev);
        projectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinProject.setAdapter(projectAdapter);
    }

    public void buttonClick3(View view) throws ParserConfigurationException, IOException, SAXException {
        Button btn = (Button) findViewById(R.id.buttonViewProject);
        readData();
    }

    public void buttonStoreFB1(View view)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Create a new user
        Map<String, Object> ProjectFB = new HashMap<>();
        ProjectFB.put("sysdev", Arrays.toString(project.sysdev));

        // Add a new document with a generated ID
        db.collection("project")
                .add(ProjectFB)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference)
                    {
                        Toast.makeText(Project.this, "Done", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(Project.this, "Not Done Yet", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void buttonRetrieveFB1(View view)
    {
        //retrieve the document
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("project")
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
                                Toast.makeText(Project.this, document.getId() + " => " + document.getData(), Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(Project.this, "Failed to Retrieve", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void buttonDeleteFB1(View view)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Add a new document with a generated ID
        db.collection("project")
                .document("8H55rOCP0moUSxTpXWKJ")
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Project.this, "Delete Successfully!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(Project.this, "Error Delete!", Toast.LENGTH_SHORT).show();
                    }
                });
    }


}