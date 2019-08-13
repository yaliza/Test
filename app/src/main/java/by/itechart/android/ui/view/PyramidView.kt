package by.itechart.android.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import by.itechart.android.R
import by.itechart.android.ext.loadCircle
import kotlinx.android.synthetic.main.view_pyramid.view.*
import java.lang.IllegalArgumentException


class PyramidView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var colors: IntArray

    private val path = Path()
    private val paint = Paint()

    private val levelString: String

    private var textSize: Float = 0f

    private var blockHeight: Float = 0f
    private var fillBlockHeight: Float = 0f
    private var photoSize: Float = 0f

    var levels: Int = 6
        set(value) {
            if (value < 0) throw IllegalArgumentException("Level count must be positive number")
            field = value
            invalidate()
        }

    var userLevel: Int = 3
        set(value) {
            if (value > levels) throw IllegalArgumentException("User level must be less or equal than level numbers")
            if (value < 0) throw IllegalArgumentException("User level must be positive number")
            field = value
            pyramidImageView.updateVerticalPosition()
        }

    init {
        setWillNotDraw(false)
        inflate(context, R.layout.view_pyramid, this)
        initAttributes(context, attrs)
        levelString = context.resources.getString(R.string.community_level)
        paint.textSize = textSize
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        blockHeight = height.toFloat() / levels
        fillBlockHeight = blockHeight * 9f / 10
        photoSize = fillBlockHeight * 4f / 5f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            val pyramidCenterX = (width.toFloat() * 5f / 7f)
            val kLeft = -height.toFloat() / pyramidCenterX
            val bLeft = height.toFloat()
            val kRight = -kLeft
            val bRight = -kRight * pyramidCenterX

            var y = height.toFloat()
            for (i in 0 until levels) {
                val currentHeight = if (i == levels - 1) blockHeight else fillBlockHeight
                path.drawBlock(y, kLeft, bLeft, kRight, bRight, currentHeight)
                paint.color = colors[i % colors.size]
                canvas.drawPath(path, paint)
                paint.color = Color.WHITE
                canvas.drawText("${i + 1}", pyramidCenterX - paint.textSize / 4, y - currentHeight / 3, paint)
                if (i == 0) canvas.drawText(levelString, pyramidCenterX / 4, y - currentHeight / 3, paint)
                y -= blockHeight
            }

            with(pyramidImageView) {
                if (layoutParams.height != photoSize.toInt()) {
                    layoutParams.height = photoSize.toInt()
                    layoutParams.width = photoSize.toInt()
                    requestLayout()
                }
                this.x = pyramidCenterX * 5f / 8f
                updateVerticalPosition()
            }
        }
    }

    fun setImage(photoUrl: String?) = pyramidImageView.loadCircle(photoUrl)

    private fun ImageView.updateVerticalPosition() {
        y = if (userLevel == levels) {
            this@PyramidView.height - blockHeight * userLevel + (blockHeight - photoSize) / 2
        } else {
            this@PyramidView.height - blockHeight * userLevel + (fillBlockHeight - photoSize) / 2 + blockHeight - fillBlockHeight
        }
    }

    private fun Path.drawBlock(y: Float, kLeft: Float, bLeft: Float, kRight: Float, bRight: Float, blockHeight: Float) {
        reset()
        moveTo((y - bLeft) / kLeft, y)
        lineTo((y - blockHeight - bLeft) / kLeft, y - blockHeight)
        lineTo((y - blockHeight - bRight) / kRight, y - blockHeight)
        lineTo((y - bRight) / kRight, y)
        lineTo((y - bLeft) / kLeft, y)
    }

    private fun initAttributes(context: Context, attrs: AttributeSet?) =
        context.theme.obtainStyledAttributes(attrs, R.styleable.PyramidView, 0, 0).apply {
            levels = getInteger(R.styleable.PyramidView_levels, levels)
            colors = context.resources.getIntArray(getResourceId(R.styleable.PyramidView_colors, R.array.pyramidColors))
            textSize = context.resources.getDimension(getResourceId(R.styleable.PyramidView_text_size, R.dimen.text_M))
            recycle()
        }
}