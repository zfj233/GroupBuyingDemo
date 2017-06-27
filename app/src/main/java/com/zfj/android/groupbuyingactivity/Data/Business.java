package com.zfj.android.groupbuyingactivity.Data;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Created by zfj_ on 2017/5/29.
 */

public class Business {
    private String businessName;
    private Drawable businessPic;

    public Business(String businessName, Drawable businessPic) {
        this.businessName = businessName;
        this.businessPic = businessPic;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Drawable getBusinessPic() {
        return businessPic;
    }

    public void setBusinessPic(Drawable businessPic) {
        this.businessPic = businessPic;
    }
}
