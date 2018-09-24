package com.ypst.primeyz.rxjavasamples;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ypst.primeyz.rxjavasamples.data.vo.CountryVO;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_text)
    TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this, this);
    }

    @OnClick(R.id.btn_in_code_two)
    public void onTapBtnInCodeTwo(View view) {
        tvText.setText("");

        RxJavaApp rxJavaApp = (RxJavaApp) getApplication();

        Observable<ArrayList<CountryVO>> countryListObservable = rxJavaApp.getCountryAPI().getAllCountries();
        countryListObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ArrayList<CountryVO>>() {
                    @Override
                    public void onNext(ArrayList<CountryVO> countryVOS) {

                        for (CountryVO vo : countryVOS) {

                            tvText.setText(String.format("%sRx Api : \"%s\" \n", tvText.getText(), vo.getName()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        tvText.setText(String.format("onError : %s", e.getMessage()));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @OnClick(R.id.btn_in_code_one)
    public void onTapBtnInCodeOne(View view) {
        tvText.setText("");
        helloRxJava("Hello", "Prime", "YZ", ":", "Android", "RxJava", "SampleOne");
    }

    private void helloRxJava(String... names) {
        Observable<String> nameObservable = Observable.fromArray(names);
        nameObservable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @SuppressLint("DefaultLocale")
            @Override
            public void onNext(@NonNull String name) {
                Log.d(RxJavaApp.TAG, "Rx : \"" + name + "\"" + " has " + name.length() + " characters.");
                tvText.setText(String.format("%sRx : \"%s\" has %d characters.\n", tvText.getText(), name, name.length()));
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

}
