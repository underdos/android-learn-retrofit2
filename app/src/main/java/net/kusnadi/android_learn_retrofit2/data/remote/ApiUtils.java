package net.kusnadi.android_learn_retrofit2.data.remote;

/**
 * Created by root on 11/09/17.
 */

public class ApiUtils {

    public static final String BASE_URL = "https://api.stackexchange.com/2.2/";

    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}
