<template>
  <v-app>
    <v-app-bar
      color="primary"
      elevation="4"
    >
      <v-app-bar-title class="text-h5 font-weight-bold">
        <v-icon class="mr-2" color="on-primary">mdi-folder-multiple</v-icon>
        Project Management
      </v-app-bar-title>
      <v-spacer></v-spacer>
      
      <!-- Theme Toggle -->
      <v-tooltip text="Toggle Theme">
        <template #activator="{ props }">
          <v-btn
            v-bind="props"
            icon="mdi-theme-light-dark"
            variant="text"
            color="primary"
            class="mr-2"
          ></v-btn>
        </template>
      </v-tooltip>
      
      <!-- Create Project Button -->
      <v-btn
        color="primary"
        variant="elevated"
        prepend-icon="mdi-plus"
        @click="openCreateModal"
        class="font-weight-bold"
        elevation="2"
      >
        New Project
      </v-btn>
    </v-app-bar>

    <v-main>
      <v-container fluid class="pa-6">
        <v-row>
          <v-col cols="12">
            <v-card
              elevation="8"
              rounded="xl"
              class="pa-6"
            >
              <v-card-title class="text-h4 mb-4 text-primary font-weight-bold">
                <v-icon class="mr-3" size="large" color="primary">mdi-view-dashboard</v-icon>
                Project Dashboard
                <v-chip class="ml-3" color="primary" size="small">
                  Light Mode
                </v-chip>
              </v-card-title>
              
              <v-card-subtitle class="text-h6 mb-6 on-surface">
                Manage and track your projects efficiently
              </v-card-subtitle>

              <ProjectTable
                :headers="headers"
                :items="projects"
                :loading="loading"
                empty-message="No projects found. Create your first project to get started!"
              >
                <template #item.actions="{ item }">
                  <TableActions
                    :item="item"
                    :is-deleting="deletingId === item.id"
                    @edit="openEditModal"
                    @delete="handleDelete"
                  />
                </template>
              </ProjectTable>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-main>

    <!-- Create Project Modal -->
    <v-dialog v-model="createModalOpen" max-width="700px" persistent>
      <v-card elevation="12" rounded="xl">
        <v-card-title class="text-h4 pa-6 pb-2 text-primary font-weight-bold">
          <v-icon class="mr-3" size="large" color="primary">mdi-plus-circle</v-icon>
          Create New Project
        </v-card-title>
        <v-card-subtitle class="text-h6 pa-6 pt-0 on-surface">
          Fill in the details to create a new project
        </v-card-subtitle>
        <v-card-text class="pa-6 pt-2">
          <ProjectFormVuetify
            submit-text="Create Project"
            submit-loading-text="Creating..."
            :loading="createLoading"
            @submit="handleCreate"
            @cancel="closeCreateModal"
          />
        </v-card-text>
      </v-card>
    </v-dialog>

    <!-- Edit Project Modal -->
    <v-dialog v-model="editModalOpen" max-width="700px" persistent>
      <v-card elevation="12" rounded="xl">
        <v-card-title class="text-h4 pa-6 pb-2 text-primary font-weight-bold">
          <v-icon class="mr-3" size="large" color="primary">mdi-pencil-circle</v-icon>
          Edit Project
        </v-card-title>
        <v-card-subtitle class="text-h6 pa-6 pt-0 on-surface">
          Update the project details
        </v-card-subtitle>
        <v-card-text class="pa-6 pt-2">
          <ProjectFormVuetify
            :initial-data="editingProject"
            submit-text="Update Project"
            submit-loading-text="Updating..."
            :loading="editLoading"
            @submit="handleEdit"
            @cancel="closeEditModal"
          />
        </v-card-text>
      </v-card>
    </v-dialog>
  </v-app>
</template>

<script setup lang="ts">
import { computed, onMounted } from 'vue'
import type { Project } from '~/types/project'

const {
  projects,
  loading,
  deletingId,
  createModalOpen,
  editModalOpen,
  createLoading,
  editLoading,
  editingProject,
  loadProjects,
  handleDelete,
  handleCreate,
  handleEdit,
  openCreateModal,
  closeCreateModal,
  openEditModal,
  closeEditModal
} = useProjectManagement()

const headers = [
  { title: 'Name', key: 'name' },
  { title: 'Description', key: 'description' },
  { title: 'Start Date', key: 'startDate' },
  { title: 'End Date', key: 'endDate' },
  { title: 'Actions', key: 'actions', sortable: false }
]

onMounted(() => {
  loadProjects()
})
</script>

