package com.soushetty.contactmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.soushetty.contactmanager.Model.Contacts;
import com.soushetty.contactmanager.adapter.RecyclerViewAdapter;
import com.soushetty.contactmanager.data.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

/* Main Activity class which displays Oncreate actions to be performed.
* -performs CRUD operations on the database by calling databaseHandler.java class
* -calls RecyclerViewAdapter.java to show the list views of each contacts by using recycler view*/

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Contacts> contactarrayList;
  //  private ArrayAdapter<String> contactarrayAdapter;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // listView=findViewById(R.id.listview);
        contactarrayList=new ArrayList<>();
        DatabaseHandler handler=new DatabaseHandler(MainActivity.this);
        //Log.d("Final","count "+handler.getcount());

        //to list the contacts as list.Getting the Recycler view which is constructed inside activity_main.xml
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//shows items in a vertical or horizontal scrolling list.

        List<Contacts> contactsList=handler.getallcontacts();
        for(Contacts contacts:contactsList){
            Log.d("contacts","added are "+contacts.getId()+" "+contacts.getName());
            contactarrayList.add(contacts);

        }

        recyclerViewAdapter=new RecyclerViewAdapter(MainActivity.this,contactarrayList);
        recyclerView.setAdapter(recyclerViewAdapter);



        /*Contacts c1=new Contacts();
        c1.setName("sweety");
        c1.setPhone_number("9902659234");
        handler.addContact(c1);
        Contacts c2=new Contacts();
        c2.setPhone_number("2017569234");
        c2.setName("prath");
        handler.addContact(c2);
        handler.addContact(new Contacts("shaila","9449772379"));
        handler.addContact(new Contacts("sarvo","9591510509"));
        handler.addContact(new Contacts("hema","9883451291"));
        handler.addContact(new Contacts("prathvi","9003468920"));
        handler.addContact(new Contacts("sourabha","9893468934"));
        handler.addContact(new Contacts("rahul","8993468920"));
        handler.addContact(new Contacts("pratheek","9752345678"));
        handler.addContact(new Contacts("kevin","8123095637"));*/

       /*         // updating the second contact
        Contacts c3=handler.getContact(2);
        c3.setName("chinnu");
        c3.setPhone_number("21224535456");
        int updatecontact =handler.updatecontact(c3);
        Log.d("update",""+updatecontact);
        Contacts c4=handler.getContact(3);
        c4.setName("kevin");
        c4.setPhone_number("89063540");
        int update =handler.updatecontact(c4);
        Log.d("update",""+update);
        //delete
        handler.deletecontact(c4);
        handler.deletecontact(c1);*/


        //creating array adapter
        /*contactarrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,contactarrayList);
        //adding it to listview
        listView.setAdapter(contactarrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("onclick"," "+contactarrayList.get(position));
            }
        });*/




    }
}
