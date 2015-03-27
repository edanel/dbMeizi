package me.edanel.fulishe.Utils;

import com.google.gson.Gson;

/**
 * Created by Eggplant on 15-1-24.
 */
public class JsonUtils {

    private static Gson gson;
    private static JsonUtils instance;


    /**
     * gson 单例
     *
     * @return
     */
    public static JsonUtils getInstance() {
        if (gson == null) {
            gson = new Gson();
        }
        return instance;
    }

    /**
     * Json数据解析到Bean
     *
     * @param data
     * @param Jclass
     * @param <E>
     * @return
     */
    public static <E> E Json2Bean(String data, Class<E> Jclass) {
        E e = null;
        if (gson != null) {
            e = gson.fromJson(data, Jclass);
        }
        return e;
    }
}
