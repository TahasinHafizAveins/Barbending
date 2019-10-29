package com.example.barbendingschedule.ui.home.main.helper;

import androidx.recyclerview.widget.RecyclerView;

public interface SwipeItemTouchHelperListener {
    void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
}
