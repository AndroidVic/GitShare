
package net.promasoft.trawellmate.args;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class LoginDetails {

    @SerializedName("DataLogin")
    private DataLogin mDataLogin;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("Token_id")
    private String mTokenId;

    public DataLogin getDataLogin() {
        return mDataLogin;
    }

    public void setDataLogin(DataLogin dataLogin) {
        mDataLogin = dataLogin;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getTokenId() {
        return mTokenId;
    }

    public void setTokenId(String tokenId) {
        mTokenId = tokenId;
    }

}
