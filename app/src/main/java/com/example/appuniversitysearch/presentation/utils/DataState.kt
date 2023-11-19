package com.example.appuniversitysearch.presentation.utils

sealed class UIComponent {

    data class Toast(val message: String): UIComponent()

}