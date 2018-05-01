package com.example.notesapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class ListviewAddapter extends ArrayAdapter<Contact> {

        private Context context;

        public ListviewAddapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
            super(context, resource, objects);
            this.context = context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Contact contact = getItem(position);
            ViewHolder viewHolder;
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_new_contact, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.setName((TextView) convertView.findViewById(R.id.textViewName));
                viewHolder.setPhoneno((TextView) convertView.findViewById(R.id.textViewPhoneNo));
                viewHolder.setEmail((TextView) convertView.findViewById(R.id.textViewEmail));
                viewHolder.setDepartment((TextView) convertView.findViewById(R.id.textViewDepartment));

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.getName().setText(contact.getName());
            viewHolder.getEmail().setText(contact.getEmail());
            viewHolder.getPhoneno().setText(contact.getPhone());
            viewHolder.getDepartment().setText(contact.getDepartment());

            return convertView;

        }


    public static class ViewHolder{
            TextView name,phoneno,email,department;

        public TextView getName() {
            return name;
        }

        public void setName(TextView name) {
            this.name = name;
        }

        public TextView getPhoneno() {
            return phoneno;
        }

        public void setPhoneno(TextView phoneno) {
            this.phoneno = phoneno;
        }

        public TextView getEmail() {
            return email;
        }

        public void setEmail(TextView email) {
            this.email = email;
        }

        public TextView getDepartment() {
            return department;
        }

        public void setDepartment(TextView department) {
            this.department = department;
        }
    }

}
