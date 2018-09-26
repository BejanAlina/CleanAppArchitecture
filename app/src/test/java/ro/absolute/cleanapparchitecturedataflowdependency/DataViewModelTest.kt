package ro.absolute.cleanapparchitecturedataflowdependency

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import junit.framework.Assert.assertEquals
import org.junit.*
import ro.absolute.cleanapparchitecturedataflowdependency.presentation.viewmodel.DataViewModel
import ro.absolute.domain.model.Data
import ro.absolute.domain.repository.DataRepository
import ro.absolute.domain.usecase.GetDataUseCase
import ro.absolute.domain.usecase.Params

/**
 * Created by Alina on 9/10/2018.
 */
class DataViewModelTest {

    private val observerState = mock<Observer<List<Data>>>()
    private val dataRepository = mock<DataRepository>()
    private val testScheduler = TestScheduler()
    private val getDataUseCase = GetDataUseCase(dataRepository, testScheduler, testScheduler)
    private val viewModel = DataViewModel(getDataUseCase)

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun reset() {
        com.nhaarman.mockitokotlin2.reset(observerState)
    }

    @Test
    fun testVM_onError() {
        val npe = NullPointerException("NPE")
        whenever(dataRepository.getData()) doReturn Observable.just(listOf(Data("data")))
        whenever(getDataUseCase.execute(Params())) doReturn Observable.error(npe)

        viewModel.data.observeForever(observerState)
        viewModel.getData()

        testScheduler.triggerActions()
    }

    @Test
    fun testVM_onChanged() {
        val data: List<Data> = listOf(Data("data"))
        whenever(dataRepository.getData()) doReturn Observable.just(data)
        whenever(getDataUseCase.execute(Params())) doReturn Observable.just(data)

        viewModel.data.observeForever(observerState)
        viewModel.getData()

        testScheduler.triggerActions()

        val argumentCaptor = argumentCaptor<List<Data>>()
        argumentCaptor.run {
            verify(observerState, times(1)).onChanged(capture())
            assertEquals(allValues[0], data)
        }
    }
}