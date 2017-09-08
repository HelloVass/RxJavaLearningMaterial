package scut.carson_ho.rxjava_operators.establishUsage_Demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import scut.carson_ho.rxjava_operators.R;

/**
 * Created by Carson_Ho on 17/9/6.
 * 应用场景 & 对应操作符介绍
 */

public class establishUsage extends AppCompatActivity {

    private String TAG = "RxJava";
    Integer i = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Observable.just(1,2,4).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(@NonNull Observable<Object> objectObservable) throws Exception {
                // 返回新的被观察者 Observable
                // 此处有两种情况：
                // 1. 原始的Observable不重新发送事件：新的被观察者 Observable发送的事件 = Error事件
                // 2. 原始的Observable重新发送事件：新的被观察者 Observable发送的事件 = 数据事件
                return objectObservable.flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull Object throwable) throws Exception {

                        // 1. 若返回的Observable发送的事件 = Error事件，则原始的Observable不重新发送事件
                        // 该异常错误信息可在观察者中的onError（）中获得
                        return Observable.empty();

                        // 2. 若返回的Observable发送的事件 = 数据事件，则原始的Observable重新发送事件（若持续遇到错误，则持续重试）
//                         return Observable.just(1);
                    }
                });

            }
        })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }

                });


//        // 具体使用
//
//        Observable.just(1,2,4).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
//            @Override
//            public ObservableSource<?> apply(@NonNull Observable<Object> objectObservable) throws Exception {
//                return  Observable.just(1);
////                return objectObservable;
////                return Observable.empty().delay(3, TimeUnit.SECONDS);
//            }
//        })
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.d(TAG, "开始采用subscribe连接");
//                    }
//
//                    @Override
//                    public void onNext(Integer value) {
//                        Log.d(TAG, "接收到了事件" + value);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "对Error事件作出响应");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "对Complete事件作出响应");
//                    }
//
//                });


        /*
         * 数组遍历
         **/

//        // 1. 设置需要传入的数组
//        Integer[] items = { 0, 1, 2, 3, 4 };
//
//        // 2. 创建被观察者对象（Observable）时传入数组
//        // 在创建后就会将该数组转换成Observable & 发送该对象中的所有数据
//        Observable.fromArray(items)
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.d(TAG, "数组遍历");
//                    }
//
//                    @Override
//                    public void onNext(Integer value) {
//                        Log.d(TAG, "数组中的元素 = "+ value  );
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "对Error事件作出响应");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "遍历结束");
//                    }
//
//                });
//
//
//        /*
//         * 集合遍历
//         **/
//        // 1. 设置一个集合
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//
//        // 2. 通过fromIterable()将集合中的对象 / 数据发送出去
//        Observable.fromIterable(list)
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.d(TAG, "集合遍历");
//                    }
//
//                    @Override
//                    public void onNext(Integer value) {
//                        Log.d(TAG, "集合中的数据元素 = "+ value  );
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "对Error事件作出响应");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "遍历结束");
//                    }
//                });




        /*
         * 周期性操作
         **/
        // 该例子发送的事件序列特点：延迟2s后发送事件，每隔1秒产生1个数字（从0开始递增1，无限个） = 每隔1s进行1次操作
//        Observable.interval(2,1,TimeUnit.SECONDS)
//                .subscribe(new Observer<Long>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.d(TAG, "开始采用subscribe连接");
//                    }
//
//                    @Override
//                    public void onNext(Long value) {
//                        Log.d(TAG, "每隔1s进行1次操作" );
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "对Error事件作出响应");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "对Complete事件作出响应");
//                    }
//
//                });

                // 注：interval默认在computation调度器上执行
                // 也可自定义指定线程调度器（第3个参数）：interval(long,TimeUnit,Scheduler)



        /*
         * 定时操作
         **/

//        // 该例子 = 延迟2s后，进行日志输出操作
//        Observable.timer(2, TimeUnit.SECONDS)
//                .subscribe(new Observer<Long>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        Log.d(TAG, "开始采用subscribe连接");
//                    }
//
//                    @Override
//                    public void onNext(Long value) {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "对Error事件作出响应");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "在2s后进行了该操作");
//                    }
//
//                });

        // 注：timer操作符默认运行在一个新线程上
        // 也可自定义线程调度器（第3个参数）：timer(long,TimeUnit,Scheduler)


        /*
         * 完整创建被观察者
         **/

        // 步骤1：通过create（）创建完整的被观察者对象
//        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
//            // 2. 在复写的subscribe（）里定义需要发送的事件
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                emitter.onNext(1);
//                emitter.onNext(2);
//                emitter.onNext(3);
//                emitter.onComplete();
//            }
//        }); // 至此，一个完整的被观察者对象创建完毕。
//
//        // 步骤2：创建观察者 Observer 并 定义响应事件行为
//        Observer<Integer> observer = new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.d(TAG, "开始采用subscribe连接");
//            }
//
//            @Override
//            public void onNext(Integer value) {
//                Log.d(TAG, "接收到了事件 = "+ value   );
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(TAG, "对Error事件作出响应");
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG, "对Complete事件作出响应");
//            }
//        };
//
//
//        // 步骤3：通过订阅（subscribe）连接观察者和被观察者
//        observable.subscribe(observer);

    }
}