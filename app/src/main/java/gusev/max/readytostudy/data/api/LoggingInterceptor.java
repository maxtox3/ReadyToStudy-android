package gusev.max.readytostudy.data.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by v on 13/01/2018.
 */

public class LoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY).intercept(chain);
    }
}
