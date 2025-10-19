import { ref, readonly } from 'vue'

export const useErrorHandler = () => {
  const errorMessage = ref('')
  const showError = ref(false)

  const showErrorMessage = (message: string) => {
    errorMessage.value = message
    showError.value = true
    
    // Auto-hide after 5 seconds
    setTimeout(() => {
      showError.value = false
    }, 5000)
  }

  const hideError = () => {
    showError.value = false
    errorMessage.value = ''
  }

  return {
    errorMessage: readonly(errorMessage),
    showError: readonly(showError),
    showErrorMessage,
    hideError
  }
}