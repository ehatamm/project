import { ref, readonly } from 'vue'
import type { Project } from '~/types/project'

export const useProjectManagement = () => {
  const { getAllProjects, deleteProject, createProject, updateProject } = useProjectApi()
  const { showErrorMessage } = useErrorHandler()
  
  // State
  const projects = ref<Project[]>([])
  const loading = ref(false)
  const deletingId = ref<number | null>(null)
  
  // Modal states
  const createModalOpen = ref(false)
  const editModalOpen = ref(false)
  const createLoading = ref(false)
  const editLoading = ref(false)
  const editingProject = ref<Project | null>(null)
  
  
  // Actions
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
  
  const handleDelete = async (item: Project) => {
    if (confirm('Are you sure you want to delete this project?')) {
      deletingId.value = item.id!
      try {
        await deleteProject(item.id!)
        await loadProjects()
      } catch (error) {
        showErrorMessage(error instanceof Error ? error.message : 'Failed to delete project')
        console.error('Failed to delete project:', error)
      } finally {
        deletingId.value = null
      }
    }
  }
  
  const handleCreate = async (project: Project) => {
    createLoading.value = true
    try {
      await createProject(project)
      await loadProjects()
      closeCreateModal()
    } catch (error) {
      showErrorMessage(error instanceof Error ? error.message : 'Failed to create project')
      console.error('Failed to create project:', error)
    } finally {
      createLoading.value = false
    }
  }
  
  const handleEdit = async (project: Project) => {
    if (!editingProject.value?.id) return
    
    editLoading.value = true
    try {
      await updateProject(editingProject.value.id, project)
      await loadProjects()
      closeEditModal()
    } catch (error) {
      showErrorMessage(error instanceof Error ? error.message : 'Failed to update project')
      console.error('Failed to update project:', error)
    } finally {
      editLoading.value = false
    }
  }
  
  // Modal functions
  const openCreateModal = () => {
    createModalOpen.value = true
  }
  
  const closeCreateModal = () => {
    createModalOpen.value = false
  }
  
  const openEditModal = (project: Project) => {
    editingProject.value = { ...project }
    editModalOpen.value = true
  }
  
  const closeEditModal = () => {
    editModalOpen.value = false
    editingProject.value = null
  }
  
  return {
    // State
    projects: readonly(projects),
    loading: readonly(loading),
    deletingId: readonly(deletingId),
    createModalOpen,
    editModalOpen,
    createLoading: readonly(createLoading),
    editLoading: readonly(editLoading),
    editingProject: readonly(editingProject),
    
    // Actions
    loadProjects,
    handleDelete,
    handleCreate,
    handleEdit,
    openCreateModal,
    closeCreateModal,
    openEditModal,
    closeEditModal
  }
}
