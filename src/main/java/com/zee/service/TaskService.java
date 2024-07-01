package com.zee.service;

import com.zee.dto.ProjectDTO;
import com.zee.dto.TaskDTO;
import com.zee.dto.UserDTO;
import com.zee.enums.Status;

import java.util.List;

public interface TaskService {

    TaskDTO findById(Long id);
    List<TaskDTO> listAllTasks();

    void save(TaskDTO dto);
    void update(TaskDTO dto);
    void delete(Long id);

    int totalNonCompletedTask(String projectCode);
    int totalCompletedTask(String projectCode);

    void deleteByProject(ProjectDTO projectDTO);

    void completeByProject(ProjectDTO projectDTO);

    List<TaskDTO> listAllTasksByStatusIsNot(Status status);
    List<TaskDTO> listAllTasksByStatus(Status status);

    List<TaskDTO> listAllNonCompletedByAssignedEmployee(UserDTO assignedEmployee);

}
