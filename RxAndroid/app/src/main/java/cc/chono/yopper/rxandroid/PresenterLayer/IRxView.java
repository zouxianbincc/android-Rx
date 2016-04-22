package cc.chono.yopper.rxandroid.PresenterLayer;

/**
 * Created by cc on 16/4/21.
 */
public interface IRxView {

    void onCompleted();

    void onError(Object object);

    void onNext(Object object);

    void range(Object object);

    void just1(Object object);
    void just2(Object object);
    void just3(Object object);

}
