
package net.promasoft.trawellmate.args;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ValidatMobileResult {
//    {"status":"error","token_id":null,"data":{},"message":"Mobile number already registered."}
    @SerializedName("data")
    private DataMobileValidRes mData;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("token_id")
    private String mTokenId;

    public DataMobileValidRes getData() {
        return mData;
    }

    public void setData(DataMobileValidRes data) {
        mData = data;
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

    public Object getTokenId() {
        return mTokenId;
    }

    public void setTokenId(String tokenId) {
        mTokenId = tokenId;
    }

}
