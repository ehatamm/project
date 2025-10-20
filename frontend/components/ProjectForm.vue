<template>
  <form @submit.prevent="handleSubmit" style="max-width: 600px;">
    <div style="margin-bottom: 15px;">
      <label style="display: block; margin-bottom: 5px; font-weight: bold;">Name:</label>
      <input
        v-model="formData.name"
        type="text"
        required
        :class="{ 'error': errors.name }"
        style="width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px;"
      />
      <div v-if="errors.name" style="color: #dc3545; font-size: 14px; margin-top: 4px;">
        {{ errors.name }}
      </div>
    </div>

    <div style="margin-bottom: 15px;">
      <label style="display: block; margin-bottom: 5px; font-weight: bold;">Description:</label>
      <textarea
        v-model="formData.description"
        rows="4"
        :class="{ 'error': errors.description }"
        style="width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px;"
      ></textarea>
      <div v-if="errors.description" style="color: #dc3545; font-size: 14px; margin-top: 4px;">
        {{ errors.description }}
      </div>
    </div>

    <div style="margin-bottom: 15px;">
      <label style="display: block; margin-bottom: 5px; font-weight: bold;">Start Date:</label>
      <input
        v-model="formData.startDate"
        type="date"
        required
        :class="{ 'error': errors.startDate }"
        style="width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px;"
      />
      <div v-if="errors.startDate" style="color: #dc3545; font-size: 14px; margin-top: 4px;">
        {{ errors.startDate }}
      </div>
    </div>

    <div style="margin-bottom: 15px;">
      <label style="display: block; margin-bottom: 5px; font-weight: bold;">End Date:</label>
      <input
        v-model="formData.endDate"
        type="date"
        required
        :class="{ 'error': errors.endDate }"
        style="width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px;"
      />
      <div v-if="errors.endDate" style="color: #dc3545; font-size: 14px; margin-top: 4px;">
        {{ errors.endDate }}
      </div>
    </div>

    <div style="display: flex; gap: 10px;">
      <button
        type="submit"
        :disabled="loading"
        style="padding: 10px 20px; background-color: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer;"
      >
        {{ loading ? submitLoadingText : submitText }}
      </button>
      <NuxtLink
        to="/"
        style="padding: 10px 20px; background-color: #6c757d; color: white; text-decoration: none; border-radius: 4px; display: inline-block;"
      >
        Cancel
      </NuxtLink>
    </div>
  </form>
</template>

<script setup lang="ts">
import { ref, watch } from 'vue'
import type { Project } from '~/types/project'

interface Props {
  initialData?: Project
  submitText: string
  submitLoadingText: string
  loading: boolean
}

interface Emits {
  submit: [data: Project]
}

const props = defineProps<Props>()
const emit = defineEmits<Emits>()

const formData = ref<Project>({
  name: props.initialData?.name || '',
  description: props.initialData?.description || '',
  startDate: props.initialData?.startDate || '',
  endDate: props.initialData?.endDate || ''
})

const errors = ref<Record<string, string>>({})

const validateForm = (): boolean => {
  errors.value = {}
  
  if (!formData.value.name?.trim()) {
    errors.value.name = 'Project name is required'
  } else if (formData.value.name.length < 3) {
    errors.value.name = 'Project name must be at least 3 characters'
  }
  
  if (formData.value.description && formData.value.description.length > 1000) {
    errors.value.description = 'Description must be less than 1000 characters'
  }
  
  if (!formData.value.startDate) {
    errors.value.startDate = 'Start date is required'
  } else {
    const startDate = new Date(formData.value.startDate)
    const today = new Date()
    today.setHours(0, 0, 0, 0)
    
    if (startDate < today) {
      errors.value.startDate = 'Start date must be today or in the future'
    }
  }
  
  if (!formData.value.endDate) {
    errors.value.endDate = 'End date is required'
  } else if (formData.value.startDate && formData.value.endDate) {
    const startDate = new Date(formData.value.startDate)
    const endDate = new Date(formData.value.endDate)
    
    if (endDate <= startDate) {
      errors.value.endDate = 'End date must be after start date'
    }
  }
  
  return Object.keys(errors.value).length === 0
}

const handleSubmit = () => {
  if (validateForm()) {
    emit('submit', formData.value)
  }
}

// Watch for prop changes to update form data
watch(() => props.initialData, (newData: Project | undefined) => {
  if (newData) {
    formData.value = { ...newData }
  }
}, { deep: true })
</script>

<style scoped>
.error {
  border-color: #dc3545 !important;
}
</style>
