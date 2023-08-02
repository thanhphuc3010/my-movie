package com.phucpt.mymovie.codebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * Created by Phucpt on 10/07/2023 at 22:03
 */

interface BaseItem<VB : ViewBinding> {
    @get:LayoutRes
    val layoutId: Int

    val uniqueId: Any

    fun buildView(parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
    }

    @Suppress("UNCHECKED_CAST")
    fun initializeViewBinding(parent: ViewGroup): VB {
        val itemView = buildView(parent)
        val actualTypeOfThis = getSuperclassParameterizedType(javaClass)
        val viewBindingClass = actualTypeOfThis.actualTypeArguments[0] as Class<ViewBinding>
        val bind = viewBindingClass.getDeclaredMethod("bind", View::class.java)
            ?: error("The binder class ${javaClass.canonicalName} should have a method inflate")
        return bind.invoke(null, itemView) as VB
    }

    private fun getSuperclassParameterizedType(klass: Class<*>): ParameterizedType =
        klass.genericInterfaces[0] as ParameterizedType

    override fun equals(other: Any?): Boolean
}