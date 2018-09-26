package ro.absolute.cleanapparchitecturedataflowdependency.presentation.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.VisibleForTesting
import io.reactivex.disposables.CompositeDisposable
import ro.absolute.domain.model.Data
import ro.absolute.domain.usecase.GetData
import ro.absolute.domain.usecase.Params

/**
 * Created by Alina on 9/10/2018.
 */
class DataViewModel(private val getDataUseCase: GetData) : ViewModel() {

    val data = MutableLiveData<List<Data>>()
    private val disposables: CompositeDisposable = CompositeDisposable()

    @VisibleForTesting
    fun getData() {
        disposables.add(getDataUseCase.execute(Params())
                .subscribe(
                        { dataList: List<Data>? -> data.postValue(dataList) },
                        { System.out.println("GetDataError $it.message") }
                )
        )
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}