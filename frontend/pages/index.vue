<template>
  <div style="padding: 20px; font-family: Arial, sans-serif;">
    <h1>Project Management</h1>
    
    <div style="margin-bottom: 20px;">
      <NuxtLink to="/projects/create" style="padding: 10px 20px; background-color: #007bff; color: white; text-decoration: none; border-radius: 4px; display: inline-block;">
        Create New Project
      </NuxtLink>
    </div>

    <div v-if="projects.length === 0" style="padding: 20px; background-color: #f8f9fa; border-radius: 4px;">
      <p>No projects found. Create your first project!</p>
    </div>

    <div v-else style="display: grid; gap: 20px;">
      <div
        v-for="project in projects"
        :key="project.id"
        style="border: 1px solid #ddd; padding: 20px; border-radius: 8px; background-color: #fff;"
      >
        <h3>{{ project.name }}</h3>
        <p>{{ project.description }}</p>
        <p><strong>Start Date:</strong> {{ project.startDate }}</p>
        <p><strong>End Date:</strong> {{ project.endDate }}</p>
        <div style="margin-top: 10px;">
          <NuxtLink
            :to="`/projects/${project.id}/edit`"
            style="padding: 8px 16px; background-color: #28a745; color: white; text-decoration: none; border-radius: 4px; margin-right: 10px; display: inline-block;"
          >
            Edit
          </NuxtLink>
          <button
            @click="handleDelete(project.id!)"
            style="padding: 8px 16px; background-color: #dc3545; color: white; border: none; border-radius: 4px; cursor: pointer;"
          >
            Delete
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { Project } from '~/types/project'

const { getAllProjects, deleteProject } = useProjectApi()
const projects = ref<Project[]>([])

const loadProjects = async () => {
  try {
    projects.value = await getAllProjects()
  } catch (error) {
    console.error('Failed to load projects:', error)
  }
}

const handleDelete = async (id: number) => {
  if (confirm('Are you sure you want to delete this project?')) {
    try {
      await deleteProject(id)
      await loadProjects()
    } catch (error) {
      console.error('Failed to delete project:', error)
    }
  }
}

onMounted(() => {
  loadProjects()
})
</script>
