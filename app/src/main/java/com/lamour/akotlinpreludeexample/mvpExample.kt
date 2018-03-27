package com.lamour.akotlinpreludeexample

import com.lamour.akotlinprelude.MVPGeneric.BasePresenter
import com.lamour.akotlinprelude.MVPGeneric.BaseView
import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Button

/**
 * Created by lamour on 3/26/18.
 */


/* Basic MVP Example*/

/* Step 1: Create Contract */
interface LoginContract {
    interface View: BaseView<Presenter> {
        fun hasAuthorization()
        fun deniedFor(reason:String)
    }

    interface Presenter: BasePresenter {
        fun fillWith(username: String, password: String)
    }

}

class AuthService {
    private var activity: Activity? = null

    fun setActivity(activity: Activity?) {
        this.activity = activity // we might need activity to call certain service i.e Firebase
    }

    fun signInWith(username: String, password: String, completion: (Error?) -> Unit) {
            completion(null)
    }
}

class LoginModel(val authService: AuthService) {

    fun setCurrentActivity(activity: Activity) {
        authService.setActivity(activity)
    }

    fun loginInBackgroundWith(username: String, password: String, callback:(Error?)-> Unit) {
        authService.signInWith(username,password) { err ->
            callback.invoke(err)
        }
    }
}

/* Step 2: Create Presenter (aka wrapper around Business Logic/ Data ) */

class LoginPresenter(val activity: Activity, val model: LoginModel?, val view: LoginContract.View?): LoginContract.Presenter {
    init {

        this.model?.setCurrentActivity(this.activity)
        this.view?.setPresenter(this)
    }

    override fun fillWith(username: String, password: String) {
        this.model?.loginInBackgroundWith(username, password) { err ->
            if(err != null) {
                this.view?.deniedFor(err.toString())
            } else {
                this.view?.hasAuthorization()
            }
        }
    }

    override fun start() {
        // would start any ressources
    }

}

/* Step 3: Create View that is your Fragment */

final class LoginFragment: Fragment(), LoginContract.View {

    private var loginButton: Button? = null
    private var presenter:LoginContract.Presenter? = null

    // add other lifecycle method ...

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // assuming that the user provides info via textEdits
        // once they pressed the button we'll send the info via the presenter
        // on the presenter would return its response throughout the delegates
        // hasAuthorization() and deniedFor(:String)
        loginButton?.setOnClickListener {
            this.presenter?.fillWith("admin", "asdkjlajsd9291")
        }
    }

    override fun setPresenter(presenter: LoginContract.Presenter) {
        this.presenter = presenter
    }

    override fun hasAuthorization() {
       // do whatever dismiss this fragment
    }

    override fun deniedFor(reason: String) {

    }

}

fun composeLogin(activity: Activity): Fragment {
    val service = AuthService()
    val model = LoginModel(service)
    // create view
    val loginFragment = LoginFragment()
    // create presenter
    val loginPresenter = LoginPresenter(activity, model, loginFragment)

    // if the presenter has any internal resources, you might need to call start()
    loginPresenter.start()

    // set presenter into view
    loginFragment.setPresenter(loginPresenter)

    return loginFragment
}