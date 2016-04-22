#### android-Rx
Android 中使用RxAndroid与Rxjava简单的例子

说明：
=================
  此Demo只是简单的例子，因本人也是在学习和探索的阶段，如有错误的地方可及时联系我zouxianbincc@gmail.com
  create，range，just，from，interval，repeat，timer只有这种操作符
  
###引入包：

因为RxAndroid是基于RxJava的。所以引用RxAndroid同时，也引用RxJava

dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile 'io.reactivex:rxandroid:1.1.0'
    compile 'io.reactivex:rxjava:1.1.3'
}

###在使用RxAndroid或RxJava时，最基本的是要理解一个概念。Subscriber（观察者）与Observable（被观察者）。

Subscriber，观察者（订阅者）：订阅者，通俗理解，当一个人订阅了某一个东西，而这个订阅的人就是订阅者（观察者）
Observable，被观察者 ： “被”，被动词。谁被谁干嘛了，如，那个女孩，被你追求了。而女孩就是“被观察者”

#####Subscriber的onNext/onComplete/onError方法:

onNext就是发射处理好的数据给Subscriber;
onComplete用来告诉Subscriber所有的数据都已发射完毕；
onError是在发生错误的时候发射一个Throwable对象给Subscriber

###create 操作符:

 Create 操作符。发送一个数据,这里发送String
 
 public void Create() {

        //被观察者

        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                boolean randomNumber = new Random().nextBoolean();//随机产生一个boolean的值

                if (randomNumber && !subscriber.isUnsubscribed()) {//当为true时，与 订阅者没有取消订阅时，发送“成功”给订阅者。
                    subscriber.onNext("成功");

                    return;
                }


                subscriber.onError(new Throwable());//为false时。我们想它是错误的。


            }
        });

        //观察者（订阅者）
        Subscriber subscriber = new Subscriber() {
            @Override
            public void onCompleted() {
                mIRxView.onCompleted();



                Log.e("Create","onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                mIRxView.onError("产生false,调用onError方法");



                Log.e("Create","产生false,调用onError方法");
            }

            @Override
            public void onNext(Object object) {
                mIRxView.onNext("产生false,调用onNext方法" + object);



                Log.e("Create","产生true,调用onNext方法" + object);
            }
        };


        observable.subscribe(subscriber);//这个，才是真正把观察者与被观察者创建联系。


    }
    
    ###Range操作符:
    
    Range(n,m),Range操作符根据出入的初始值n和数目m发射一系列大于等于n的m个值
    
    public void Range() {

        //n=100,m=5;这里的效果时。会发送100,101,102,103,104这几个数字。从100开始。不断加1发送五次
        Observable observable = Observable.range(100, 5);

        Subscriber subscriber = new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object object) {



                Log.e("Range","renge=" + object);


                mIRxView.range(object);

            }
        };

        observable.subscribe(subscriber);


    }
    
    ###just 操作符:
    
    just方法。这里做的是传一个long类型的值。这个值随机生成。
    实验。同一个被观察者observable 被不同的订阅者订阅时，会不会生成不同的long 的值。
    很显然，值是相同的。并不会因观察者改变而改变
    jsut与defer的不同之处。defer当有Subscriber订阅时，都会新创建一个Observable对象
    Defer操作符只有当有Subscriber来订阅的时候才会创建一个新的Observable对象,也就是说每次订阅都会得到一个刚创建的最新的Observable对象，这可以确保Observable对象里的数据是最新的
    
     public void just() {

        final Observable<Long> observable = Observable.just(System.currentTimeMillis());

        observable.subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {

                mIRxView.just1(aLong);

                Log.e("just","just1=" + aLong);

            }
        });


        observable.subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                mIRxView.just2(aLong);
                Log.e("just","just2=" + aLong);
            }
        });
        observable.subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                mIRxView.just3(aLong);
                Log.e("just","just3=" + aLong);
            }
        });


    }
    
     ###From 操作符:
    
    From操作符用来将某个对象转化为Observable对象，并且依次将其内容发射出去。
    这个类似于just，但是just会将这个对象整个发射出去。
    比如说一个含有10个数字的数组，使用from就会发射10次，每次发射一个数字，而使用just会发射一次来将整个的数组发射出去

public void From() {

        Integer[] umber = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 11, 13, 14, 15};

        Observable observable = Observable.from(umber);

        observable.subscribe(new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object object) {
                Log.e("From","from=" + object);
            }
        });


    }
    
    ###Interval 操作符:
    
    Interval所创建的Observable对象会从0开始，每隔固定的时间发射一个数字。
    需要注意的是这个对象是运行在computation Scheduler,所以如果需要在view中显示结果，要在主线程中订阅。
    
    public void Interval(){

        Observable<Long> observable=Observable.interval(2, TimeUnit.SECONDS);

        //interva operates by default on the computation Scheduler,so observe on main Thread
        observable.observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                Log.e("Interval","Interval=" + aLong);
            }
        });

    }
###Repeat 操作符:

Repeat会将一个Observable对象重复发射，我们可以指定其发射的次数
下面是发送一个字符Ｓ，发送5次

  public void Repeat(){

        Observable<String> observable=Observable.just("S").repeat(5);

        observable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e("Repeat","Repeat=" + s);
            }
        });



    }
    
    ###Timer操作符:
    
    Timer会在指定时间后发射一个数字0，注意其也是运行在computation Scheduler
    
     public void Timer(){


        Observable<Long> observable=Observable.timer(2,TimeUnit.SECONDS);
        //timer by default operates on the computation Scheduler
        observable.observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                Log.e("Timer","Timer=" + aLong);
            }
        });

    }
##不断学习中。后续持续更新
