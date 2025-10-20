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

    <div v-else>
      <table style="width: 100%; border-collapse: collapse; border: 1px solid #ddd;">
        <thead>
          <tr style="background-color: #f8f9fa;">
            <th style="padding: 12px; text-align: left; border-bottom: 1px solid #ddd;">Name</th>
            <th style="padding: 12px; text-align: left; border-bottom: 1px solid #ddd;">Description</th>
            <th style="padding: 12px; text-align: left; border-bottom: 1px solid #ddd;">Start Date</th>
            <th style="padding: 12px; text-align: left; border-bottom: 1px solid #ddd;">End Date</th>
            <th style="padding: 12px; text-align: center; border-bottom: 1px solid #ddd;">Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="project in projects"
            :key="project.id"
            style="border-bottom: 1px solid #eee;"
          >
            <td style="padding: 12px; border-bottom: 1px solid #eee;">{{ project.name }}</td>
            <td style="padding: 12px; border-bottom: 1px solid #eee;">{{ project.description }}</td>
            <td style="padding: 12px; border-bottom: 1px solid #eee;">{{ project.startDate }}</td>
            <td style="padding: 12px; border-bottom: 1px solid #eee;">{{ project.endDate }}</td>
            <td style="padding: 12px; border-bottom: 1px solid #eee; text-align: center;">
              <NuxtLink
                :to="`/projects/${project.id}/edit`"
                style="padding: 6px 12px; background-color: #28a745; color: white; text-decoration: none; border-radius: 4px; margin-right: 8px; display: inline-block; font-size: 14px;"
              >
                Edit
              </NuxtLink>
              <button
                @click="handleDelete(project.id!)"
                :disabled="deletingId === project.id"
                style="padding: 6px 12px; background-color: #dc3545; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 14px;"
              >
                {{ deletingId === project.id ? 'Deleting...' : 'Delete' }}
              </button>
            </td>
          </tr>
        </tbody>
      </table>
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

