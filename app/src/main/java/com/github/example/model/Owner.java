
package com.github.example.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Owner implements Parcelable {

    @SerializedName("avatar_url")
    private String mAvatarUrl;
    @SerializedName("events_url")
    private String mEventsUrl;
    @SerializedName("followers_url")
    private String mFollowersUrl;
    @SerializedName("following_url")
    private String mFollowingUrl;
    @SerializedName("gists_url")
    private String mGistsUrl;
    @SerializedName("gravatar_id")
    private String mGravatarId;
    @SerializedName("html_url")
    private String mHtmlUrl;
    @SerializedName("id")
    private Long mId;
    @SerializedName("login")
    private String mLogin;
    @SerializedName("organizations_url")
    private String mOrganizationsUrl;
    @SerializedName("received_events_url")
    private String mReceivedEventsUrl;
    @SerializedName("repos_url")
    private String mReposUrl;
    @SerializedName("site_admin")
    private Boolean mSiteAdmin;
    @SerializedName("starred_url")
    private String mStarredUrl;
    @SerializedName("subscriptions_url")
    private String mSubscriptionsUrl;
    @SerializedName("type")
    private String mType;
    @SerializedName("url")
    private String mUrl;

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
    }

    public String getEventsUrl() {
        return mEventsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        mEventsUrl = eventsUrl;
    }

    public String getFollowersUrl() {
        return mFollowersUrl;
    }

    public void setFollowersUrl(String followersUrl) {
        mFollowersUrl = followersUrl;
    }

    public String getFollowingUrl() {
        return mFollowingUrl;
    }

    public void setFollowingUrl(String followingUrl) {
        mFollowingUrl = followingUrl;
    }

    public String getGistsUrl() {
        return mGistsUrl;
    }

    public void setGistsUrl(String gistsUrl) {
        mGistsUrl = gistsUrl;
    }

    public String getGravatarId() {
        return mGravatarId;
    }

    public void setGravatarId(String gravatarId) {
        mGravatarId = gravatarId;
    }

    public String getHtmlUrl() {
        return mHtmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        mHtmlUrl = htmlUrl;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getLogin() {
        return mLogin;
    }

    public void setLogin(String login) {
        mLogin = login;
    }

    public String getOrganizationsUrl() {
        return mOrganizationsUrl;
    }

    public void setOrganizationsUrl(String organizationsUrl) {
        mOrganizationsUrl = organizationsUrl;
    }

    public String getReceivedEventsUrl() {
        return mReceivedEventsUrl;
    }

    public void setReceivedEventsUrl(String receivedEventsUrl) {
        mReceivedEventsUrl = receivedEventsUrl;
    }

    public String getReposUrl() {
        return mReposUrl;
    }

    public void setReposUrl(String reposUrl) {
        mReposUrl = reposUrl;
    }

    public Boolean getSiteAdmin() {
        return mSiteAdmin;
    }

    public void setSiteAdmin(Boolean siteAdmin) {
        mSiteAdmin = siteAdmin;
    }

    public String getStarredUrl() {
        return mStarredUrl;
    }

    public void setStarredUrl(String starredUrl) {
        mStarredUrl = starredUrl;
    }

    public String getSubscriptionsUrl() {
        return mSubscriptionsUrl;
    }

    public void setSubscriptionsUrl(String subscriptionsUrl) {
        mSubscriptionsUrl = subscriptionsUrl;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mAvatarUrl);
        dest.writeString(this.mEventsUrl);
        dest.writeString(this.mFollowersUrl);
        dest.writeString(this.mFollowingUrl);
        dest.writeString(this.mGistsUrl);
        dest.writeString(this.mGravatarId);
        dest.writeString(this.mHtmlUrl);
        dest.writeValue(this.mId);
        dest.writeString(this.mLogin);
        dest.writeString(this.mOrganizationsUrl);
        dest.writeString(this.mReceivedEventsUrl);
        dest.writeString(this.mReposUrl);
        dest.writeValue(this.mSiteAdmin);
        dest.writeString(this.mStarredUrl);
        dest.writeString(this.mSubscriptionsUrl);
        dest.writeString(this.mType);
        dest.writeString(this.mUrl);
    }

    public Owner() {
    }

    protected Owner(Parcel in) {
        this.mAvatarUrl = in.readString();
        this.mEventsUrl = in.readString();
        this.mFollowersUrl = in.readString();
        this.mFollowingUrl = in.readString();
        this.mGistsUrl = in.readString();
        this.mGravatarId = in.readString();
        this.mHtmlUrl = in.readString();
        this.mId = (Long) in.readValue(Long.class.getClassLoader());
        this.mLogin = in.readString();
        this.mOrganizationsUrl = in.readString();
        this.mReceivedEventsUrl = in.readString();
        this.mReposUrl = in.readString();
        this.mSiteAdmin = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mStarredUrl = in.readString();
        this.mSubscriptionsUrl = in.readString();
        this.mType = in.readString();
        this.mUrl = in.readString();
    }

    public static final Parcelable.Creator<Owner> CREATOR = new Parcelable.Creator<Owner>() {
        @Override
        public Owner createFromParcel(Parcel source) {
            return new Owner(source);
        }

        @Override
        public Owner[] newArray(int size) {
            return new Owner[size];
        }
    };
}
