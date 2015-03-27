package me.edanel.fulishe.Imp;

import me.edanel.fulishe.Bean.CateAllBean;
import me.edanel.fulishe.Bean.ConfigBean;
import me.edanel.fulishe.CallBack.Connected;
import me.edanel.fulishe.CallBack.HttpConnectedListener;
import me.edanel.fulishe.CallBack.ResultListener;
import me.edanel.fulishe.Config.Url;
import me.edanel.fulishe.Utils.HttpConnect;
import me.edanel.fulishe.Utils.JsonUtils;

/**
 * Created by Eggplant on 2015-03-26 02:31:58.
 */
public class ConnectedImp implements Connected {
    @Override
    public void getConfig(final ResultListener listener) {
        String jsonData = new HttpConnect().get(Url.HOST_CONFIG, new HttpConnectedListener() {


            @Override
            public void onSuccess(String str) {
                ConfigBean configBean = JsonUtils.getInstance().Json2Bean(str.toString(), ConfigBean.class);
                ConfigBean.setInstance(configBean);//设置单例
                listener.onSuccess();
            }

            @Override
            public void onFail(String str) {

            }
        });
    }

    @Override
    public void getImageALL(String url,final ResultListener listener) {
        String jsonData = new HttpConnect().get(url, new HttpConnectedListener() {

            @Override
            public void onSuccess(String str) {
                CateAllBean cateAllBean = JsonUtils.getInstance().Json2Bean(str.toString(),CateAllBean.class);
                cateAllBean.setInstance(cateAllBean);
                listener.onSuccess();
            }
            @Override
            public void onFail(String str) {

            }
        });
    }
}
