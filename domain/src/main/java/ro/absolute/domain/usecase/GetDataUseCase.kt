package ro.absolute.domain.usecase

import guru.stefma.cleancomponents.usecase.ObservableUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ro.absolute.domain.model.Data
import ro.absolute.domain.repository.DataRepository

/**
 * Created by Alina on 9/10/2018.
 * Used to get [List] of [Data] from data layer exposed through [ro.absolute.domain.repository.DataRepository]
 * subscribes & operates by default on [Schedulers.io], ends on [AndroidSchedulers.mainThread]
 * @return [Observable] [List] of [Data]
 */
class GetDataUseCase(
        private val dataRepository: DataRepository,
        override val executionScheduler: Scheduler = Schedulers.io(),
        override val postExecutionScheduler: Scheduler = AndroidSchedulers.mainThread()
) : ObservableUseCase<List<Data>, Params> {

    override fun buildUseCase(params: Params): Observable<List<Data>> = dataRepository.getData()
}
typealias GetData = ObservableUseCase<List<Data>, Params>

class Params