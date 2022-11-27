package com.example.libreriadecontactos.OnItemClickListener;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemClickListener implements RecyclerView.OnItemTouchListener{
    GestureDetector lectorGestos;
    private OnItemClickListener clickListener;
    public interface OnItemClickListener{
        void onItemClick(View v, int posicion);

    }
    public ItemClickListener(Context c, RecyclerView recyclerView, OnItemClickListener Listener) {
        clickListener = Listener;
        lectorGestos = new GestureDetector(c, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e){


            }
        });
    }
    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        View Child = rv.findChildViewUnder(e.getX(),e.getY());
        if(Child != null && clickListener != null && lectorGestos.onTouchEvent(e)){
            clickListener.onItemClick(Child,rv.getChildAdapterPosition(Child));
            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
