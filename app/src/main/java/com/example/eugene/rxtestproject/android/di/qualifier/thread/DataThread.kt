package  com.example.eugene.rxtestproject.android.di.qualifier.thread

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Qualifier


@Qualifier
@Retention(RetentionPolicy.RUNTIME)
annotation class DataThread {

}