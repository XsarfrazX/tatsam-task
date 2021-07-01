package com.sarfraz.priority.entity;


import com.sarfraz.priority.model.AreaPriority;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "UserPriorityCollection")
public class UserPriority {

    @Id
    String userName;

    @Field
    List<AreaPriority> areaPriorityList;

    public UserPriority(String userName, List<AreaPriority> areaPriorityList) {
        this.userName = userName;
        this.areaPriorityList = areaPriorityList;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<AreaPriority> getAreaPriorityList() {
        return areaPriorityList;
    }

    public void setAreaPriorityList(List<AreaPriority> areaPriorityList) {
        this.areaPriorityList = areaPriorityList;
    }

    @Override
    public String toString() {
        return "UserPriority{" +
                "userName='" + userName + '\'' +
                ", areaPriorityList=" + areaPriorityList +
                '}';
    }
}
