package cl.bootcamp.moviedbapp


import androidx.compose.ui.test.junit4.createAndroidComposeRule

import androidx.test.ext.junit.runners.AndroidJUnit4

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest

import org.junit.runner.RunWith


import org.junit.Rule

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeTestRule = createAndroidComposeRule<MainActivity>()


}