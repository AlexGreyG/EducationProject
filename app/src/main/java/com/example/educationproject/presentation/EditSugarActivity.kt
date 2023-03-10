package com.example.educationproject.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.educationproject.R
import com.example.educationproject.domain.SugarItem

class EditSugarActivity : AppCompatActivity() {
    private lateinit var etName: EditText
    private lateinit var etValue: EditText
    private lateinit var etSave: Button
    private val viewModel by lazy {
        ViewModelProvider(this)[EditSugarViewModel::class.java]
    }
    private var screenMode = M_ADD
    private var sugarId = SugarItem.UNDEFINED_ID
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_sugar)
        parseIntent()
        initFields()
        launchMode()
        launchObservers()
    }

    private fun launchObservers() {
        viewModel.errorInputName.observe(this) {
            if (it) {
                //show error
            }
        }
        viewModel.errorInputValue.observe(this) {
            if (it) {
                //show error
            }
        }
        viewModel.canExit.observe(this) {
            finish()
        }
    }

    private fun launchMode() {
        when (screenMode) {
            M_ADD -> launchAddMode()
            M_EDIT -> launchEditMode()
        }
    }

    private fun launchAddMode() {
        etSave.setOnClickListener {
            viewModel.addSugarItem(etName.text?.toString(), etValue.text?.toString())
        }
    }

    private fun launchEditMode() {
        viewModel.getSugarItem(sugarId)
        viewModel.sugarItem.observe(this) {
            etName.setText(it.date)
            etValue.setText(it.sugar.toString())
        }

        etSave.setOnClickListener {
            viewModel.editSugarItem(etName.text?.toString(), etValue.text?.toString())
        }
    }

    private fun parseIntent() {
        if (!intent.hasExtra(EXSTRA_SCREEN_MODE)) {
            throw RuntimeException("no screen mode")
        }

        screenMode = intent.getStringExtra(EXSTRA_SCREEN_MODE) ?: M_ADD
        if (screenMode == M_EDIT && !intent.hasExtra(EXSTRA_ID)) {
            throw RuntimeException("no id")
        }
        sugarId = intent.getIntExtra(EXSTRA_ID, SugarItem.UNDEFINED_ID)
    }

    private fun initFields() {
        etName = findViewById<EditText>(R.id.aesName)
        etValue = findViewById<EditText>(R.id.aesValue)
        etSave = findViewById<Button>(R.id.aesSave)
    }


    companion object {
        private const val EXSTRA_SCREEN_MODE = "extra_mode"
        private const val EXSTRA_ID = "extra_id"
        private const val M_EDIT = "mode_edit"
        private const val M_ADD = "mode_add"

        fun startEdit(context: Context, sugarId: Int): Intent {
            return Intent(context, EditSugarActivity::class.java).apply {
                putExtra(EXSTRA_SCREEN_MODE, M_EDIT)
                putExtra(EXSTRA_ID, sugarId)
            }
        }

        fun starеTest(context: Context, sugarItem: SugarItem): Intent {
            return Intent(context, EditSugarActivity::class.java).apply {
//                putExtra(EXSTRA_SCREEN_MODE, sugarItem)
//                putExtra(EXSTRA_ID, sugarId)
                //  putParcelableArrayListExtra()
            }
        }

        fun startAdd(context: Context): Intent = Intent(context, EditSugarActivity::class.java).apply {
            putExtra(EXSTRA_SCREEN_MODE, M_ADD)
        }
    }
}