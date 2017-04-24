package pl.sda.bluj.marcin.weatherapp.base;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by MSI on 22.04.2017.
 */

public abstract class BasePresenter<V extends BaseView> {
    private V mView;
    protected Scheduler mSubscribeOn;
    protected Scheduler mObserveOn;
    private CompositeDisposable mContainer;

    public BasePresenter() {
    }

    public BasePresenter(Scheduler subscribeOn, Scheduler observeOn) {
        mSubscribeOn = subscribeOn;
        mObserveOn = observeOn;
        mContainer = new CompositeDisposable();
    }

    public void setView(V view) {
        mView = view;
    }

    public V getView() {
        return mView;
    }

    public void addDisposable(Disposable disposable) {
        mContainer.add(disposable);
    }

    public void clearContainer() {
        mContainer.clear();
    }
}
