package utils

import androidx.annotation.VisibleForTesting
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import java.lang.reflect.Modifier

@VisibleForTesting(otherwise = Modifier.PRIVATE)
@ExperimentalCoroutinesApi
open class CoroutineTestRule(
    open val testDispatcher: TestDispatcher = StandardTestDispatcher()
) : TestWatcher() {

    val scope: CoroutineScope
        get() = CoroutineScope(context = testDispatcher)

    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}
