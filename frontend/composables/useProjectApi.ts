import type { Project } from '~/types/project'

interface ApiError {
  type: string
  title: string
  status: number
  detail: string
  fieldErrors?: Record<string, string>
}

export const useProjectApi = () => {
  const config = useRuntimeConfig()
  const apiBase = config.public.apiBase

  const handleApiResponse = async (response: Response) => {
    if (!response.ok) {
      let errorMessage = `API Error ${response.status}`
      
      try {
        const errorData: ApiError = await response.json()
        errorMessage = errorData.detail || errorData.title || errorMessage
        
        if (errorData.fieldErrors) {
          const fieldErrors = Object.entries(errorData.fieldErrors)
            .map(([field, message]) => `${field}: ${message}`)
            .join(', ')
          errorMessage += ` (${fieldErrors})`
        }
      } catch {
        // If we can't parse the error, use the status text
        errorMessage = response.statusText || errorMessage
      }
      
      throw new Error(errorMessage)
    }
    
    return response.json()
  }

  const getAllProjects = async (): Promise<Project[]> => {
    const response = await fetch(`${apiBase}/api/projects`)
    return handleApiResponse(response)
  }

  const getProjectById = async (id: number): Promise<Project> => {
    const response = await fetch(`${apiBase}/api/projects/${id}`)
    return handleApiResponse(response)
  }

  const createProject = async (project: Project): Promise<Project> => {
    const response = await fetch(`${apiBase}/api/projects`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(project)
    })
    return handleApiResponse(response)
  }

  const updateProject = async (id: number, project: Project): Promise<Project> => {
    const response = await fetch(`${apiBase}/api/projects/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(project)
    })
    return handleApiResponse(response)
  }

  const deleteProject = async (id: number): Promise<void> => {
    const response = await fetch(`${apiBase}/api/projects/${id}`, {
      method: 'DELETE'
    })
    
    if (!response.ok) {
      let errorMessage = `API Error ${response.status}`
      
      try {
        const errorData: ApiError = await response.json()
        errorMessage = errorData.detail || errorData.title || errorMessage
        
        if (errorData.fieldErrors) {
          const fieldErrors = Object.entries(errorData.fieldErrors)
            .map(([field, message]) => `${field}: ${message}`)
            .join(', ')
          errorMessage += ` (${fieldErrors})`
        }
      } catch {
        // If we can't parse the error, use the status text
        errorMessage = response.statusText || errorMessage
      }
      
      throw new Error(errorMessage)
    }
    
    return
  }

  return {
    getAllProjects,
    getProjectById,
    createProject,
    updateProject,
    deleteProject
  }
}