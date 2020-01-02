package com.soushetty.contactmanager.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.soushetty.contactmanager.Model.Contacts;
import com.soushetty.contactmanager.R;
import com.soushetty.contactmanager.SecondPage;

import java.util.List;

import static com.soushetty.contactmanager.R.layout.contacts_row_view;
/*override two main methods: one to inflate the view and its view holder, and another one to bind data to the view.
The good thing about RecyclerViewAdapter is that the first method is called only when we really need to create a new view*/
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Contacts> contactsList;
/* from the MainActivity context and contacts array list is passed */
    public RecyclerViewAdapter(Context context, List<Contacts> contactsList) {
        this.context = context;
        this.contactsList = contactsList;
    }
/* Implement all the methods from the super class RecyclerView.Adapter*/
    @NonNull
    @Override
    /*to show only the data that a particular user desired to see,create a data object*/
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        //creating an object 'view' to inflate all the contacts information from the contacts_row_view.xml file
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(contacts_row_view,viewGroup,false);
        return new ViewHolder(view); //passing the view object created to ViewHolder class to get access to each items in it
    }

    @Override
    /*Binding the contacts and the Recycler View i.e Binding the view and data source*/
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contacts contacts=contactsList.get(position);
        holder.name.setText(contacts.getName());          //retrieving the data from Contacts class for the particular row and adding it to the View
        holder.number.setText(contacts.getPhone_number());
    }

    @Override
    /*count of the data that comes in*/
    public int getItemCount() {
        return contactsList.size(); //returning the size of contacts stored in List
    }
    /* defining a new class ViewHolder as a subclass to access the Ui elements and send data to it*/
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView name;
        public TextView number;
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name=itemView.findViewById(R.id.name);
            number=itemView.findViewById(R.id.phonenumber);
            imageView=itemView.findViewById(R.id.image);
            imageView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            Contacts contacts=contactsList.get(position);
            /*using intent to go to the second page when the user clicks on the image*/
            Intent intent=new Intent(context, SecondPage.class);
            /*sending data to another activity using adapter*/
            intent.putExtra("name",contacts.getName());
            intent.putExtra("phone_number",contacts.getPhone_number());
            context.startActivity(intent);

            //Log.d("onclicking :",""+contacts.getPhone_number());
        }
    }
}

