
package net.promasoft.trawellmate.args;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class DataLogin {

    @SerializedName("user_details")
    private UserDetails mUserDetails;

    public UserDetails getUserDetails() {
        return mUserDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        mUserDetails = userDetails;
    }

}
