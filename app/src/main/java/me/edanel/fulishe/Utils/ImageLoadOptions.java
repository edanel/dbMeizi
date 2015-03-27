/**
 * 
 */
package me.edanel.fulishe.Utils;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import me.edanel.fulishe.R;

/**
 * @author terily
 *
 */
public class ImageLoadOptions {
	/** 图片加载配置 */
	public static DisplayImageOptions getImagesOptions() {
		DisplayImageOptions 	options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_images_default)
		.showImageForEmptyUri(R.drawable.ic_images_default)
		.showImageOnFail(R.drawable.ic_images_default)
		.resetViewBeforeLoading(true)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.imageScaleType(ImageScaleType.EXACTLY)
		.bitmapConfig(Bitmap.Config.ARGB_8888)
//		.displayer(new RoundedBitmapDisplayer(200))
		.displayer(new FadeInBitmapDisplayer(800))// 淡入
		.build();
		return options;
		
	}
	

}
