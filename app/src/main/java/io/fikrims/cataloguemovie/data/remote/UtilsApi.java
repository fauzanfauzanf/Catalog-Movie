package io.fikrims.cataloguemovie.data.remote;

import android.content.Context;
import static io.fikrims.cataloguemovie.utils.Constant.Utils.BASE_URL;

public class UtilsApi {

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(Context context){
        return RetrofitClient.getClient(BASE_URL,context).create(BaseApiService.class);
    }
}
