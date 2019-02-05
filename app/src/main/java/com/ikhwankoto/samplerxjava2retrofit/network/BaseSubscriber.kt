package com.ikhwankoto.samplerxjava2retrofit.network

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.ArrayList

class BaseSubscriber {

    private var compositeDisposable = CompositeDisposable()

    fun addDispos(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun finishDispos() {
        compositeDisposable.clear()
    }

}