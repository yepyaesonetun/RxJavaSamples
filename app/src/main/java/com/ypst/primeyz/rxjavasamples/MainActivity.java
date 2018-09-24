package com.ypst.primeyz.rxjavasamples;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_text)
    TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this, this);
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
