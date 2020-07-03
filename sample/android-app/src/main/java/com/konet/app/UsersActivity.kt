package com.konet.app

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.konet.sample.library.domain.model.DUser
import com.konet.sample.library.presenter.UsersViewModel
import dev.icerock.moko.mvvm.getViewModel

class UsersActivity : AppCompatActivity() {

    private lateinit var usersTextView: TextView
    private lateinit var apiTextView: TextView
    private lateinit var progressVar: ProgressBar
    private val fakeUser = DUser(2, "test@konet.com", "Konet")

    val viewModel by lazy { getViewModel { UsersViewModel() } }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_users)
        usersTextView = findViewById(R.id.usersTextView)
        apiTextView = findViewById(R.id.apiLogTextView)
        progressVar = findViewById(R.id.progressBar)

        viewModel.users.ld().observe(this, Observer {
            response ->
            showProgress(false)
            usersTextView.text = this.usersToString(response)
        })
    }

    private fun showProgress(show: Boolean) {
        progressVar.visibility = if (show) View.VISIBLE else View.GONE
        usersTextView.visibility = if (show) View.GONE else View.VISIBLE
    }

    private fun usersToString(users: List<DUser>?): String =
        users?.joinToString(separator = "\n") { "${it.id}: ${it.first_name} (${it.email})" } ?: ""

    fun getUsersClick(view: View) {
        showProgress(true)
        viewModel.getAllUsers()
    }

    fun getUsersPageClick(view: View) {
        showProgress(true)
        viewModel.getUsersPage(2)
    }

    fun getUserClick(view: View) {
        showProgress(true)
        viewModel.getUser(2)
    }

    fun postUsersClick(view: View) {
        showProgress(true)
        viewModel.postUser(fakeUser)
    }

    fun putUsersClick(view: View) {
        showProgress(true)
        viewModel.putUser(fakeUser)
    }

    fun patchUsersClick(view: View) {
        showProgress(true)
        viewModel.patchUser(fakeUser)
    }

    fun deleteUsersClick(view: View) {
        showProgress(true)
        viewModel.deleteUser(2)
    }
}