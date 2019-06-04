/*
 * Copyright 2014-2019 JetBrains s.r.o and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

package io.ktor.client.call

import java.lang.reflect.*
import kotlin.reflect.*

actual typealias Type = java.lang.reflect.Type

@PublishedApi
internal open class TypeBase<T>

actual inline fun <reified T> typeInfo(): TypeInfo {
    val base = object : TypeBase<T>() {}
    val superType = base::class.java.genericSuperclass!!

    val reifiedType = (superType as ParameterizedType).actualTypeArguments.first()!!
    return TypeInfo(T::class, reifiedType)
}

/**
 * Check [this] is instance of [type].
 */
internal actual fun Any.instanceOf(type: KClass<*>): Boolean = type.java.isInstance(this)
