package com.example.backend.model;

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

  @Column(name = "status")
  private boolean status;

  public Show() {
  }

  public Show(String title, String description, boolean status) {
    this.title = title;
    this.description = description;
    this.status = status;
  }

  public long getId() {
    return id;
  }

  public String getTitle() {
    return title;
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
    return "Show [id = " + id + ", title = " + title + ", description = " + description + ", status = " + status + "].";

  }
}

