<template>
  <div style="padding: 20px; font-family: Arial, sans-serif;">
    <h1>Edit Project</h1>
    
    <form v-if="project" @submit.prevent="handleSubmit" style="max-width: 600px;">
      <div style="margin-bottom: 15px;">
        <label style="display: block; margin-bottom: 5px; font-weight: bold;">Name:</label>
        <input
          v-model="project.name"
          type="text"
          required
          style="width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px;"
        />
      </div>

      <div style="margin-bottom: 15px;">
        <label style="display: block; margin-bottom: 5px; font-weight: bold;">Description:</label>
        <textarea
          v-model="project.description"
          rows="4"
          style="width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px;"
        ></textarea>
      </div>

      <div style="margin-bottom: 15px;">
        <label style="display: block; margin-bottom: 5px; font-weight: bold;">Start Date:</label>
        <input
          v-model="project.startDate"
          type="date"
          required
          style="width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px;"
        />
      </div>

      <div style="margin-bottom: 15px;">
        <label style="display: block; margin-bottom: 5px; font-weight: bold;">End Date:</label>
        <input
          v-model="project.endDate"
          type="date"
          required
          style="width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px;"
        />
      </div>

      <div style="display: flex; gap: 10px;">
        <button
          type="submit"
          :disabled="loading"
          style="padding: 10px 20px; background-color: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer;"
        >
          {{ loading ? 'Updating...' : 'Update Project' }}
        </button>
        <NuxtLink
          to="/"
          style="padding: 10px 20px; background-color: #6c757d; color: white; text-decoration: none; border-radius: 4px; display: inline-block;"
        >
          Cancel
        </NuxtLink>
      </div>
    </form>

    <div v-else style="padding: 20px; background-color: #f8f9fa; border-radius: 4px;">
      <p>Loading...</p>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { Project } from '~/types/project'

const route = useRoute()
const router = useRouter()
const { getProjectById, updateProject } = useProjectApi()
const { showErrorMessage } = inject('errorHandler', { showErrorMessage: () => {} })

const project = ref<Project | null>(null)
const loading = ref(false)

const loadProject = async () => {
  try {
    const id = Number(route.params.id)
    project.value = await getProjectById(id)
  } catch (error) {
    showErrorMessage(error instanceof Error ? error.message : 'Failed to load project')
    console.error('Failed to load project:', error)
  }
}

const handleSubmit = async () => {
  if (!project.value) return
  
  loading.value = true
  try {
    const id = Number(route.params.id)
    await updateProject(id, project.value)
    router.push('/')
  } catch (error) {
    showErrorMessage(error instanceof Error ? error.message : 'Failed to update project')
    console.error('Failed to update project:', error)
  } finally {
    loading.value = false
  }
}

onMounted(() => {
  loadProject()
})
</script>
