package com.example.project.mapper;

import com.example.project.dto.ProjectCreateDto;
import com.example.project.dto.ProjectDto;
import com.example.project.dto.ProjectUpdateDto;
import com.example.project.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    
    ProjectDto projectToProjectDto(Project project);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Project projectCreateDtoToProject(ProjectCreateDto dto);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void projectUpdateDtoToProject(ProjectUpdateDto dto, @MappingTarget Project project);
}
