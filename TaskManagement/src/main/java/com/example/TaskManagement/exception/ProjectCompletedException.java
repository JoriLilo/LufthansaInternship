package com.example.TaskManagement.exception;

public class ProjectCompletedException extends RuntimeException {
  public ProjectCompletedException(String message) {
    super(message);
  }
}
