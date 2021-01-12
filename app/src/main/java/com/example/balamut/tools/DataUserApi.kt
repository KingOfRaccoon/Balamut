package com.example.balamut.tools

import com.example.balamut.Group
import com.example.balamut.users.Student
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

interface DataUserApi {
    fun addStudent(student: Student, group: Group)

    fun updateStudent(student: Student)

    fun deleteStudent(student: Student)

    fun readAllStudent():MutableList<Student>
}