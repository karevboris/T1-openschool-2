package com.openschool.hw.starter.dto;

import com.openschool.hw.starter.model.TaskStatus;

import java.util.Objects;

public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private Long userId;
    private TaskStatus status;

    public TaskDto() {
    }

    public TaskDto(Long id, TaskStatus status, Long userId, String description, String title) {
        this.id = id;
        this.status = status;
        this.userId = userId;
        this.description = description;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TaskDto taskDto = (TaskDto) o;
        return Objects.equals(id, taskDto.id) && Objects.equals(title, taskDto.title) && Objects.equals(description, taskDto.description) && Objects.equals(userId, taskDto.userId) && status == taskDto.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, userId, status);
    }
}
