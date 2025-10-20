<template>
  <div style="padding: 20px; font-family: Arial, sans-serif;">
    <h1>Create New Project</h1>
    
    <ProjectForm
      submit-text="Create Project"
      submit-loading-text="Creating..."
      :loading="loading"
      @submit="handleSubmit"
    />
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import type { Project } from '~/types/project'

const { createProject } = useProjectApi()
const { showErrorMessage } = useErrorHandler()
const router = useRouter()

const loading = ref(false)

const handleSubmit = async (project: Project) => {
  loading.value = true
  
  try {
    await createProject(project)
    router.push('/')
  } catch (error) {
    showErrorMessage(error instanceof Error ? error.message : 'Failed to create project')
    console.error('Failed to create project:', error)
  } finally {
    loading.value = false
  }
}
</script>
