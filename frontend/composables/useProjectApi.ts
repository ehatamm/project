import type { Project } from '~/types/project'

export const useProjectApi = () => {
  const config = useRuntimeConfig()
  const apiBase = config.public.apiBase

  const handleResponse = async (response: Response) => {
    if (!response.ok) {
      const errorText = await response.text()
      throw new Error(`API Error ${response.status}: ${errorText}`)
    }
    return response.json()
  }

  const getAllProjects = async (): Promise<Project[]> => {
    const response = await fetch(`${apiBase}/api/projects`)
    return handleResponse(response)
  }

  const getProjectById = async (id: number): Promise<Project> => {
    const response = await fetch(`${apiBase}/api/projects/${id}`)
    return handleResponse(response)
  }

  const createProject = async (project: Project): Promise<Project> => {
    const response = await fetch(`${apiBase}/api/projects`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(project)
    })
    return handleResponse(response)
  }

  const updateProject = async (id: number, project: Project): Promise<Project> => {
    const response = await fetch(`${apiBase}/api/projects/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(project)
    })
    return handleResponse(response)
  }

  const deleteProject = async (id: number): Promise<void> => {
    const response = await fetch(`${apiBase}/api/projects/${id}`, {
      method: 'DELETE'
    })
    if (!response.ok) {
      const errorText = await response.text()
      throw new Error(`API Error ${response.status}: ${errorText}`)
    }
  }

  return {
    getAllProjects,
    getProjectById,
    createProject,
    updateProject,
    deleteProject
  }
}
