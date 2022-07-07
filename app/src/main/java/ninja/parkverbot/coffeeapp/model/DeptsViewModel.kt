package ninja.parkverbot.coffeeapp.model

import androidx.lifecycle.MutableLiveData

class DeptsViewModel {

    private val _depts = MutableLiveData<List<DeptEntry>>()
    val depts get() = _depts


}