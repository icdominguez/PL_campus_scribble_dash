package com.icdominguez.scribbledash.domain

import android.content.Context
import android.util.Log
import androidx.compose.ui.graphics.vector.PathParser
import com.icdominguez.scribbledash.R
import com.icdominguez.scribbledash.ui.screens.drawing.VectorData
import org.xmlpull.v1.XmlPullParser

class GetRandomAssetUseCase(private val context: Context) {
    operator fun invoke(): VectorData {
        val fields = R.drawable::class.java.fields
        val allFiles = fields.filter { it.name.startsWith("drawing_") }

        var vectorData = VectorData()
        val parser = context.resources.getXml(allFiles.random().getInt(null))

        val randomPhrase = context.resources.getStringArray(R.array.feedback_ops_meh).random()

        Log.i("icd", randomPhrase)

        var eventType = parser.eventType
        while (eventType != XmlPullParser.END_DOCUMENT) {
               if (eventType == XmlPullParser.START_TAG && parser.name == "path") {
                   val pathData = parser.getAttributeValue("http://schemas.android.com/apk/res/android", "pathData")
                   if(pathData != null) {
                       vectorData = vectorData.copy(paths = vectorData.paths + PathParser().parsePathString(pathData).toPath())
                   }
               } else if(eventType == XmlPullParser.START_TAG && parser.name == "vector") {
                   val viewPortWidth = parser.getAttributeValue("http://schemas.android.com/apk/res/android", "viewportWidth").toFloatOrNull()
                   val viewPortHeight = parser.getAttributeValue("http://schemas.android.com/apk/res/android", "viewportHeight").toFloatOrNull()
                   if(viewPortHeight != null) {
                       vectorData = vectorData.copy(viewportHeight = viewPortHeight)
                   }

                   if(viewPortWidth != null) {
                       vectorData = vectorData.copy(viewportWidth = viewPortWidth)
                   }
               }
            eventType = parser.next()
        }

        return vectorData
    }
}