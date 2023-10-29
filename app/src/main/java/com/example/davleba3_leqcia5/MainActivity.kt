package com.example.davleba3_leqcia5
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.example.davleba3_leqcia5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var person:Person

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btnSave.setOnClickListener(){
            if(validatePersonInfo()){
                hideOldOpenNew()
            }
        }
        binding.btnClear.setOnLongClickListener(){
            clear()
            true
        }
        binding.btnAgain.setOnClickListener(){
            openOldHideNew()
        }
    }

    private fun validatePersonInfo() :Boolean{
        var email:String = binding.edt1.text.toString()
        var userName:String = binding.edt2.text.toString()
        var firstName:String = binding.edt3.text.toString()
        var lastName:String = binding.edt4.text.toString()
        var age:String = binding.edt5.text.toString()
        var ageInIntegers:Int

        // since editext can't contain null no need to validate null cases here

        // validating that all fields (editexts) are filled
        if(email.isEmpty()||userName.isEmpty()||firstName.isEmpty()||lastName.isEmpty()||age.isEmpty()){
            Toast.makeText(this, "Plese fill all info field", Toast.LENGTH_SHORT).show()
            return false
        }

        //validating that username has at least 10 character
        if(userName.length <10){
            Toast.makeText(this, "Username must contain at least 10 characters", Toast.LENGTH_SHORT).show()
            return false
        }

        //validating email
        if(!validateEmail(email)){
            Toast.makeText(this, "Email isn't valid", Toast.LENGTH_SHORT).show()
            return false
        }

        //validating the age
        try{
            ageInIntegers = age.toInt()
            if(ageInIntegers < 0){
                Toast.makeText(this, "Age can't be negaive number", Toast.LENGTH_SHORT).show()
                return false
            }
        }catch (e:NumberFormatException){
            Toast.makeText(this, "Age must be Integer type", Toast.LENGTH_SHORT).show()
            return false
        }

        //validation is compleate, we save create Person object with given properties and save its reference in person val
        person = Person(email,userName,firstName,lastName,ageInIntegers)
        return true
    }

    private fun clear(){
        binding.edt1.text?.clear()
        binding.edt2.text?.clear()
        binding.edt3.text?.clear()
        binding.edt4.text?.clear()
        binding.edt5.text?.clear()
    }

    private fun validateEmail(email:String):Boolean{
        // 1) must contain @
        // 2) must have at least one character to the left and right of the @
        // 3) must contain . in the domain part (on the rigth side of the @ )

        // or just use builtin paterns to validate

        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun hideOldOpenNew(){
        binding.edt1.visibility = View.GONE
        binding.edt2.visibility = View.GONE
        binding.edt3.visibility = View.GONE
        binding.edt4.visibility = View.GONE
        binding.edt5.visibility = View.GONE
        binding.btnSave.visibility = View.GONE
        binding.btnClear.visibility = View.GONE

        binding.txt2.visibility = View.VISIBLE
        binding.btnAgain.visibility = View.VISIBLE

        binding.txt2.text = person.toString() // Person is a data classs, so it has toString
    }

    private fun openOldHideNew(){
        binding.edt1.visibility = View.VISIBLE
        binding.edt2.visibility = View.VISIBLE
        binding.edt3.visibility = View.VISIBLE
        binding.edt4.visibility = View.VISIBLE
        binding.edt5.visibility = View.VISIBLE
        binding.btnSave.visibility = View.VISIBLE
        binding.btnClear.visibility = View.VISIBLE

        binding.txt2.visibility = View.GONE
        binding.btnAgain.visibility = View.GONE

        binding.edt1.text?.clear()
        binding.edt2.text?.clear()
        binding.edt3.text?.clear()
        binding.edt4.text?.clear()
        binding.edt5.text?.clear()
    }
}