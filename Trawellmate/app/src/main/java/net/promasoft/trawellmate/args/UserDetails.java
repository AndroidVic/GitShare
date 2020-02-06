
package net.promasoft.trawellmate.args;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class UserDetails {

    @SerializedName("mail")
    private String mMail;
    @SerializedName("uid")
    private Long mUid;

    public String getMail() {
        return mMail;
    }

    public void setMail(String mail) {
        mMail = mail;
    }

    public Long getUid() {
        return mUid;
    }

    public void setUid(Long uid) {
        mUid = uid;
    }

}
