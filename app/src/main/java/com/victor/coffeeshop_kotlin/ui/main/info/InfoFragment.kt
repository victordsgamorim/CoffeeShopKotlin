package com.victor.coffeeshop_kotlin.ui.main.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.victor.coffeeshop_kotlin.R
import com.victor.coffeeshop_kotlin.model.domain.Coffee
import com.victor.coffeeshop_kotlin.persistence.dao.CoffeeDao
import com.victor.coffeeshop_kotlin.ui.BaseFragment
import com.victor.coffeeshop_kotlin.ui.main.UIComponent
import kotlinx.android.synthetic.main.fragment_info.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InfoFragment : BaseFragment() {

    private val arguments by navArgs<InfoFragmentArgs>()
    private val coffeeId by lazy { arguments.coffeeId }

    @Inject
    lateinit var coffeeDao: CoffeeDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setUIComponent = UIComponent(false, true)



        /**example*/
        CoroutineScope(IO).launch {
            val c = loadInfo()

            withContext(Main) {
                coffee_name.text = c.name
            }
        }

    }

    suspend fun loadInfo(): Coffee {
        return coffeeDao.getCoffee(coffeeId)
    }

}