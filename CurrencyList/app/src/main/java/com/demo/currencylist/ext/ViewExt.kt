package com.demo.currencylist.ext

import android.view.View
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.view.detaches
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

private const val THROTTLE_MILLISECONDS = 500L

fun View.throttleClicks(detach: Boolean = false): Observable<Unit> {
    var observable = this.clicks().throttleFirst(THROTTLE_MILLISECONDS, TimeUnit.MILLISECONDS)
    if (detach) {
        observable = observable.takeUntil(detaches())
    }

    return observable
}