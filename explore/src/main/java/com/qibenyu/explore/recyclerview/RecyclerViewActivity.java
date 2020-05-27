package com.qibenyu.explore.recyclerview;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.qibenyu.explore.R;

import java.util.ArrayDeque;
import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class RecyclerViewActivity extends Activity {

    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        mRecyclerView.findViewById(R.id.recycler_view);

//        mRecyclerView.setHasFixedSize();
//        LinearLayoutManager llm = new LinearLayoutManager();
//        llm.setInitialPrefetchItemCount();

//        ArrayDeque<Integer> deque = new ArrayDeque<>();
//        ObjectAnimator.ofFloat()


    }

    static class ItemAdapter extends ListAdapter {

        private List<String> data;

        protected ItemAdapter(@NonNull DiffUtil.ItemCallback diffCallback) {
            super(diffCallback);
        }

//        public ItemAdapter(List<String> data) {
//            super();
//            this.data = data;
//            AsyncListDiffer.
//        }

        public void swapData(List<String> newData, boolean diff) {
            if (diff) {

                DiffUtil.DiffResult result = DiffUtil.calculateDiff(new UserDiff(data,newData), false);
                data = newData;
                result.dispatchUpdatesTo(this);
            } else {
                data = newData;
                notifyDataSetChanged();
            }
        }

        @Override
        public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        }

        @Override
        public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List payloads) {
            super.onBindViewHolder(holder, position, payloads);
        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    static class UserDiff extends DiffUtil.Callback {

        private List<String> newD;
        private List<String> oldD;

        public UserDiff(List<String> newD, List<String> oldD) {
            this.newD = newD;
            this.oldD = oldD;
        }

        @Override
        public int getOldListSize() {
            return 0;
        }

        @Override
        public int getNewListSize() {
            return 0;
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return false;
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {

            Bundle bundle = new Bundle();
            newD.get(newItemPosition);
            return null;
        }
    }

}
