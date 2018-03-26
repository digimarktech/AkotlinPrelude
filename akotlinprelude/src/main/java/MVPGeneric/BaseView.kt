package MVPGeneric

/**
 * Created by lamour on 3/26/18.
 */
interface BaseView<T> {
    fun setPresenter(presenter: T)
}