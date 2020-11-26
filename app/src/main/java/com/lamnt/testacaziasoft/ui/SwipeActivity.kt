package com.lamnt.testacaziasoft.ui

import android.content.DialogInterface
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import com.lamnt.testacaziasoft.R
import com.lamnt.testacaziasoft.models.User
import com.lamnt.testacaziasoft.repository.PrefRepository
import com.lamnt.testacaziasoft.utils.Utils
import com.lamnt.testacaziasoft.viewmodel.SwipeViewModel
import com.yuyakaido.android.cardstackview.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_swipe.*
import javax.inject.Inject

@AndroidEntryPoint
class SwipeActivity : BaseActivity<SwipeViewModel>(), UserCardAdapter.OnLogoutClick {
    private lateinit var manager: CardStackLayoutManager
    private lateinit var userAdapter: UserCardAdapter
    @Inject lateinit var prefRepository: PrefRepository
    override fun setLayoutId(): Int = R.layout.activity_swipe

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(SwipeViewModel::class.java)
    }

    override fun initView() {
        initStackView()

        if (Utils.isNetworkAvailable(this)) {
            viewModel.getListUser(10)
        } else {
            viewModel.usersLiveData.postValue(viewModel.getListUserOffline())
        }
    }

    override fun initData() {

    }

    override fun initObserver() {
        viewModel.usersLiveData.observe(this,
            Observer<ArrayList<User>> {
                userAdapter.setItems(it)
            })
    }

    private fun initStackView() {
        manager = CardStackLayoutManager(this, object : CardStackListener {
            override fun onCardDragging(
                direction: Direction,
                ratio: Float
            ) {
            }

            override fun onCardSwiped(direction: Direction) {
                if (direction == Direction.Right) {
                    if (Utils.isNetworkAvailable(this@SwipeActivity))
                        viewModel.addUserOffline(userAdapter.getItems()[manager.topPosition - 1])
                }

                if (direction == Direction.Top) {

                }
                if (direction == Direction.Left) {

                }
                if (direction == Direction.Bottom) {

                }

                if (manager.topPosition == userAdapter.itemCount) {
                    if (Utils.isNetworkAvailable(this@SwipeActivity)) {
                        viewModel.getListUser(10)
                    } else {
                        txtNoData.visibility = View.VISIBLE
                        card_stack_view.visibility = View.GONE
                    }
                }
            }

            override fun onCardRewound() {
            }

            override fun onCardCanceled() {
            }

            override fun onCardAppeared(view: View, position: Int) {

            }

            override fun onCardDisappeared(view: View, position: Int) {

            }
        })
        manager.setStackFrom(StackFrom.None)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.FREEDOM)
        manager.setCanScrollHorizontal(true)
        manager.setSwipeableMethod(SwipeableMethod.Manual)
        manager.setOverlayInterpolator(LinearInterpolator())
        userAdapter = UserCardAdapter(this)
        with(card_stack_view) {
            adapter = userAdapter
            layoutManager = manager
            itemAnimator = DefaultItemAnimator()
        }
    }

    override fun onLogout() {
        showPopup(getString(R.string.title_notify),
            getString(R.string.msg_logout),
            DialogInterface.OnClickListener {
                    dialog, which ->
                prefRepository.clear()
                dialog.dismiss()
                navigateTo(LoginActivity::class.java,null,true)
            })
    }

}