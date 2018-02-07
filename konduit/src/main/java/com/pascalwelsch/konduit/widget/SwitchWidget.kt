/*
 * Copyright (C) 2018 Pascal Welsch
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pascalwelsch.konduit.widget

fun WidgetListBuilder.switch(init: SwitchWidget.() -> Unit) = add(SwitchWidget(), init)

class SwitchWidget : Widget() {
    /**
     * emits the current value when the user changes it.
     */
    var onSwitch: ((Boolean) -> Unit)? = null
        set(value) {
            checkWritability()
            field = value
        }

    var checked: Boolean = false
        set(value) {
            checkWritability()
            field = value
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SwitchWidget) return false
        if (!super.equals(other)) return false

        if (onSwitch != other.onSwitch) return false
        if (checked != other.checked) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (onSwitch?.hashCode() ?: 0)
        result = 31 * result + checked.hashCode()
        return result
    }
}