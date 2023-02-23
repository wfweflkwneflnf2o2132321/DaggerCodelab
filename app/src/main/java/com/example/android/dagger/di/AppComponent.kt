package com.example.android.dagger.di

import android.content.Context
import com.example.android.dagger.login.LoginComponent
import com.example.android.dagger.registration.RegistrationComponent
import com.example.android.dagger.user.UserManager
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

// Definition of a Dagger component that adds info from the StorageModule to the graph
@Singleton
@Component(modules = [StorageModule::class, AppSubcomponents::class])
interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        // Use @BindsInstance for objects that are constructed outside of the graph
        // (e.g. instances of Context).
        fun create(@BindsInstance context: Context): AppComponent
    }
    // Classes that can be injected by this Component
    //fun inject(activity: RegistrationActivity)
    //fun inject(fragment: EnterDetailsFragment)
    //fun inject(fragment: TermsAndConditionsFragment)

    // Expose RegistrationComponent factory from the graph
    // Types that can be retrieved from the graph
    fun registrationComponent(): RegistrationComponent.Factory
    fun loginComponent(): LoginComponent.Factory

    // 2) Expose UserManager so that MainActivity and SettingsActivity
    // can access a particular instance of UserComponent
    fun userManager(): UserManager

    //fun inject(activity: MainActivity)
    //fun inject(activity: SettingsActivity)
}