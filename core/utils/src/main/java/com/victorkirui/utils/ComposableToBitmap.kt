import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


suspend fun captureComposableAsBitmap(
    content: @Composable () -> Unit,
    activity: Activity,
    width: Int? = null,
    height: Int? = null
): Bitmap = withContext(Dispatchers.Main) {
    val composeView = ComposeView(activity).apply {
        // Dispose of the Composition when the view is detached.
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            content()
        }
    }

    // Add the ComposeView to the activity's content view to allow proper measurement.
    activity.addContentView(
        composeView,
        android.view.ViewGroup.LayoutParams(
            android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            android.view.ViewGroup.LayoutParams.WRAP_CONTENT
        )
    )

    // Wait for the layout to be measured.
    composeView.measure(
        View.MeasureSpec.makeMeasureSpec(width ?: 0, if (width == null) View.MeasureSpec.UNSPECIFIED else View.MeasureSpec.EXACTLY),
        View.MeasureSpec.makeMeasureSpec(height ?: 0, if (height == null) View.MeasureSpec.UNSPECIFIED else View.MeasureSpec.EXACTLY)
    )
    composeView.layout(0, 0, composeView.measuredWidth, composeView.measuredHeight)

    // Check if the layout was measured successfully.
    if (composeView.measuredWidth <= 0 || composeView.measuredHeight <= 0) {
        throw IllegalStateException("Composable layout could not be measured.")
    }

    // Create a Bitmap with the measured dimensions.
    val bitmap = Bitmap.createBitmap(
        composeView.measuredWidth,
        composeView.measuredHeight,
        Bitmap.Config.ARGB_8888
    )

    // Create a Canvas to draw on the Bitmap.
    val canvas = Canvas(bitmap)
    canvas.drawColor(Color.WHITE) // Optional: Set a background color.

    // Draw the ComposeView onto the Canvas.
    composeView.draw(canvas)

    // Remove the ComposeView from the activity's content view.
    (composeView.parent as? android.view.ViewGroup)?.removeView(composeView)

    bitmap
}
