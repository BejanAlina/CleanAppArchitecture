package ro.absolute.data

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.*
import ro.absolute.domain.model.Data
import ro.absolute.domain.repository.DataRepository

/**
 * Created by Alina on 9/10/2018.
 */
class DataRepositoryImplTest {

    private val dataRepositoryImpl = mock<DataRepository>()

    @Test
    fun emptyData() {
        val data = emptyList<Data>()

        whenever(dataRepositoryImpl.getData()) doReturn Observable.just(data)

        val test = dataRepositoryImpl.getData().test()

        test.assertValue(data)
        test.assertNoErrors()
        test.assertComplete()
    }
}