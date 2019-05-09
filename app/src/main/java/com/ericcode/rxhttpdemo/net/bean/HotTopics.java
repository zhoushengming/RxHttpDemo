package com.ericcode.rxhttpdemo.net.bean;

public class HotTopics {

    private NodeBean node;
    private MemberBean member;
    private String last_reply_by;
    private int last_touched;
    private String title;
    private String url;
    private long created;
    private String content;
    private String content_rendered;
    private long last_modified;
    private int replies;
    private int id;

    public NodeBean getNode() {
        return node;
    }

    public void setNode(NodeBean node) {
        this.node = node;
    }

    public MemberBean getMember() {
        return member;
    }

    public void setMember(MemberBean member) {
        this.member = member;
    }

    public String getLast_reply_by() {
        return last_reply_by;
    }

    public void setLast_reply_by(String last_reply_by) {
        this.last_reply_by = last_reply_by;
    }

    public int getLast_touched() {
        return last_touched;
    }

    public void setLast_touched(int last_touched) {
        this.last_touched = last_touched;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent_rendered() {
        return content_rendered;
    }

    public void setContent_rendered(String content_rendered) {
        this.content_rendered = content_rendered;
    }

    public long getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(long last_modified) {
        this.last_modified = last_modified;
    }

    public int getReplies() {
        return replies;
    }

    public void setReplies(int replies) {
        this.replies = replies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static class NodeBean {
        /**
         * avatar_large : //cdn.v2ex.com/navatar/4ea0/6fbc/770_large.png?m=1553247082
         * name : career
         * avatar_normal : //cdn.v2ex.com/navatar/4ea0/6fbc/770_normal.png?m=1553247082
         * title : 职场话题
         * url : https://www.v2ex.com/go/career
         * topics : 6288
         * footer :
         * header : 这里，我们聊聊那些工作中遇到的开心和不开心的事。
         * title_alternative : Career
         * avatar_mini : //cdn.v2ex.com/navatar/4ea0/6fbc/770_mini.png?m=1553247082
         * stars : 1205
         * root : false
         * id : 770
         * parent_node_name : work
         */

        private String avatar_large;
        private String name;
        private String avatar_normal;
        private String title;
        private String url;
        private int topics;
        private String footer;
        private String header;
        private String title_alternative;
        private String avatar_mini;
        private int stars;
        private boolean root;
        private int id;
        private String parent_node_name;

        public String getAvatar_large() {
            return avatar_large;
        }

        public void setAvatar_large(String avatar_large) {
            this.avatar_large = avatar_large;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar_normal() {
            return avatar_normal;
        }

        public void setAvatar_normal(String avatar_normal) {
            this.avatar_normal = avatar_normal;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getTopics() {
            return topics;
        }

        public void setTopics(int topics) {
            this.topics = topics;
        }

        public String getFooter() {
            return footer;
        }

        public void setFooter(String footer) {
            this.footer = footer;
        }

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public String getTitle_alternative() {
            return title_alternative;
        }

        public void setTitle_alternative(String title_alternative) {
            this.title_alternative = title_alternative;
        }

        public String getAvatar_mini() {
            return avatar_mini;
        }

        public void setAvatar_mini(String avatar_mini) {
            this.avatar_mini = avatar_mini;
        }

        public int getStars() {
            return stars;
        }

        public void setStars(int stars) {
            this.stars = stars;
        }

        public boolean isRoot() {
            return root;
        }

        public void setRoot(boolean root) {
            this.root = root;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getParent_node_name() {
            return parent_node_name;
        }

        public void setParent_node_name(String parent_node_name) {
            this.parent_node_name = parent_node_name;
        }
    }

    public static class MemberBean {
        /**
         * username : iamJack
         * website :
         * github :
         * psn :
         * avatar_normal : //cdn.v2ex.com/gravatar/794a744a2b02b2d72197bb4056187e13?s=24&d=retro
         * bio :
         * url : https://www.v2ex.com/u/iamJack
         * tagline :
         * twitter :
         * created : 1530761390
         * avatar_large : //cdn.v2ex.com/gravatar/794a744a2b02b2d72197bb4056187e13?s=24&d=retro
         * avatar_mini : //cdn.v2ex.com/gravatar/794a744a2b02b2d72197bb4056187e13?s=24&d=retro
         * location :
         * btc :
         * id : 327008
         */

        private String username;
        private String website;
        private String github;
        private String psn;
        private String avatar_normal;
        private String bio;
        private String url;
        private String tagline;
        private String twitter;
        private int created;
        private String avatar_large;
        private String avatar_mini;
        private String location;
        private String btc;
        private int id;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getGithub() {
            return github;
        }

        public void setGithub(String github) {
            this.github = github;
        }

        public String getPsn() {
            return psn;
        }

        public void setPsn(String psn) {
            this.psn = psn;
        }

        public String getAvatar_normal() {
            return avatar_normal;
        }

        public void setAvatar_normal(String avatar_normal) {
            this.avatar_normal = avatar_normal;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTagline() {
            return tagline;
        }

        public void setTagline(String tagline) {
            this.tagline = tagline;
        }

        public String getTwitter() {
            return twitter;
        }

        public void setTwitter(String twitter) {
            this.twitter = twitter;
        }

        public int getCreated() {
            return created;
        }

        public void setCreated(int created) {
            this.created = created;
        }

        public String getAvatar_large() {
            return avatar_large;
        }

        public void setAvatar_large(String avatar_large) {
            this.avatar_large = avatar_large;
        }

        public String getAvatar_mini() {
            return avatar_mini;
        }

        public void setAvatar_mini(String avatar_mini) {
            this.avatar_mini = avatar_mini;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getBtc() {
            return btc;
        }

        public void setBtc(String btc) {
            this.btc = btc;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
