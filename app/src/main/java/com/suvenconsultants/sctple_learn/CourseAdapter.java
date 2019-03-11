package com.suvenconsultants.sctple_learn;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
    public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder> {
    private List<Course> courseList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, price, discount;
        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            price = (TextView) view.findViewById(R.id.price);
            discount = (TextView) view.findViewById(R.id.discount);
        }
    }

        public CourseAdapter(List<Course> courseList) {
            this.courseList = courseList;
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.course_list_row, parent, false);
            return new MyViewHolder(itemView);
        }
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Course course = courseList.get(position);
            holder.name.setText(course.getName());
            holder.price.setText(course.getPrice());

            holder.discount.setText(course.getDiscount());
        }
        @Override
        public int getItemCount() {
            return courseList.size();
        }
    }
