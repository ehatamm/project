<template>
  <div style="padding: 20px; font-family: Arial, sans-serif;">
    <h1>Project Management</h1>
    
    <div style="margin-bottom: 20px;">
      <NuxtLink to="/projects/create" style="padding: 10px 20px; background-color: #007bff; color: white; text-decoration: none; border-radius: 4px; display: inline-block;">
        Create New Project
      </NuxtLink>
    </div>

    <div v-if="loading" style="padding: 20px; text-align: center;">
      <p>Loading projects...</p>
    </div>

    <div v-else-if="projects.length === 0" style="padding: 20px; background-color: #f8f9fa; border-radius: 4px;">
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
            :disabled="deletingId === project.id"
            style="padding: 8px 16px; background-color: #dc3545; color: white; border: none; border-radius: 4px; cursor: pointer;"
          >
            {{ deletingId === project.id ? 'Deleting...' : 'Delete' }}
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { Project } from '~/types/project'

const { getAllProjects, deleteProject } = useProjectApi()
const { showErrorMessage } = useErrorHandler()
const projects = ref<Project[]>([])
const loading = ref(false)
const deletingId = ref<number | null>(null)

const loadProjects = async () => {
  loading.value = true
  try {
    projects.value = await getAllProjects()
  } catch (error) {
    showErrorMessage(error instanceof Error ? error.message : 'Failed to load projects')
    console.error('Failed to load projects:', error)
  } finally {
    loading.value = false
  }
}

const handleDelete = async (id: number) => {
  if (confirm('Are you sure you want to delete this project?')) {
    deletingId.value = id
    try {
      await deleteProject(id)
      await loadProjects()
    } catch (error) {
      showErrorMessage(error instanceof Error ? error.message : 'Failed to delete project')
      console.error('Failed to delete project:', error)
    } finally {
      deletingId.value = null
    }
  }
}

onMounted(() => {
  loadProjects()
})
</script>

