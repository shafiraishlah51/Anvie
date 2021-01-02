package id.ac.ui.cs.mobileprogramming.shafira_ishlah.anvie.views;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class MusicView extends GLSurfaceView {

    public MusicView(Context context) {
        super(context);
        init();
    }

    public MusicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setEGLContextClientVersion(2);
        setPreserveEGLContextOnPause(true);
        setRenderer(new MusicRenderer());
    }

}