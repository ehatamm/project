<template>
  <v-form @submit.prevent="handleSubmit">
    <v-text-field
      v-model="formData.name"
      label="Project Name"
      :error-messages="errors.name"
      required
      variant="outlined"
      class="mb-4"
      prepend-inner-icon="mdi-folder-text"
      color="primary"
    ></v-text-field>

    <v-textarea
      v-model="formData.description"
      label="Description"
      :error-messages="errors.description"
      variant="outlined"
      rows="4"
      class="mb-4"
      prepend-inner-icon="mdi-text"
      color="primary"
      auto-grow
    ></v-textarea>

    <v-row>
      <v-col cols="6">
        <v-text-field
          v-model="formData.startDate"
          label="Start Date"
          :error-messages="errors.startDate"
          type="date"
          required
          variant="outlined"
          class="mb-4"
          prepend-inner-icon="mdi-calendar-start"
          color="primary"
        ></v-text-field>
      </v-col>
      <v-col cols="6">
        <v-text-field
          v-model="formData.endDate"
          label="End Date"
          :error-messages="errors.endDate"
          type="date"
          required
          variant="outlined"
          class="mb-4"
          prepend-inner-icon="mdi-calendar-end"
          color="primary"
        ></v-text-field>
      </v-col>
    </v-row>

    <v-divider class="my-6"></v-divider>

    <div class="d-flex justify-end ga-3">
      <v-btn
        variant="outlined"
        color="secondary"
        size="large"
        @click="handleCancel"
        class="px-8"
      >
        Cancel
      </v-btn>
      <v-btn
        type="submit"
        color="primary"
        size="large"
        :loading="loading"
        :disabled="loading"
        class="px-8"
        elevation="2"
      >
        {{ loading ? submitLoadingText : submitText }}
      </v-btn>
    </div>
  </v-form>
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
  cancel: []
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

const handleCancel = () => {
  emit('cancel')
}

// Watch for prop changes to update form data
watch(() => props.initialData, (newData: Project | undefined) => {
  if (newData) {
    formData.value = { ...newData }
  }
}, { deep: true })
</script>
