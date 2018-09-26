package ro.absolute.data

import io.reactivex.Observable
import ro.absolute.domain.model.Data
import ro.absolute.domain.repository.DataRepository

/**
 * Created by Alina on 9/10/2018.
 * Uses repository pattern to expose [DataRepository]
 */
class DataRepositoryImpl : DataRepository {

    override fun getData(): Observable<List<Data>> {
        return Observable.just(listOf(Data("some data")))
    }
}