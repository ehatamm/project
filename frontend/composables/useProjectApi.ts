import type { Project } from '~/types/project'

export const useProjectApi = () => {
  const config = useRuntimeConfig()
  const apiBase = config.public.apiBase

  const getAllProjects = async (): Promise<Project[]> => {
    const response = await fetch(`${apiBase}/api/projects`)
    return response.json()
  }

  const getProjectById = async (id: number): Promise<Project> => {
    const response = await fetch(`${apiBase}/api/projects/${id}`)
    return response.json()
  }

  const createProject = async (project: Project): Promise<Project> => {
    const response = await fetch(`${apiBase}/api/projects`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(project)
    })
    return response.json()
  }

  const updateProject = async (id: number, project: Project): Promise<Project> => {
    const response = await fetch(`${apiBase}/api/projects/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(project)
    })
    return response.json()
  }

  const deleteProject = async (id: number): Promise<void> => {
    await fetch(`${apiBase}/api/projects/${id}`, {
      method: 'DELETE'
    })
  }

  return {
    getAllProjects,
    getProjectById,
    createProject,
    updateProject,
    deleteProject
  }
}
