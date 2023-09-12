package com.example.demoandroid

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.demoandroid.db.DbHelper
import com.example.demoandroid.db.daos.BookDao
import com.example.demoandroid.db.entities.Book
import kotlinx.coroutines.launch

class MainViewModel(private val dao : BookDao) : ViewModel() {
    private val _books : MutableLiveData<List<Book>> = MutableLiveData(mutableListOf())
    val books : LiveData<List<Book>>
        get() = _books

    init {
        viewModelScope.launch {
            val books = dao.findAll()
            _books.value = books
        }
    }

    fun addBook(book : Book) {
        viewModelScope.launch {
            val id = dao.create(book)
            book.id = id

            val newValue = _books.value!!.toMutableList()
            newValue.add(book)
            _books.value = newValue
        }
    }
}

class MainViewModelFactory(val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(DbHelper.instance(context).bookDao()) as T
    }
}