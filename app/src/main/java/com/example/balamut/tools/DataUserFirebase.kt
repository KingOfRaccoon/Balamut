package com.example.balamut.tools

import android.util.Log
import com.example.balamut.Group
import com.example.balamut.users.Student
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.GsonBuilder

class DataUserFirebase: DataUserApi{

    private val gsonConverter = GsonBuilder().create()
    private val fireStore = FirebaseFirestore.getInstance()

    override fun addStudent(student: Student, group: Group) {
        fireStore.collection(groupTag)
            .document()
            .collection(studentTag)
            .add(student)
    }

    override fun updateStudent(student: Student) {
    }

    override fun deleteStudent(student: Student) {
        fireStore.collection(studentTag)
                .document(student.number)
                .delete()
    }

    override fun readAllStudent(): MutableList<Student> {
        val mutableListStudents = mutableListOf<Student>()
        fireStore.collection(studentTag)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    task.result?.documents?.forEach {
                        mutableListStudents.add(
                            gsonConverter.fromJson(it.data.toString(), Student::class.java)
                        )
                    }
                }
                else
                    Log.d("Test", task.exception?.message.toString())
            }
        return mutableListStudents
    }

    companion object{
        const val studentTag = "students"
        const val groupTag = "groups"
    }
}