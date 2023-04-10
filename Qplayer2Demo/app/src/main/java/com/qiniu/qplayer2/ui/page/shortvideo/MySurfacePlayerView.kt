package com.qiniu.qplayer2.ui.page.shortvideo

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.widget.FrameLayout
import com.qiniu.qmedia.component.player.QLogLevel
import com.qiniu.qmedia.component.player.QPlayerContext
import com.qiniu.qmedia.component.player.QPlayerControlHandler
import com.qiniu.qmedia.component.player.QPlayerRenderHandler
import com.qiniu.qmedia.render.screen.QSurfaceRenderView

class MySurfacePlayerView : FrameLayout {


    private val mPlayerContext = QPlayerContext(
        QLogLevel.LOG_DEBUG,
        this.context.getExternalFilesDir(null)?.path ?: "",
        "",
        "/data/data/com.qiniu.qplayer2/files/so")

    val playerControlHandler: QPlayerControlHandler
        get() = mPlayerContext.getPlayerControlHandler()

    val playerRenderHandler: QPlayerRenderHandler
        get() = mPlayerContext.getPlayerRenderHandler()

    private lateinit var mQSurfaceRenderView: QSurfaceRenderView

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

        initRenderView()

    }

    private fun initRenderView() {
        mQSurfaceRenderView = QSurfaceRenderView(context)
        // attach the render view
        val lp = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT,
            Gravity.CENTER
        )
        mQSurfaceRenderView.layoutParams = lp

        mQSurfaceRenderView.attachRenderHandler(mPlayerContext.getPlayerRenderHandler())
        addView(mQSurfaceRenderView)
    }
}