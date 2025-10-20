<template>
  <div style="padding: 20px; font-family: Arial, sans-serif;">
    <h1>Edit Project</h1>
    
    <div v-if="loading" style="padding: 20px; background-color: #f8f9fa; border-radius: 4px;">
      <p>Loading...</p>
    </div>
    
    <ProjectForm
      v-else-if="project"
      :initial-data="project"
      submit-text="Update Project"
      submit-loading-text="Updating..."
      :loading="submitting"
      @submit="handleSubmit"
    />
    
    <div v-else style="padding: 20px; background-color: #f8f9fa; border-radius: 4px;">
      <p>Project not found</p>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import type { Project } from '~/types/project'

const route = useRoute()
const router = useRouter()
const { getProjectById, updateProject } = useProjectApi()
const { showErrorMessage } = useErrorHandler()

const project = ref<Project | null>(null)
const loading = ref(false)
const submitting = ref(false)

const loadProject = async () => {
  loading.value = true
  try {
    const id = Number(route.params.id)
    project.value = await getProjectById(id)
  } catch (error) {
    showErrorMessage(error instanceof Error ? error.message : 'Failed to load project')
    console.error('Failed to load project:', error)
  } finally {
    loading.value = false
  }
}

const handleSubmit = async (projectData: Project) => {
  if (!project.value) return
  
  submitting.value = true
  try {
    const id = Number(route.params.id)
    await updateProject(id, projectData)
    router.push('/')
  } catch (error) {
    showErrorMessage(error instanceof Error ? error.message : 'Failed to update project')
    console.error('Failed to update project:', error)
  } finally {
    submitting.value = false
  }
}

onMounted(() => {
  loadProject()
})
</script>
