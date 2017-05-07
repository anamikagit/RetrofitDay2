package com.example.aarya.retrofitday2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuestPojo {
    @SerializedName("topicName")
    @Expose
    private String topicName;

    @SerializedName("questionCount")
    @Expose
    private Integer questionCount=5;

    public QuestPojo(String topicName, Integer questionCount) {
        this.topicName = topicName;
        this.questionCount = questionCount;
    }

    public QuestPojo(String topicName) {
        this.topicName = topicName;
    }


    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Integer getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(Integer questionCount) {
        this.questionCount = questionCount;
    }
}

