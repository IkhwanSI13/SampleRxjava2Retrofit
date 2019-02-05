package com.ikhwankoto.samplerxjava2retrofit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.ikhwankoto.samplerxjava2retrofit.network.HewanDao
import com.ikhwankoto.samplerxjava2retrofit.network.HewanRepo
import io.reactivex.Flowable
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnObservable.setOnClickListener {
            Log.e("Ikhwan", "hit api with observable")
            runHitApiObservable()
        }
        btnSingle.setOnClickListener {
            Log.e("Ikhwan", "hit api with single")
            runHitApiSingle()
        }
    }

    fun simple() {
        Flowable.just("aa").subscribe(object : Consumer<String> {
            override fun accept(t: String?) {
                Log.e("Ikwhan", t)
            }
        }).dispose()
    }

    fun runHitApiObservable() {
        HewanRepo.getHewanWithObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<HewanDao> {
                    override fun onComplete() {
                        Log.e("Ikhwan", "Observable onComplete")
                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.e("Ikhwan", "Observable onSubscribe")
                    }

                    override fun onNext(t: HewanDao) {
                        Log.e("Ikhwan", "Observable onNext")
                    }

                    override fun onError(e: Throwable) {
                        Log.e("Ikhwan", "Observable onError " + e.message)
                    }
                })
    }

    fun runHitApiSingle() {
        HewanRepo.getHewanWithSingle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<HewanDao> {
                    override fun onSuccess(t: HewanDao) {
                        Log.e("Ikhwan", "Single onsucces")
                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.e("Ikhwan", "Single onSubscribe")
                        compositeDisposable.add(d)
                    }

                    override fun onError(e: Throwable) {
                        Log.e("Ikhwan", "Single onError " + e.message)
                    }
                })
    }

}
