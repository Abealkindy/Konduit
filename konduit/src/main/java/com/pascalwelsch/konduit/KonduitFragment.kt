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

package com.pascalwelsch.konduit

import android.app.Activity
import android.content.Context
import android.view.View
import com.pascalwelsch.konduit.widget.Widget
import net.grandcentrix.thirtyinch.TiFragment

/**
 * Automatically binds [Widget]s generated by [KonduitPresenter.build] function to android views when the [Widget.key] matches [View.getId]
 */
abstract class KonduitFragment<P : KonduitPresenter<V>, V : KonduitView> : TiFragment<P, V>(), KonduitView, WidgetBinder {

    override val renderer = AndroidViewRenderer()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        renderer.activity = context as Activity
    }

    override fun onDetach() {
        super.onDetach()
        renderer.activity = null
    }

    override fun getBuildContext(): BuildContext {
        return renderer.getBuildContext()
    }

    override fun render(widgets: List<Widget>) {
        renderer.render(widgets)
    }

    /**
     * Override this method to change/diable the default binding behaviour.
     *
     * Called before [onStart] to onChanged all views.
     */
    protected open fun onBindAllViews() {
        renderer.autobindAllViews(view!!)
    }

    override fun onStart() {
        // onChanged all views (by default) which will become visible soon
        onBindAllViews()
        super.onStart()
    }
}