package com.ericcode.rxhttpdemo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.ericcode.rxhttpdemo.base.BaseActivity;
import com.ericcode.rxhttpdemo.net.Api;
import com.ericcode.rxhttpdemo.net.bean.HotTopics;
import com.ericcode.rxhttpdemo.rx.ObserverFactory;
import com.ericcode.rxhttpdemo.util.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Logger.i("onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        Observer<List<HotTopics>> complete = ObserverFactory.create(
                this::onHotTopicsResp,
                Throwable::printStackTrace,
                () -> Logger.i("complete"));

//        Api.v2exRepo().getHotTopics().subscribe(complete);
//
//        Api.v2exRepo()
//                .getHotTopics()
//                .compose(IO2MainTransformer.create())
//                .compose(bindToLifecycle())
//                .compose(ProgressTransformer.create(this, false))
//                .subscribe(complete);
//
//        Api.v2exRepo(IO2MainTransformer.create(),
//                bindToLifecycle(),
//                ProgressTransformer.create(this))
//                .getHotTopics()
//                .subscribe(complete);

        Api.v2exRepo().getHotTopics().subscribe(complete);

    }

    private void onHotTopicsResp(List<HotTopics> hotTopics) {
        Logger.i("hot topic:" + hotTopics.size());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.i("onDestroy()");
    }
}
