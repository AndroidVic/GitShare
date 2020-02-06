
package net.promasoft.trawellmate.args;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class RegisterResult {

    @SerializedName("DataRegister")
    private DataRegister mDataRegister;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("token_id")
    private String mTokenId;

    public DataRegister getDataRegister() {
        return mDataRegister;
    }

    public void setDataRegister(DataRegister dataRegister) {
        mDataRegister = dataRegister;
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
