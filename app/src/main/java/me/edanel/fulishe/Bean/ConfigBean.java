package me.edanel.fulishe.Bean;

import java.util.List;

/**
 * Created by Eggplant on 2015-03-26 02:32:18.
 */
public class ConfigBean {
    private List<catesBean> cates;
    private static ConfigBean instance;

    public List<catesBean> getCates() {
        return cates;
    }

    public void setCates(List<catesBean> cates) {
        this.cates = cates;
    }



    public static ConfigBean getInstance() {
        if (instance == null){
            instance = new ConfigBean();
        }
        return instance;
    }

    public static void setInstance(ConfigBean instance) {
        ConfigBean.instance = instance;
    }
}
