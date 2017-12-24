package com.github.example.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Item implements  Parcelable {
    @SerializedName("archive_url")
    public String archiveUrl;
    @SerializedName("archived")
    public boolean archived;
    @SerializedName("assignees_url")
    public String assigneesUrl;
    @SerializedName("blobs_url")
    public String blobsUrl;
    @SerializedName("branches_url")
    public String branchesUrl;
    @SerializedName("clone_url")
    public String cloneUrl;
    @SerializedName("collaborators_url")
    public String collaboratorsUrl;
    @SerializedName("comments_url")
    public String commentsUrl;
    @SerializedName("commits_url")
    public String commitsUrl;
    @SerializedName("compare_url")
    public String compareUrl;
    @SerializedName("contents_url")
    public String contentsUrl;
    @SerializedName("contributors_url")
    public String contributorsUrl;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("default_branch")
    public String defaultBranch;
    @SerializedName("deployments_url")
    public String deploymentsUrl;
    @SerializedName("description")
    public String description;
    @SerializedName("downloads_url")
    public String downloadsUrl;
    @SerializedName("events_url")
    public String eventsUrl;
    @SerializedName("fork")
    public boolean fork;
    @SerializedName("forks")
    public int forks;
    @SerializedName("forks_count")
    public int forksCount;
    @SerializedName("forks_url")
    public String forksUrl;
    @SerializedName("full_name")
    public String fullName;
    @SerializedName("git_commits_url")
    public String gitCommitsUrl;
    @SerializedName("git_refs_url")
    public String gitRefsUrl;
    @SerializedName("git_tags_url")
    public String gitTagsUrl;
    @SerializedName("git_url")
    public String gitUrl;
    @SerializedName("has_downloads")
    public boolean hasDownloads;
    @SerializedName("has_issues")
    public boolean hasIssues;
    @SerializedName("has_pages")
    public boolean hasPages;
    @SerializedName("has_projects")
    public boolean hasProjects;
    @SerializedName("has_wiki")
    public boolean hasWiki;
    @SerializedName("homepage")
    public String homepage;
    @SerializedName("hooks_url")
    public String hooksUrl;
    @SerializedName("html_url")
    public String htmlUrl;
    @SerializedName("id")
    public int id;
    @SerializedName("issue_comment_url")
    public String issueCommentUrl;
    @SerializedName("issue_events_url")
    public String issueEventsUrl;
    @SerializedName("issues_url")
    public String issuesUrl;
    @SerializedName("keys_url")
    public String keysUrl;
    @SerializedName("labels_url")
    public String labelsUrl;
    @SerializedName("language")
    public String language;
    @SerializedName("languages_url")
    public String languagesUrl;
    @SerializedName("merges_url")
    public String mergesUrl;
    @SerializedName("milestones_url")
    public String milestonesUrl;
    @SerializedName("mirror_url")
    public String mirrorUrl;
    @SerializedName("name")
    public String name;
    @SerializedName("notifications_url")
    public String notificationsUrl;
    @SerializedName("open_issues")
    public int openIssues;
    @SerializedName("open_issues_count")
    public int openIssuesCount;
    @SerializedName("owner")
    public Owner owner;
    @SerializedName("private")
    public boolean private1;
    @SerializedName("pulls_url")
    public String pullsUrl;
    @SerializedName("pushed_at")
    public String pushedAt;
    @SerializedName("releases_url")
    public String releasesUrl;
    @SerializedName("size")
    public int size;
    @SerializedName("ssh_url")
    public String sshUrl;
    @SerializedName("stargazers_count")
    public int stargazersCount;
    @SerializedName("stargazers_url")
    public String stargazersUrl;
    @SerializedName("statuses_url")
    public String statusesUrl;
    @SerializedName("subscribers_url")
    public String subscribersUrl;
    @SerializedName("subscription_url")
    public String subscriptionUrl;
    @SerializedName("svn_url")
    public String svnUrl;
    @SerializedName("tags_url")
    public String tagsUrl;
    @SerializedName("teams_url")
    public String teamsUrl;
    @SerializedName("trees_url")
    public String treesUrl;
    @SerializedName("updated_at")
    public String updatedAt;
    @SerializedName("url")
    public String url;
    @SerializedName("watchers")
    public int watchers;
    @SerializedName("watchers_count")
    public int watchersCount;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Owner getOwner() {
        return this.owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public boolean isPrivate1() {
        return this.private1;
    }

    public void setPrivate1(boolean private1) {
        this.private1 = private1;
    }

    public String getHtmlUrl() {
        return this.htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFork() {
        return this.fork;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getForksUrl() {
        return this.forksUrl;
    }

    public void setForksUrl(String forksUrl) {
        this.forksUrl = forksUrl;
    }

    public String getKeysUrl() {
        return this.keysUrl;
    }

    public void setKeysUrl(String keysUrl) {
        this.keysUrl = keysUrl;
    }

    public String getCollaboratorsUrl() {
        return this.collaboratorsUrl;
    }

    public void setCollaboratorsUrl(String collaboratorsUrl) {
        this.collaboratorsUrl = collaboratorsUrl;
    }

    public String getTeamsUrl() {
        return this.teamsUrl;
    }

    public void setTeamsUrl(String teamsUrl) {
        this.teamsUrl = teamsUrl;
    }

    public String getHooksUrl() {
        return this.hooksUrl;
    }

    public void setHooksUrl(String hooksUrl) {
        this.hooksUrl = hooksUrl;
    }

    public String getIssueEventsUrl() {
        return this.issueEventsUrl;
    }

    public void setIssueEventsUrl(String issueEventsUrl) {
        this.issueEventsUrl = issueEventsUrl;
    }

    public String getEventsUrl() {
        return this.eventsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    public String getAssigneesUrl() {
        return this.assigneesUrl;
    }

    public void setAssigneesUrl(String assigneesUrl) {
        this.assigneesUrl = assigneesUrl;
    }

    public String getBranchesUrl() {
        return this.branchesUrl;
    }

    public void setBranchesUrl(String branchesUrl) {
        this.branchesUrl = branchesUrl;
    }

    public String getTagsUrl() {
        return this.tagsUrl;
    }

    public void setTagsUrl(String tagsUrl) {
        this.tagsUrl = tagsUrl;
    }

    public String getBlobsUrl() {
        return this.blobsUrl;
    }

    public void setBlobsUrl(String blobsUrl) {
        this.blobsUrl = blobsUrl;
    }

    public String getGitTagsUrl() {
        return this.gitTagsUrl;
    }

    public void setGitTagsUrl(String gitTagsUrl) {
        this.gitTagsUrl = gitTagsUrl;
    }

    public String getGitRefsUrl() {
        return this.gitRefsUrl;
    }

    public void setGitRefsUrl(String gitRefsUrl) {
        this.gitRefsUrl = gitRefsUrl;
    }

    public String getTreesUrl() {
        return this.treesUrl;
    }

    public void setTreesUrl(String treesUrl) {
        this.treesUrl = treesUrl;
    }

    public String getStatusesUrl() {
        return this.statusesUrl;
    }

    public void setStatusesUrl(String statusesUrl) {
        this.statusesUrl = statusesUrl;
    }

    public String getLanguagesUrl() {
        return this.languagesUrl;
    }

    public void setLanguagesUrl(String languagesUrl) {
        this.languagesUrl = languagesUrl;
    }

    public String getStargazersUrl() {
        return this.stargazersUrl;
    }

    public void setStargazersUrl(String stargazersUrl) {
        this.stargazersUrl = stargazersUrl;
    }

    public String getContributorsUrl() {
        return this.contributorsUrl;
    }

    public void setContributorsUrl(String contributorsUrl) {
        this.contributorsUrl = contributorsUrl;
    }

    public String getSubscribersUrl() {
        return this.subscribersUrl;
    }

    public void setSubscribersUrl(String subscribersUrl) {
        this.subscribersUrl = subscribersUrl;
    }

    public String getSubscriptionUrl() {
        return this.subscriptionUrl;
    }

    public void setSubscriptionUrl(String subscriptionUrl) {
        this.subscriptionUrl = subscriptionUrl;
    }

    public String getCommitsUrl() {
        return this.commitsUrl;
    }

    public void setCommitsUrl(String commitsUrl) {
        this.commitsUrl = commitsUrl;
    }

    public String getGitCommitsUrl() {
        return this.gitCommitsUrl;
    }

    public void setGitCommitsUrl(String gitCommitsUrl) {
        this.gitCommitsUrl = gitCommitsUrl;
    }

    public String getCommentsUrl() {
        return this.commentsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    public String getIssueCommentUrl() {
        return this.issueCommentUrl;
    }

    public void setIssueCommentUrl(String issueCommentUrl) {
        this.issueCommentUrl = issueCommentUrl;
    }

    public String getContentsUrl() {
        return this.contentsUrl;
    }

    public void setContentsUrl(String contentsUrl) {
        this.contentsUrl = contentsUrl;
    }

    public String getCompareUrl() {
        return this.compareUrl;
    }

    public void setCompareUrl(String compareUrl) {
        this.compareUrl = compareUrl;
    }

    public String getMergesUrl() {
        return this.mergesUrl;
    }

    public void setMergesUrl(String mergesUrl) {
        this.mergesUrl = mergesUrl;
    }

    public String getArchiveUrl() {
        return this.archiveUrl;
    }

    public void setArchiveUrl(String archiveUrl) {
        this.archiveUrl = archiveUrl;
    }

    public String getDownloadsUrl() {
        return this.downloadsUrl;
    }

    public void setDownloadsUrl(String downloadsUrl) {
        this.downloadsUrl = downloadsUrl;
    }

    public String getIssuesUrl() {
        return this.issuesUrl;
    }

    public void setIssuesUrl(String issuesUrl) {
        this.issuesUrl = issuesUrl;
    }

    public String getPullsUrl() {
        return this.pullsUrl;
    }

    public void setPullsUrl(String pullsUrl) {
        this.pullsUrl = pullsUrl;
    }

    public String getMilestonesUrl() {
        return this.milestonesUrl;
    }

    public void setMilestonesUrl(String milestonesUrl) {
        this.milestonesUrl = milestonesUrl;
    }

    public String getNotificationsUrl() {
        return this.notificationsUrl;
    }

    public void setNotificationsUrl(String notificationsUrl) {
        this.notificationsUrl = notificationsUrl;
    }

    public String getLabelsUrl() {
        return this.labelsUrl;
    }

    public void setLabelsUrl(String labelsUrl) {
        this.labelsUrl = labelsUrl;
    }

    public String getReleasesUrl() {
        return this.releasesUrl;
    }

    public void setReleasesUrl(String releasesUrl) {
        this.releasesUrl = releasesUrl;
    }

    public String getDeploymentsUrl() {
        return this.deploymentsUrl;
    }

    public void setDeploymentsUrl(String deploymentsUrl) {
        this.deploymentsUrl = deploymentsUrl;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPushedAt() {
        return this.pushedAt;
    }

    public void setPushedAt(String pushedAt) {
        this.pushedAt = pushedAt;
    }

    public String getGitUrl() {
        return this.gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public String getSshUrl() {
        return this.sshUrl;
    }

    public void setSshUrl(String sshUrl) {
        this.sshUrl = sshUrl;
    }

    public String getCloneUrl() {
        return this.cloneUrl;
    }

    public void setCloneUrl(String cloneUrl) {
        this.cloneUrl = cloneUrl;
    }

    public String getSvnUrl() {
        return this.svnUrl;
    }

    public void setSvnUrl(String svnUrl) {
        this.svnUrl = svnUrl;
    }

    public String getHomepage() {
        return this.homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStargazersCount() {
        return this.stargazersCount;
    }

    public void setStargazersCount(int stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public int getWatchersCount() {
        return this.watchersCount;
    }

    public void setWatchersCount(int watchersCount) {
        this.watchersCount = watchersCount;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isHasIssues() {
        return this.hasIssues;
    }

    public void setHasIssues(boolean hasIssues) {
        this.hasIssues = hasIssues;
    }

    public boolean isHasProjects() {
        return this.hasProjects;
    }

    public void setHasProjects(boolean hasProjects) {
        this.hasProjects = hasProjects;
    }

    public boolean isHasDownloads() {
        return this.hasDownloads;
    }

    public void setHasDownloads(boolean hasDownloads) {
        this.hasDownloads = hasDownloads;
    }

    public boolean isHasWiki() {
        return this.hasWiki;
    }

    public void setHasWiki(boolean hasWiki) {
        this.hasWiki = hasWiki;
    }

    public boolean isHasPages() {
        return this.hasPages;
    }

    public void setHasPages(boolean hasPages) {
        this.hasPages = hasPages;
    }

    public int getForksCount() {
        return this.forksCount;
    }

    public void setForksCount(int forksCount) {
        this.forksCount = forksCount;
    }

    public String getMirrorUrl() {
        return this.mirrorUrl;
    }

    public void setMirrorUrl(String mirrorUrl) {
        this.mirrorUrl = mirrorUrl;
    }

    public boolean isArchived() {
        return this.archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public int getOpenIssuesCount() {
        return this.openIssuesCount;
    }

    public void setOpenIssuesCount(int openIssuesCount) {
        this.openIssuesCount = openIssuesCount;
    }

    public int getForks() {
        return this.forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    public int getOpenIssues() {
        return this.openIssues;
    }

    public void setOpenIssues(int openIssues) {
        this.openIssues = openIssues;
    }

    public int getWatchers() {
        return this.watchers;
    }

    public void setWatchers(int watchers) {
        this.watchers = watchers;
    }

    public String getDefaultBranch() {
        return this.defaultBranch;
    }

    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.archiveUrl);
        dest.writeByte(this.archived ? (byte) 1 : (byte) 0);
        dest.writeString(this.assigneesUrl);
        dest.writeString(this.blobsUrl);
        dest.writeString(this.branchesUrl);
        dest.writeString(this.cloneUrl);
        dest.writeString(this.collaboratorsUrl);
        dest.writeString(this.commentsUrl);
        dest.writeString(this.commitsUrl);
        dest.writeString(this.compareUrl);
        dest.writeString(this.contentsUrl);
        dest.writeString(this.contributorsUrl);
        dest.writeString(this.createdAt);
        dest.writeString(this.defaultBranch);
        dest.writeString(this.deploymentsUrl);
        dest.writeString(this.description);
        dest.writeString(this.downloadsUrl);
        dest.writeString(this.eventsUrl);
        dest.writeByte(this.fork ? (byte) 1 : (byte) 0);
        dest.writeInt(this.forks);
        dest.writeInt(this.forksCount);
        dest.writeString(this.forksUrl);
        dest.writeString(this.fullName);
        dest.writeString(this.gitCommitsUrl);
        dest.writeString(this.gitRefsUrl);
        dest.writeString(this.gitTagsUrl);
        dest.writeString(this.gitUrl);
        dest.writeByte(this.hasDownloads ? (byte) 1 : (byte) 0);
        dest.writeByte(this.hasIssues ? (byte) 1 : (byte) 0);
        dest.writeByte(this.hasPages ? (byte) 1 : (byte) 0);
        dest.writeByte(this.hasProjects ? (byte) 1 : (byte) 0);
        dest.writeByte(this.hasWiki ? (byte) 1 : (byte) 0);
        dest.writeString(this.homepage);
        dest.writeString(this.hooksUrl);
        dest.writeString(this.htmlUrl);
        dest.writeInt(this.id);
        dest.writeString(this.issueCommentUrl);
        dest.writeString(this.issueEventsUrl);
        dest.writeString(this.issuesUrl);
        dest.writeString(this.keysUrl);
        dest.writeString(this.labelsUrl);
        dest.writeString(this.language);
        dest.writeString(this.languagesUrl);
        dest.writeString(this.mergesUrl);
        dest.writeString(this.milestonesUrl);
        dest.writeString(this.mirrorUrl);
        dest.writeString(this.name);
        dest.writeString(this.notificationsUrl);
        dest.writeInt(this.openIssues);
        dest.writeInt(this.openIssuesCount);
        dest.writeParcelable(this.owner, flags);
        dest.writeByte(this.private1 ? (byte) 1 : (byte) 0);
        dest.writeString(this.pullsUrl);
        dest.writeString(this.pushedAt);
        dest.writeString(this.releasesUrl);
        dest.writeInt(this.size);
        dest.writeString(this.sshUrl);
        dest.writeInt(this.stargazersCount);
        dest.writeString(this.stargazersUrl);
        dest.writeString(this.statusesUrl);
        dest.writeString(this.subscribersUrl);
        dest.writeString(this.subscriptionUrl);
        dest.writeString(this.svnUrl);
        dest.writeString(this.tagsUrl);
        dest.writeString(this.teamsUrl);
        dest.writeString(this.treesUrl);
        dest.writeString(this.updatedAt);
        dest.writeString(this.url);
        dest.writeInt(this.watchers);
        dest.writeInt(this.watchersCount);
    }

    public Item() {
    }

    protected Item(Parcel in) {
        this.archiveUrl = in.readString();
        this.archived = in.readByte() != 0;
        this.assigneesUrl = in.readString();
        this.blobsUrl = in.readString();
        this.branchesUrl = in.readString();
        this.cloneUrl = in.readString();
        this.collaboratorsUrl = in.readString();
        this.commentsUrl = in.readString();
        this.commitsUrl = in.readString();
        this.compareUrl = in.readString();
        this.contentsUrl = in.readString();
        this.contributorsUrl = in.readString();
        this.createdAt = in.readString();
        this.defaultBranch = in.readString();
        this.deploymentsUrl = in.readString();
        this.description = in.readString();
        this.downloadsUrl = in.readString();
        this.eventsUrl = in.readString();
        this.fork = in.readByte() != 0;
        this.forks = in.readInt();
        this.forksCount = in.readInt();
        this.forksUrl = in.readString();
        this.fullName = in.readString();
        this.gitCommitsUrl = in.readString();
        this.gitRefsUrl = in.readString();
        this.gitTagsUrl = in.readString();
        this.gitUrl = in.readString();
        this.hasDownloads = in.readByte() != 0;
        this.hasIssues = in.readByte() != 0;
        this.hasPages = in.readByte() != 0;
        this.hasProjects = in.readByte() != 0;
        this.hasWiki = in.readByte() != 0;
        this.homepage = in.readString();
        this.hooksUrl = in.readString();
        this.htmlUrl = in.readString();
        this.id = in.readInt();
        this.issueCommentUrl = in.readString();
        this.issueEventsUrl = in.readString();
        this.issuesUrl = in.readString();
        this.keysUrl = in.readString();
        this.labelsUrl = in.readString();
        this.language = in.readString();
        this.languagesUrl = in.readString();
        this.mergesUrl = in.readString();
        this.milestonesUrl = in.readString();
        this.mirrorUrl = in.readString();
        this.name = in.readString();
        this.notificationsUrl = in.readString();
        this.openIssues = in.readInt();
        this.openIssuesCount = in.readInt();
        this.owner = in.readParcelable(Owner.class.getClassLoader());
        this.private1 = in.readByte() != 0;
        this.pullsUrl = in.readString();
        this.pushedAt = in.readString();
        this.releasesUrl = in.readString();
        this.size = in.readInt();
        this.sshUrl = in.readString();
        this.stargazersCount = in.readInt();
        this.stargazersUrl = in.readString();
        this.statusesUrl = in.readString();
        this.subscribersUrl = in.readString();
        this.subscriptionUrl = in.readString();
        this.svnUrl = in.readString();
        this.tagsUrl = in.readString();
        this.teamsUrl = in.readString();
        this.treesUrl = in.readString();
        this.updatedAt = in.readString();
        this.url = in.readString();
        this.watchers = in.readInt();
        this.watchersCount = in.readInt();
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
