package com.github.carlosmateo89.ematemplate.templates

import org.jetbrains.kotlin.lombok.utils.capitalize

fun addActivityLayout(featureName: String, hasToolbar: Boolean): String {
    val toolbarCode = if(hasToolbar)
        """
    <com.google.android.material.appbar.AppBarLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        android:id="@+id/abl${featureName.capitalize()}">
    
        <androidx.constraintlayout.widget.ConstraintLayout
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize">
    
            <androidx.appcompat.widget.Toolbar
                android:id="@+id/tb${featureName.capitalize()}"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:contentInsetStart="0dp"
                android:contentInsetLeft="0dp"
                android:contentInsetEnd="0dp"
                android:contentInsetRight="0dp"
                android:elevation="0dp"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_scrollFlags="enterAlwaysCollapsed">
    
            </androidx.appcompat.widget.Toolbar>
    
        </androidx.constraintlayout.widget.ConstraintLayout>

    </com.google.android.material.appbar.AppBarLayout>
    """
    else
        ""

    val fragmentCode = if(hasToolbar){
        """<androidx.fragment.app.FragmentContainerView
            android:id="@+id/navHostFragment"
            android:name="androidx.navigation.fragment.NavHostFragment"
            android:layout_width="0dp"
            android:layout_height="0dp"
            app:defaultNavHost="true"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@id/abl${featureName.capitalize()}" />"""
    }
    else{
        """<androidx.fragment.app.FragmentContainerView
            android:id="@+id/navHostFragment"
            android:name="androidx.navigation.fragment.NavHostFragment"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:defaultNavHost="true" />"""
    }

    return """<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    $toolbarCode
    $fragmentCode
    
</androidx.constraintlayout.widget.ConstraintLayout>
    """

}