package flyingduo.intellifacts;

import android.app.Application;
import android.content.Context;

public class intellifacts extends Application {
    public Context getContext() {

        Context mContext;

        super.onCreate();
        mContext = getApplicationContext();

        {
            return mContext;
        }
    }
}
