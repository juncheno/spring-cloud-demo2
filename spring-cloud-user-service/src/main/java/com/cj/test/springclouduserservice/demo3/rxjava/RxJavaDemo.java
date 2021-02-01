package com.cj.test.springclouduserservice.demo3.rxjava;


import rx.Observable;
import rx.Observer;
import rx.functions.Action0;
import rx.functions.Func0;

public class RxJavaDemo {


    public static void main(String[] args) {


        final String[] datas=new String[]{"登陆","进入课堂","上课","答疑"};

        final Action0 onCompleted=new Action0() {
            @Override
            public void call() {
                System.out.println("on completed");
            }
        };

        Observable<String> observable= Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                Observable observable=Observable.from(datas);

                return observable.doOnCompleted(onCompleted);
            }
        });

        Observer observer=new Observer() {
            @Override
            public void onCompleted() {
                System.out.println("Observer: onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Observer: onError");
            }

            @Override
            public void onNext(Object o) {
                System.out.println("Observer: onNext"+o);
            }
        };

        observable.subscribe(observer);
    }

}
