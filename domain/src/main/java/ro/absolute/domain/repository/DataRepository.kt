package ro.absolute.domain.repository

import io.reactivex.Observable
import ro.absolute.domain.model.Data

/**
 * Created by Alina on 9/10/2018.
 * Implemented in data layer
 */
interface DataRepository {

    fun getData(): Observable<List<Data>>
}