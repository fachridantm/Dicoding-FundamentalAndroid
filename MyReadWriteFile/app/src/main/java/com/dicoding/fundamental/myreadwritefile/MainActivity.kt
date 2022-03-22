package com.dicoding.fundamental.myreadwritefile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.fundamental.myreadwritefile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.buttonNew.setOnClickListener(this)
        mainBinding.buttonOpen.setOnClickListener(this)
        mainBinding.buttonSave.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_new -> newFile()
            R.id.button_open -> showList()
            R.id.button_save -> saveFile()
        }
    }

    private fun saveFile() {
        when {
            mainBinding.editTitle.text.toString().isEmpty() -> Toast.makeText(
                this,
                "Title harus diisi terlebih dahulu",
                Toast.LENGTH_SHORT
            ).show()
            mainBinding.editFile.text.toString().isEmpty() -> Toast.makeText(
                this,
                "Kontent harus diisi terlebih dahulu",
                Toast.LENGTH_SHORT
            ).show()
            else -> {
                val title = mainBinding.editTitle.text.toString()
                val text = mainBinding.editFile.text.toString()
                val fileModel = FileModel()
                fileModel.filename = title
                fileModel.data = text
                FileHelper.writeToFile(fileModel, this)
                Toast.makeText(
                    this,
                    "Saving ${fileModel.filename} file",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun showList() {
        val items = fileList()
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Pilih file yang diinginkan")
        builder.setItems(items) { _, item -> loadData(items[item].toString()) }
        val alert = builder.create()
        alert.show()
    }

    private fun loadData(title: String) {
        val fileModel = FileHelper.readFromFile(this, title)
        mainBinding.editTitle.setText(fileModel.filename)
        mainBinding.editFile.setText(fileModel.data)
        Toast.makeText(
            this,
            "Loading ${fileModel.filename} data",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun newFile() {
        mainBinding.editTitle.setText("")
        mainBinding.editFile.setText("")
        Toast.makeText (
            this,
            "Clearing file",
            Toast.LENGTH_SHORT
        ).show()
    }
}