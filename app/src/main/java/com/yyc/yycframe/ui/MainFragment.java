package com.yyc.yycframe.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yyc.yycframe.R;
import com.yyc.yycframe.base.BaseSimpleFragment;
import com.yyc.yycframe.weight.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainFragment extends BaseSimpleFragment {

    @BindView(R.id.rv_main_fragment)
    RecyclerView mRecyclerView;

    @Override
    protected int layout() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTitleBar.setTitleText("Main Fragment");
        mTitleBar.setTitleBarBackgroundRes(R.color.colorPrimary);

        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(4, 4));
        mRecyclerView.setAdapter(new MyAdapter());
    }

    @Override
    protected boolean useDefaultTitleBar() {
        return true;
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private List<String> mDatas = new ArrayList<>();

        public MyAdapter() {
            mDatas.add("");
            mDatas.add("");
            mDatas.add("");
            mDatas.add("");
            mDatas.add("");
            mDatas.add("");
            mDatas.add("");
            mDatas.add("");
            mDatas.add("");
            mDatas.add("");
            mDatas.add("");
            mDatas.add("");
            mDatas.add("");
            mDatas.add("");
            mDatas.add("");
            mDatas.add("");
            mDatas.add("");
            mDatas.add("");
            mDatas.add("");
            mDatas.add("");
            mDatas.add("");
            mDatas.add("");
            mDatas.add("");
            mDatas.add("");
            mDatas.add("");
            mDatas.add("");
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_main_fragment, viewGroup, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }
}
