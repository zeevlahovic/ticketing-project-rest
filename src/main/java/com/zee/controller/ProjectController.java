package com.zee.controller;

import com.zee.dto.ProjectDTO;
import com.zee.dto.ResponseWrapper;
import com.zee.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    public final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    @RolesAllowed({"Manager"})
    public ResponseEntity<ResponseWrapper> getProjects() {
        List<ProjectDTO> projectDTOList = projectService.listAllProjects();
        return ResponseEntity.ok(new ResponseWrapper("projects are successfully retrieved", projectDTOList, HttpStatus.OK));
    }

    @GetMapping("/{code}")
    @RolesAllowed({"Manager"})
    public ResponseEntity<ResponseWrapper> getProjectByCode(@PathVariable("code") String code) {
        ProjectDTO projectDTO = projectService.getByProjectCode(code);
        return ResponseEntity.ok(new ResponseWrapper("project is successfully retrieved", projectDTO, HttpStatus.OK));

    }

    @PostMapping
    @RolesAllowed({"Manager","Admin"})
    public ResponseEntity<ResponseWrapper> createProject(@RequestBody ProjectDTO project) {
        projectService.save(project);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseWrapper("project is successfully created", HttpStatus.CREATED));
    }

    @PutMapping
    @RolesAllowed({"Manager"})
    public ResponseEntity<ResponseWrapper> updateProject(@RequestBody ProjectDTO project) {
        projectService.update(project);
        return ResponseEntity.ok(new ResponseWrapper("project is successfully updated", HttpStatus.OK));
    }

    @DeleteMapping("/{projectcode}")
    @RolesAllowed({"Manager"})
    public ResponseEntity<ResponseWrapper> deleteProject(@PathVariable("projectcode") String projectcode) {
        projectService.delete(projectcode);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseWrapper("project is successfully deleted", HttpStatus.OK));
    }

    @GetMapping("/manager/project-status")
    @RolesAllowed({"Manager","Admin"})
    public ResponseEntity<ResponseWrapper> getProjectByManager() {
        List<ProjectDTO> projectDTOList = projectService.listAllProjectDetails();
        return ResponseEntity.ok(new ResponseWrapper("projects are successfully retrieved", projectDTOList, HttpStatus.OK));
    }

    @PutMapping("/manager/complete/{projectCode}")
    @RolesAllowed({"Manager"})
    public ResponseEntity<ResponseWrapper> managerCompleteProject(@PathVariable("projectCode") String projectCode) {
        projectService.complete(projectCode);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseWrapper("project is successfully completed", HttpStatus.OK));
    }
}
