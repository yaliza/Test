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
    private val textPaint = Paint().apply {
        isAntiAlias = true
        color = Color.WHITE
    }

    private val levelString: String

    private var levelCount: Int = 6

    private var textSize: Float = 0f

    private var blockHeight: Float = 0f
    private var fillBlockHeight: Float = 0f
    private var photoSize: Float = 0f
    private var pyramidCenterX = 0f

    // y = kx + b
    // left pyramid side
    private var kLeft: Float = 0f
    private var bLeft: Float = 0f
    // right pyramid side
    private var kRight: Float = 0f
    private var bRight: Float = 0f

    var userLevel: Int = 3
        set(value) {
            if (userLevel !in 1..levelCount) throw IllegalArgumentException("Incorrect user level")
            field = value
            pyramidImageView.updateVerticalPosition()
        }

    var photoUrl: String? = null
        set(value) {
            field = value
            pyramidImageView.loadCircle(value)
        }

    init {
        setWillNotDraw(false)
        inflate(context, R.layout.view_pyramid, this)
        initAttributes(context, attrs)
        levelString = context.resources.getString(R.string.community_level)
        textPaint.textSize = textSize
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        blockHeight = height.toFloat() / levelCount
        fillBlockHeight = blockHeight * 9f / 10
        photoSize = fillBlockHeight * 4f / 5f
        pyramidCenterX = (width.toFloat() * 5f / 7f)

        kLeft = -height.toFloat() / pyramidCenterX
        bLeft = height.toFloat()
        kRight = -kLeft
        bRight = -kRight * pyramidCenterX
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawBlocks(canvas)
        setupAvatar()
    }

    private fun setupAvatar() =
        with(pyramidImageView) {
            if (layoutParams.height != photoSize.toInt()) {
                layoutParams.height = photoSize.toInt()
                layoutParams.width = photoSize.toInt()
                requestLayout()
            }
            x = pyramidCenterX * 5f / 8f
            updateVerticalPosition()
        }

    private fun ImageView.updateVerticalPosition() {
        y = if (userLevel == levelCount) {
            this@PyramidView.height - blockHeight * userLevel + (blockHeight - photoSize) / 2
        } else {
            this@PyramidView.height - blockHeight * userLevel + (fillBlockHeight - photoSize) / 2 + blockHeight - fillBlockHeight
        }
    }

    private fun drawBlocks(canvas: Canvas) {
        var y = height.toFloat()
        for (i in 0 until levelCount) {
            val currentHeight = if (i == levelCount - 1) blockHeight else fillBlockHeight
            path.drawBlock(y, kLeft, bLeft, kRight, bRight, currentHeight)
            canvas.drawPath(path, paint.apply { color = colors[i % colors.size] })
            val textY = if (i == levelCount - 1) y - currentHeight / 4 else y - currentHeight / 3
            canvas.drawText("${i + 1}", pyramidCenterX - textPaint.textSize / 4, textY, textPaint)
            if (i == 0) canvas.drawText(levelString, pyramidCenterX / 4, y - currentHeight / 3, textPaint)
            y -= blockHeight
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
            levelCount = getInteger(R.styleable.PyramidView_pv_level_count, levelCount)
            colors =
                context.resources.getIntArray(getResourceId(R.styleable.PyramidView_pv_colors, R.array.pyramidColors))
            textSize =
                context.resources.getDimension(getResourceId(R.styleable.PyramidView_pv_text_size, R.dimen.text_M))
            recycle()
        }
}