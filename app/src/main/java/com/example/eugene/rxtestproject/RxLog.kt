@file:Suppress("NOTHING_TO_INLINE")

import android.util.Log
import io.reactivex.*

inline fun <reified T> printEvent(tag: String, success: T?, error: Throwable?) =
        when {
            success == null && error == null -> Log.d(tag, "[RX] Complete") /* Only with Maybe */
            success != null -> Log.d(tag, "[RX] Success $success")
            error != null -> Log.d(tag, "[RX] Error $error")
            else -> -1 /* Cannot happen*/
        }

inline fun printEvent(tag: String, error: Throwable?) =
        when {
            error != null -> Log.d(tag, "[RX] Error $error")
            else -> Log.d(tag, "[RX] Complete")
        }

/**
 * Example usage of [log]:
Single.timer(1, TimeUnit.SECONDS)
.log()
.subscribe({ }, { })
 */

inline fun tag() =
        Thread.currentThread().stackTrace
                .first { it.fileName.endsWith(".kt") }
                .let { stack -> "${stack.fileName.removeSuffix(".kt")}::${stack.methodName}:${stack.lineNumber}" }

inline fun <reified T> Single<T>.log(): Single<T> {
    val tag = tag()
    return doOnEvent { success, error -> printEvent(tag, success, error) }
            .doOnSubscribe { Log.d(tag, "[RX] Subscribe" + "Thread = " + Thread.currentThread().name) }
            .doOnDispose { Log.d(tag, "[RX] Dispose" + "Thread = " + Thread.currentThread().name) }
}

inline fun <reified T> Maybe<T>.log(): Maybe<T> {
    val tag = tag()
    return doOnEvent { success, error -> printEvent(tag, success, error) }
            .doOnSubscribe { Log.d(tag, "[RX] Subscribe" + "Thread = " + Thread.currentThread().name) }
            .doOnDispose { Log.d(tag, "[RX] Dispose" + "Thread = " + Thread.currentThread().name) }
}

inline fun Completable.log(): Completable {
    val tag = tag()
    return doOnEvent { printEvent(tag, it) }
            .doOnSubscribe { Log.d(tag, "[RX] Subscribe" + "Thread = " + Thread.currentThread().name) }
            .doOnDispose { Log.d(tag, "[RX] Dispose" + "Thread = " + Thread.currentThread().name) }
}

inline fun <reified T> Observable<T>.log(): Observable<T> {
    val line = tag()
    return doOnEach { Log.d(line, "[RX] Thread =  ${Thread.currentThread().name} Each $it") }
            .doOnSubscribe { Log.d(line, "[RX] Subscribe" + "Thread = " + Thread.currentThread().name) }
            .doOnDispose { Log.d(line, "[RX] Dispose" + "Thread = " + Thread.currentThread().name) }
}

inline fun <reified T> Flowable<T>.log(): Flowable<T> {
    val line = tag()
    return doOnEach { Log.d(line, "[RX] Thread =  ${Thread.currentThread().name} Each $it") }
            .doOnSubscribe { Log.d(line, "[RX] Subscribe" + "Thread = " + Thread.currentThread().name) }
            .doOnCancel { Log.d(line, "[RX] Cancel" + "Thread = " + Thread.currentThread().name) }
}