package id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.views;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class OpenGLView extends GLSurfaceView {

    public boolean GLReady = false;
    public OpenGLRenderer renderer;

    public OpenGLView(Context context) {
        super(context);
        init();
    }

    public OpenGLView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        setEGLContextClientVersion(2);
        setPreserveEGLContextOnPause(true);
        renderer = new OpenGLRenderer();
        setRenderer(renderer);
        GLReady = true;
    }
}