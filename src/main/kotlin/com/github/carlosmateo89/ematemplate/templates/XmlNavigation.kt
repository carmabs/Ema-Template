package com.github.carlosmateo89.ematemplate.templates

fun addXmlNavigation(
    featureName: String,
    packageFragmentName: String,
    hasFragment: Boolean
): String {
    val fragmentCode = if (hasFragment) {
        """
    <fragment
        android:id="@+id/${featureName.lowercase()}Fragment"
        android:name="$packageFragmentName.${featureName}Fragment"
        android:label="${featureName}Fragment"
        tools:layout="@layout/${featureName.lowercase()}_fragment"/>
"""
    } else
        ""
    return """<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">
    $fragmentCode
</navigation>
"""
}