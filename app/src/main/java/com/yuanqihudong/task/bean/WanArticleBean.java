package com.yuanqihudong.task.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class WanArticleBean {


    @SerializedName("data")
    private DataDTO data;
    @SerializedName("errorCode")
    private int errorCode;
    @SerializedName("errorMsg")
    private String errorMsg;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @SerializedName("curPage")
        private int curPage;
        @SerializedName("datas")
        private List<DatasDTO> datas;
        @SerializedName("offset")
        private int offset;
        @SerializedName("over")
        private boolean over;
        @SerializedName("pageCount")
        private int pageCount;
        @SerializedName("size")
        private int size;
        @SerializedName("total")
        private int total;

        @NoArgsConstructor
        @Data
        public static class DatasDTO {
            @SerializedName("adminAdd")
            private boolean adminAdd;
            @SerializedName("apkLink")
            private String apkLink;
            @SerializedName("audit")
            private int audit;
            @SerializedName("author")
            private String author;
            @SerializedName("canEdit")
            private boolean canEdit;
            @SerializedName("chapterId")
            private int chapterId;
            @SerializedName("chapterName")
            private String chapterName;
            @SerializedName("collect")
            private boolean collect;
            @SerializedName("courseId")
            private int courseId;
            @SerializedName("desc")
            private String desc;
            @SerializedName("descMd")
            private String descMd;
            @SerializedName("envelopePic")
            private String envelopePic;
            @SerializedName("fresh")
            private boolean fresh;
            @SerializedName("host")
            private String host;
            @SerializedName("id")
            private int id;
            @SerializedName("isAdminAdd")
            private boolean isAdminAdd;
            @SerializedName("link")
            private String link;
            @SerializedName("niceDate")
            private String niceDate;
            @SerializedName("niceShareDate")
            private String niceShareDate;
            @SerializedName("origin")
            private String origin;
            @SerializedName("prefix")
            private String prefix;
            @SerializedName("projectLink")
            private String projectLink;
            @SerializedName("publishTime")
            private long publishTime;
            @SerializedName("realSuperChapterId")
            private int realSuperChapterId;
            @SerializedName("selfVisible")
            private int selfVisible;
            @SerializedName("shareDate")
            private long shareDate;
            @SerializedName("shareUser")
            private String shareUser;
            @SerializedName("superChapterId")
            private int superChapterId;
            @SerializedName("superChapterName")
            private String superChapterName;
            @SerializedName("tags")
            private List<TagsDTO> tags;
            @SerializedName("title")
            private String title;
            @SerializedName("type")
            private int type;
            @SerializedName("userId")
            private int userId;
            @SerializedName("visible")
            private int visible;
            @SerializedName("zan")
            private int zan;

            @NoArgsConstructor
            @Data
            public static class TagsDTO {
                @SerializedName("name")
                private String name;
                @SerializedName("url")
                private String url;
            }

            @Override
            public String toString() {
                return "DatasDTO{" +
                        "adminAdd=" + adminAdd +
                        ", apkLink='" + apkLink + '\'' +
                        ", audit=" + audit +
                        ", author='" + author + '\'' +
                        ", canEdit=" + canEdit +
                        ", chapterId=" + chapterId +
                        ", chapterName='" + chapterName + '\'' +
                        ", collect=" + collect +
                        ", courseId=" + courseId +
                        ", desc='" + desc + '\'' +
                        ", descMd='" + descMd + '\'' +
                        ", envelopePic='" + envelopePic + '\'' +
                        ", fresh=" + fresh +
                        ", host='" + host + '\'' +
                        ", id=" + id +
                        ", isAdminAdd=" + isAdminAdd +
                        ", link='" + link + '\'' +
                        ", niceDate='" + niceDate + '\'' +
                        ", niceShareDate='" + niceShareDate + '\'' +
                        ", origin='" + origin + '\'' +
                        ", prefix='" + prefix + '\'' +
                        ", projectLink='" + projectLink + '\'' +
                        ", publishTime=" + publishTime +
                        ", realSuperChapterId=" + realSuperChapterId +
                        ", selfVisible=" + selfVisible +
                        ", shareDate=" + shareDate +
                        ", shareUser='" + shareUser + '\'' +
                        ", superChapterId=" + superChapterId +
                        ", superChapterName='" + superChapterName + '\'' +
                        ", tags=" + tags +
                        ", title='" + title + '\'' +
                        ", type=" + type +
                        ", userId=" + userId +
                        ", visible=" + visible +
                        ", zan=" + zan +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataDTO{" +
                    "curPage=" + curPage +
                    ", datas=" + datas +
                    ", offset=" + offset +
                    ", over=" + over +
                    ", pageCount=" + pageCount +
                    ", size=" + size +
                    ", total=" + total +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "WanArticleBean{" +
                "data=" + data +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
