package ro.absolute.domain

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import junit.framework.Assert.assertTrue
import org.junit.*
import ro.absolute.domain.repository.DataRepository
import ro.absolute.domain.usecase.GetDataUseCase
import ro.absolute.domain.usecase.Params

/**
 * Created by Alina on 9/10/2018.
 */

public class GetDataUseCaseTest {

    private val dataRepository = mock<DataRepository>()
    private val testScheduler = TestScheduler()
    private val getDataUseCase = GetDataUseCase(dataRepository, testScheduler, testScheduler)

    @Test
    fun onError_doLogging() {
        val npe = NullPointerException("NPE")
        whenever(getDataUseCase.buildUseCase(Params())) doReturn Observable.error { npe }

        val testObserver = getDataUseCase.buildUseCase(Params()).test()

        testScheduler.triggerActions()

        testObserver.assertError { true }
        testObserver.onError(npe)
        assertTrue(testObserver.errors()[0].message.equals("NPE"))
        testObserver.assertNotComplete()
    }
}