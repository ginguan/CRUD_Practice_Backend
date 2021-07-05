package com.example.backend.model;
import java.lang.reflect.Array;
import java.util.Arrays;
import javax.persistence.*;
@Entity
@Table(name = "shows")
public class Show {
  @Id
  // AUTO means Auto increment field
  @GeneratedValue(strategy = GenerationType.AUTO) // used to define generation strategy for the primary key
  private long id;
  @Column(name = "title")
  private String title;
  @Column(name = "description")
  private String description;
  @Column(name = "network")
  private String network;
  @Column(name = "weekday")
  private String[] weekday;
  @Column(name = "status")
  private boolean status;

  public Show() {
  }

  public Show(String title, String description, String network, String[] weekday, boolean status) {
    this.title = title;
    this.description = description;
    this.status = status;
    this.network = network;
    this.weekday = weekday;
  }

  public long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getNetwork() {
    return network;
  }

  public void setNetwork(String network) {
    this.network = network;
  }

  public String[] getWeekday() {
    return weekday;
  }

  public void setWeekday(String[] weekday) {
    this.weekday = weekday;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isStatus() {
    // true --> active
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  @Override
  public String toString() {
    String result ="Show [id = " + id + ", title = " + title + ", " +
            "description = " + description+ ", network = " + network +
            ", weekday = ";
    for(int i =0;i<weekday.length;i++){
      result += weekday[i]+", ";
    }
    result +=", status = " + status +"].";
    return result;
  }
}

