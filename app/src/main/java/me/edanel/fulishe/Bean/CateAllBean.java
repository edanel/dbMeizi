package me.edanel.fulishe.Bean;

import java.util.List;

/**
 * Created by Eggplant on 2015-03-26 02:31:50.
 */
public class CateAllBean {

    private List<imagesBean> imgs;

    private static CateAllBean instance;

    public static CateAllBean getInstance() {
        if (instance == null) {
            instance = new CateAllBean();
        }
        return instance;
    }

    public static void setInstance(CateAllBean instance) {
        CateAllBean.instance = instance;
    }


    public List<imagesBean> getImgs() {
        return imgs;
    }

    public void setImgs(List<imagesBean> imgs) {
        this.imgs = imgs;
    }
}
