import com.sample.cats.domain.GetCatBreedsUseCase
import com.sample.cats.domain.Repository
import com.sample.cats.domain.exceptions.IErrorHandler
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetCatBreedsUseCaseTest {

    private lateinit var getCatBreedsUseCase: GetCatBreedsUseCase
    private val repository: Repository = mockk()
    private val errorHandler: IErrorHandler = mockk()

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        getCatBreedsUseCase = GetCatBreedsUseCase(repository, errorHandler)
    }

    @After
    fun tearDown() {
        clearAllMocks()

        Dispatchers.resetMain()
    }

    @Test
    fun `should handle errors gracefully`() = runTest {
        val exception = Exception("Network error")
        coEvery { repository.getCatsBreeds() } throws exception

        val result = runCatching { getCatBreedsUseCase(Unit) }

        coVerify { repository.getCatsBreeds() }
        assert(result.isFailure)
    }
}