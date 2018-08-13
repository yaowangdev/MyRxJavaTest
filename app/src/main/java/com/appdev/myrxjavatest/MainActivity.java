package com.appdev.myrxjavatest;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test1(View view) {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("大保健");
                emitter.onNext("泡吧");
                emitter.onNext("K歌");
                emitter.onError(null);
                emitter.onComplete();

            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("yaowang","onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.d("yaowang","onNext="+s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("yaowang","onError");

            }

            @Override
            public void onComplete() {
                Log.d("yaowang","onComplete");

            }
        });
    }

    @SuppressLint("CheckResult")
    public void test2(View view) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("大保健");
                emitter.onNext("泡吧");
                emitter.onNext("K歌");
//                emitter.onError(null);
                emitter.onComplete();

            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d("yaowang","onNext="+s);

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d("yaowang","onError");

            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                Log.d("yaowang","onComplete");

            }
        }, new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                Log.d("yaowang","onSubscribe");

            }
        });


    }

    public void test3(View view) {
        Observable.just("大保健","泡吧","K歌").subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("yaowang","onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.d("yaowang","onNext="+s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("yaowang","onError");

            }

            @Override
            public void onComplete() {
                Log.d("yaowang","onComplete");

            }
        });
    }

    public void test4(View view) {
        Observable.fromArray("大保健","泡吧","K歌").subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("yaowang","onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.d("yaowang","onNext="+s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("yaowang","onError");

            }

            @Override
            public void onComplete() {
                Log.d("yaowang","onComplete");

            }
        });
    }

    @SuppressLint("CheckResult")
    public void test5(View view) {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                for(int i=0;i<10000;i++){
                    emitter.onNext(i);
                }
                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.newThread())
                .subscribe(new Consumer<Integer>() {

                    private boolean dis;

                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d("yaowang", integer.toString());
                        Thread.sleep(1000);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("yaowang", throwable.toString());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d("yaowang", "onComplete");
                    }
                }, new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {

                        subscription.request(Long.MAX_VALUE);
                    }
                });
    }

    public void test6(View view) {
        List<String> list = new ArrayList<>();
        list.add("s1");
        list.add("s2");
        Observable.just(list).subscribe(new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<String> strings) {
                Log.d("yaowang","strings");

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }
}
