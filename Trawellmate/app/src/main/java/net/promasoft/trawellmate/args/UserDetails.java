
package net.promasoft.trawellmate.args;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class UserDetails {

    @SerializedName("address1")
    private String mAddress1;
    @SerializedName("address2")
    private String mAddress2;
    @SerializedName("city")
    private String mCity;
    @SerializedName("country")
    private String mCountry;
    @SerializedName("dob")
    private String mDob;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("fname")
    private String mFname;
    @SerializedName("lname")
    private String mLname;
    @SerializedName("mail_v_status")
    private Long mMailVStatus;
    @SerializedName("mname")
    private String mMname;
    @SerializedName("mob_v_status")
    private Long mMobVStatus;
    @SerializedName("mobile")
    private Long mMobile;
    @SerializedName("passport")
    private String mPassport;
    @SerializedName("phone")
    private Long mPhone;
    @SerializedName("salutation")
    private String mSalutation;
    @SerializedName("state")
    private String mState;
    @SerializedName("token_id")
    private Object mTokenId;
    @SerializedName("uid")
    private Long mUid;
    @SerializedName("userimage")
    private String mUserimage;
    @SerializedName("zip")
    private String mZip;

    public String getAddress1() {
        return mAddress1;
    }

    public void setAddress1(String address1) {
        mAddress1 = address1;
    }

    public String getAddress2() {
        return mAddress2;
    }

    public void setAddress2(String address2) {
        mAddress2 = address2;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public String getDob() {
        return mDob;
    }

    public void setDob(String dob) {
        mDob = dob;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getFname() {
        return mFname;
    }

    public void setFname(String fname) {
        mFname = fname;
    }

    public String getLname() {
        return mLname;
    }

    public void setLname(String lname) {
        mLname = lname;
    }

    public Long getMailVStatus() {
        return mMailVStatus;
    }

    public void setMailVStatus(Long mailVStatus) {
        mMailVStatus = mailVStatus;
    }

    public String getMname() {
        return mMname;
    }

    public void setMname(String mname) {
        mMname = mname;
    }

    public Long getMobVStatus() {
        return mMobVStatus;
    }

    public void setMobVStatus(Long mobVStatus) {
        mMobVStatus = mobVStatus;
    }

    public Long getMobile() {
        return mMobile;
    }

    public void setMobile(Long mobile) {
        mMobile = mobile;
    }

    public String getPassport() {
        return mPassport;
    }

    public void setPassport(String passport) {
        mPassport = passport;
    }

    public Long getPhone() {
        return mPhone;
    }

    public void setPhone(Long phone) {
        mPhone = phone;
    }

    public String getSalutation() {
        return mSalutation;
    }

    public void setSalutation(String salutation) {
        mSalutation = salutation;
    }

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        mState = state;
    }

    public Object getTokenId() {
        return mTokenId;
    }

    public void setTokenId(Object tokenId) {
        mTokenId = tokenId;
    }

    public Long getUid() {
        return mUid;
    }

    public void setUid(Long uid) {
        mUid = uid;
    }

    public String getUserimage() {
        return mUserimage;
    }

    public void setUserimage(String userimage) {
        mUserimage = userimage;
    }

    public String getZip() {
        return mZip;
    }

    public void setZip(String zip) {
        mZip = zip;
    }

}
