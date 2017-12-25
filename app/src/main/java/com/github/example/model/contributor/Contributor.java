package com.github.example.model.contributor;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Contributor implements Parcelable {
    @SerializedName("avatar_url")
    public String avatarUrl;
    @SerializedName("contributions")
    public int contributions;
    @SerializedName("events_url")
    public String eventsUrl;
    @SerializedName("followers_url")
    public String followersUrl;
    @SerializedName("following_url")
    public String followingUrl;
    @SerializedName("gists_url")
    public String gistsUrl;
    @SerializedName("gravatar_id")
    public String gravatarId;
    @SerializedName("html_url")
    public String htmlUrl;
    @SerializedName("id")
    public int id;
    @SerializedName("login")
    public String login;
    @SerializedName("organizations_url")
    public String organizationsUrl;
    @SerializedName("received_events_url")
    public String receivedEventsUrl;
    @SerializedName("repos_url")
    public String reposUrl;
    @SerializedName("site_admin")
    public boolean siteAdmin;
    @SerializedName("starred_url")
    public String starredUrl;
    @SerializedName("subscriptions_url")
    public String subscriptionsUrl;
    @SerializedName("type")
    public String type;
    @SerializedName("url")
    public String url;

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getGravatarId() {
        return this.gravatarId;
    }

    public void setGravatarId(String gravatarId) {
        this.gravatarId = gravatarId;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtmlUrl() {
        return this.htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getFollowersUrl() {
        return this.followersUrl;
    }

    public void setFollowersUrl(String followersUrl) {
        this.followersUrl = followersUrl;
    }

    public String getFollowingUrl() {
        return this.followingUrl;
    }

    public void setFollowingUrl(String followingUrl) {
        this.followingUrl = followingUrl;
    }

    public String getGistsUrl() {
        return this.gistsUrl;
    }

    public void setGistsUrl(String gistsUrl) {
        this.gistsUrl = gistsUrl;
    }

    public String getStarredUrl() {
        return this.starredUrl;
    }

    public void setStarredUrl(String starredUrl) {
        this.starredUrl = starredUrl;
    }

    public String getSubscriptionsUrl() {
        return this.subscriptionsUrl;
    }

    public void setSubscriptionsUrl(String subscriptionsUrl) {
        this.subscriptionsUrl = subscriptionsUrl;
    }

    public String getOrganizationsUrl() {
        return this.organizationsUrl;
    }

    public void setOrganizationsUrl(String organizationsUrl) {
        this.organizationsUrl = organizationsUrl;
    }

    public String getReposUrl() {
        return this.reposUrl;
    }

    public void setReposUrl(String reposUrl) {
        this.reposUrl = reposUrl;
    }

    public String getEventsUrl() {
        return this.eventsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    public String getReceivedEventsUrl() {
        return this.receivedEventsUrl;
    }

    public void setReceivedEventsUrl(String receivedEventsUrl) {
        this.receivedEventsUrl = receivedEventsUrl;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSiteAdmin() {
        return this.siteAdmin;
    }

    public void setSiteAdmin(boolean siteAdmin) {
        this.siteAdmin = siteAdmin;
    }

    public int getContributions() {
        return this.contributions;
    }

    public void setContributions(int contributions) {
        this.contributions = contributions;
    }

    @Override
    public String toString() {
        return "Contributor{" +
                "avatarUrl='" + avatarUrl + '\'' +
                ", contributions=" + contributions +
                ", eventsUrl='" + eventsUrl + '\'' +
                ", followersUrl='" + followersUrl + '\'' +
                ", followingUrl='" + followingUrl + '\'' +
                ", gistsUrl='" + gistsUrl + '\'' +
                ", gravatarId='" + gravatarId + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                ", id=" + id +
                ", login='" + login + '\'' +
                ", organizationsUrl='" + organizationsUrl + '\'' +
                ", receivedEventsUrl='" + receivedEventsUrl + '\'' +
                ", reposUrl='" + reposUrl + '\'' +
                ", siteAdmin=" + siteAdmin +
                ", starredUrl='" + starredUrl + '\'' +
                ", subscriptionsUrl='" + subscriptionsUrl + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.avatarUrl);
        dest.writeInt(this.contributions);
        dest.writeString(this.eventsUrl);
        dest.writeString(this.followersUrl);
        dest.writeString(this.followingUrl);
        dest.writeString(this.gistsUrl);
        dest.writeString(this.gravatarId);
        dest.writeString(this.htmlUrl);
        dest.writeInt(this.id);
        dest.writeString(this.login);
        dest.writeString(this.organizationsUrl);
        dest.writeString(this.receivedEventsUrl);
        dest.writeString(this.reposUrl);
        dest.writeByte(this.siteAdmin ? (byte) 1 : (byte) 0);
        dest.writeString(this.starredUrl);
        dest.writeString(this.subscriptionsUrl);
        dest.writeString(this.type);
        dest.writeString(this.url);
    }

    public Contributor() {
    }

    protected Contributor(Parcel in) {
        this.avatarUrl = in.readString();
        this.contributions = in.readInt();
        this.eventsUrl = in.readString();
        this.followersUrl = in.readString();
        this.followingUrl = in.readString();
        this.gistsUrl = in.readString();
        this.gravatarId = in.readString();
        this.htmlUrl = in.readString();
        this.id = in.readInt();
        this.login = in.readString();
        this.organizationsUrl = in.readString();
        this.receivedEventsUrl = in.readString();
        this.reposUrl = in.readString();
        this.siteAdmin = in.readByte() != 0;
        this.starredUrl = in.readString();
        this.subscriptionsUrl = in.readString();
        this.type = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<Contributor> CREATOR = new Parcelable.Creator<Contributor>() {
        @Override
        public Contributor createFromParcel(Parcel source) {
            return new Contributor(source);
        }

        @Override
        public Contributor[] newArray(int size) {
            return new Contributor[size];
        }
    };
}
