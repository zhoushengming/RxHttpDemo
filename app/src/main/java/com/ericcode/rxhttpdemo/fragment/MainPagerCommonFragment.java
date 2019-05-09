package com.ericcode.rxhttpdemo.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ericcode.rxhttpdemo.R;
import com.ericcode.rxhttpdemo.base.BaseFragment;
import com.ericcode.rxhttpdemo.net.Api;
import com.ericcode.rxhttpdemo.net.bean.HotTopics;
import com.ericcode.rxhttpdemo.rx.ObserverFactory;
import com.ericcode.rxhttpdemo.rx.transformer.SwipeRefreshTransformer;
import com.ericcode.rxhttpdemo.util.FormatUtil;
import com.ericcode.rxhttpdemo.util.ImageLoader;
import com.ericcode.rxhttpdemo.util.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 */
public class MainPagerCommonFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_pager_common;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BaseQuickAdapter<HotTopics, BaseViewHolder> adapter = new BaseQuickAdapter<HotTopics, BaseViewHolder>(R.layout.item_main_pager) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, HotTopics topics) {
                baseViewHolder.setText(R.id.userName, topics.getMember().getUsername());
                baseViewHolder.setText(R.id.title, topics.getTitle());
                baseViewHolder.setText(R.id.context, topics.getContent());
                baseViewHolder.setText(R.id.lastModify, FormatUtil.formatTime(topics.getLast_modified() * 1000));
                baseViewHolder.setText(R.id.replies, topics.getReplies() + "回复");
                baseViewHolder.setText(R.id.topic, topics.getNode().getName());
                ImageView avatar = baseViewHolder.getView(R.id.avatar);
                String avatar_normal = topics.getMember().getAvatar_large();
                avatar_normal = "https:" + avatar_normal;
                Logger.i("avatar_normal:" + avatar_normal);
                ImageLoader.load(avatar_normal, avatar);
            }
        };

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        swipeRefreshLayout.setOnRefreshListener(() -> doRefresh(adapter));
        swipeRefreshLayout.setRefreshing(true);
        doRefresh(adapter);
    }

    private void doRefresh(BaseQuickAdapter<HotTopics, BaseViewHolder> adapter) {
        Api.v2exRepo(this)
                .getHotTopics()
                .compose(SwipeRefreshTransformer.create(swipeRefreshLayout))
                .subscribe(ObserverFactory.create(
                        adapter::setNewData));
    }

}
