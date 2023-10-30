package com.example.chunga_cash_app

import android.database.DatabaseErrorHandler
import com.example.chunga_cash_app.DatabaseHandler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class AddActivityTest {

    @Mock
    private lateinit var mockDatabaseHandler: DatabaseHandler

    private lateinit var addActivity: AddActivity

    @Before
    fun setup() {
        // Initialize Mocks
        MockitoAnnotations.initMocks(this)

        // Creating an instance of Add Activity with the mock DatabaseHandler
        addActivity = AddActivity()
        addActivity.databaseHandler = mockDatabaseHandler
    }

    @Test
    fun onSaveButtonClicked_withValidData_shouldSaveToDatabase() {
        // Arrange: Set Up Mck Behaviour
        Mockito.'when'(mockDatabaseHandler.saveStudent(Mockito.any(), Mockito.any()))
            .thenAnswer {invocation ->
                val callback = invocation.arguments[1] as (Boolean) -> Unit
                callback.invoke(true) // Simulate successful data save
            }

        // Act: Call the method to be tested
        addActivity.onSaveButtonClicked()

        // Assert: Verify that the appropriate methods were called on the mockDatabase
        Mockito.verify(mockDatabaseHandler).saveStudent(Mockito.any(), Mockito.any())
        // W e can add more assertions if we want if needed based on the behaviour after data is saved
    }

    @Test
    fun onSaveButtonClicked_withInvalidData_shouldNotSaveToDatabase() {
        // Arrange: Set up Mock Behaviour for invalid data (for example, empty fields)
        // Act: Call the method to be Tested
        addActivity.onSaveButtonClicked()

        // Assert: Verify that the appropriate methods were NOT called on the mockDatabaseHandler
        Mockito.verify(mockDatabaseHandler, Mockito.never()).saveStudent(Mockito.any(), Mockito.any())
        // We can add more assertations if needed based on the behaviour when data isn't saved
    }
}