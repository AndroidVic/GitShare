
package net.promasoft.trawellmate.args;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DataMobileValidRes {

    @SerializedName("otp")
    private Long mOtp;
    @SerializedName("tempid")
    private Long mTempid;

    public Long getOtp() {
        return mOtp;
    }

    public void setOtp(Long otp) {
        mOtp = otp;
    }

    public Long getTempid() {
        return mTempid;
    }

    public void setTempid(Long tempid) {
        mTempid = tempid;
    }

}
