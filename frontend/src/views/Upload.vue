
<template>
  <div class="min-h-screen bg-white px-[42px] py-[38px]">
    <!-- CABEÇALHO -->
    <div>
      <h1 class="text-[20px] font-semibold tracking-tight text-[#0F172A]">
        Processamento de Dados
      </h1>

      <p class="mt-[6px] font-mono text-[12px] tracking-wide text-[#2563EB]">
        &gt;_ SELECIONE ARQUIVOS .XLSX OU .CSV PARA NORMALIZAÇÃO
      </p>
    </div>

    <!-- ÁREA DE UPLOAD -->
    <div
      class="mt-[35px] flex min-h-[250px] flex-col items-center justify-center rounded-[8px] border border-dashed border-[#0EA5E9]/40 bg-[#0EA5E9]/5 px-6 transition hover:border-[#14B8A6]"
      @dragover.prevent
      @drop.prevent="handleDrop"
    >
      <!-- ÍCONE -->
      <div class="flex h-[48px] w-[48px] items-center justify-center rounded-full bg-[#2563EB]/10">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          class="h-6 w-6 text-[#2563EB]"
          fill="none"
          viewBox="0 0 24 24"
          stroke="currentColor"
          stroke-width="1.7"
        >
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            d="M12 16V4m0 0L7 9m5-5l5 5"
          />
          <path
            stroke-linecap="round"
            stroke-linejoin="round"
            d="M5 20h14"
          />
        </svg>
      </div>

      <p class="mt-[16px] text-[15px] font-medium text-[#0F172A]">
        Arraste sua planilha para iniciar o ETL
      </p>

      <p class="mt-[5px] text-[12px] text-slate-500">
        Motor de processamento via Python preparado.
      </p>

      <!-- INPUT ESCONDIDO -->
      <input
        ref="fileInput"
        type="file"
        accept=".xlsx,.csv"
        class="hidden"
        @change="handleFile"
      />

      <!-- BOTÃO DE UPLOAD -->
      <button
        type="button"
        class="mt-[20px] rounded-[5px] bg-[#2563EB] px-[18px] py-[9px] text-[12px] font-medium text-white transition hover:bg-[#0EA5E9] active:scale-[0.99] disabled:cursor-not-allowed disabled:opacity-50"
        :disabled="processing"
        @click="openFile"
      >
        {{ processing ? 'Processando...' : 'Selecionar Arquivo' }}
      </button>

      <p class="mt-[10px] text-[10px] uppercase tracking-wide text-slate-500">
        .XLSX · .CSV · MÁX. 20MB
      </p>
    </div>

    <!-- ERRO -->
    <div
      v-if="uploadStore.error"
      class="mt-[15px] rounded-[5px] border border-red-200 bg-red-50 px-[14px] py-[10px]"
    >
      <p class="text-[11px] font-medium text-red-600">
        {{ uploadStore.error }}
      </p>
    </div>

    <!-- ARQUIVO SELECIONADO -->
    <div
      v-if="uploadStore.selectedFile"
      class="mt-[15px] flex items-center justify-between rounded-[6px] border border-[#14B8A6]/40 bg-white px-[15px] py-[12px]"
    >
      <div>
        <p class="text-[11px] font-medium text-[#0F172A]">
          {{ uploadStore.selectedFile.name }}
        </p>

        <p class="mt-[3px] text-[9px] text-slate-500">
          {{ formatFileSize(uploadStore.selectedFile.size) }}
        </p>
      </div>

      <span
        v-if="processing"
        class="rounded-[4px] bg-[#0EA5E9]/10 px-[8px] py-[4px] text-[8px] font-semibold text-[#0EA5E9]"
      >
        PROCESSANDO...
      </span>

      <span
        v-else-if="uploadStore.validationResult?.status === 'ERRO_SCHEMA'"
        class="rounded-[4px] bg-red-100 px-[8px] py-[4px] text-[8px] font-semibold text-red-600"
      >
        ERRO NO ARQUIVO
      </span>

      <span
        v-else-if="uploadStore.validationResult"
        class="rounded-[4px] bg-[#14B8A6]/10 px-[8px] py-[4px] text-[8px] font-semibold text-[#14B8A6]"
      >
        VALIDAÇÃO CONCLUÍDA
      </span>
    </div>

    <!-- RESUMO DA ÚLTIMA VALIDAÇÃO -->
    <div
      v-if="uploadStore.validationResult"
      class="mt-[20px] grid grid-cols-1 gap-3 sm:grid-cols-3"
    >
      <div class="rounded-lg border border-slate-200 p-4">
        <p class="text-xs text-slate-500">Linhas analisadas</p>
        <p class="mt-1 text-2xl font-semibold text-[#0F172A]">
          {{ uploadStore.validationResult.totalLinhas ?? 0 }}
        </p>
      </div>

      <div class="rounded-lg border border-[#14B8A6]/30 p-4">
        <p class="text-xs text-slate-500">Registros válidos</p>
        <p class="mt-1 text-2xl font-semibold text-[#14B8A6]">
          {{ uploadStore.validationResult.totalValidos ?? 0 }}
        </p>
      </div>

      <div class="rounded-lg border border-red-200 p-4">
        <p class="text-xs text-slate-500">Registros inválidos</p>
        <p class="mt-1 text-2xl font-semibold text-red-600">
          {{ uploadStore.validationResult.totalInvalidos ?? 0 }}
        </p>
      </div>

      <div class="sm:col-span-3 flex flex-wrap items-center justify-between gap-3">
        <p class="text-xs text-slate-500">
          {{ uploadStore.validationResult.totalErros ?? 0 }} erro(s) e
          {{ uploadStore.validationResult.totalAvisos ?? 0 }} aviso(s).
        </p>

        <button
          type="button"
          class="rounded-[5px] bg-[#2563EB] px-4 py-2 text-xs font-medium text-white transition hover:bg-[#0EA5E9]"
          @click="abrirRelatorio"
        >
          Visualizar relatório
        </button>
      </div>
    </div>

    <!-- HISTÓRICO -->
    <div class="mt-[40px]">
      <div class="flex items-center justify-between">
        <h2 class="text-[20px] font-semibold text-[#0F172A]">
          Histórico de Processamento
        </h2>

        <span class="font-mono text-[8px] uppercase tracking-wide text-[#2563EB]">
          {{ uploadStore.history.length }} arquivos
        </span>
      </div>

      <div class="mt-[15px] overflow-x-auto rounded-[6px] border border-slate-200">
        <table class="w-full border-collapse">
          <thead>
            <tr class="border-b border-[#0EA5E9]/20 bg-[#0F172A]">
              <th class="px-[14px] py-[11px] text-left text-[9px] font-semibold uppercase tracking-wide text-white">
                Arquivo
              </th>

              <th class="px-[14px] py-[11px] text-left text-[9px] font-semibold uppercase tracking-wide text-white">
                Data_Processamento
              </th>

              <th class="px-[14px] py-[11px] text-left text-[9px] font-semibold uppercase tracking-wide text-white">
                Linhas_Lidas
              </th>

              <th class="px-[14px] py-[11px] text-left text-[9px] font-semibold uppercase tracking-wide text-white">
                Status
              </th>

              <th class="px-[14px] py-[11px] text-left text-[9px] font-semibold uppercase tracking-wide text-white">
                Ações
              </th>
            </tr>
          </thead>

          <tbody>
            <tr
              v-for="item in uploadStore.history"
              :key="item.id"
              class="border-b border-slate-100 transition last:border-0 hover:bg-[#0EA5E9]/5"
            >
              <td class="px-[14px] py-[13px] text-[10px] font-medium text-[#0F172A]">
                {{ item.name }}
              </td>

              <td class="px-[14px] py-[13px] text-[9px] text-slate-500">
                {{ item.date }}
              </td>

              <td class="px-[14px] py-[13px] text-[9px] text-slate-500">
                {{ item.rows ?? '--' }}
              </td>

              <td class="px-[14px] py-[13px]">
                <span
                  v-if="item.status === 'PROCESSANDO'"
                  class="rounded-[4px] bg-[#0EA5E9]/10 px-[7px] py-[4px] text-[8px] font-semibold text-[#0EA5E9]"
                >
                  PROCESSANDO...
                </span>

                <span
                  v-else-if="item.status === 'NORMALIZADO'"
                  class="rounded-[4px] bg-[#14B8A6]/10 px-[7px] py-[4px] text-[8px] font-semibold text-[#14B8A6]"
                >
                  NORMALIZADO
                </span>

                <span
                  v-else-if="item.status === 'VALIDADO_COM_AVISOS'"
                  class="rounded-[4px] bg-amber-100 px-[7px] py-[4px] text-[8px] font-semibold text-amber-700"
                >
                  VALIDADO COM AVISOS
                </span>

                <span
                  v-else
                  class="rounded-[4px] bg-[#2563EB]/10 px-[7px] py-[4px] text-[8px] font-semibold text-[#2563EB]"
                >
                  {{ item.status }}
                </span>
              </td>

              <td class="px-[14px] py-[13px]">
                <div class="flex items-center gap-3">
                  <button
                    v-if="uploadStore.validationResult?.arquivo === item.name"
                    type="button"
                    class="text-[9px] font-medium text-[#14B8A6] hover:underline"
                    @click="abrirRelatorio"
                  >
                    Visualizar relatório
                  </button>

                  <button
                    type="button"
                    class="text-[9px] font-medium text-[#2563EB] transition hover:text-[#0EA5E9] hover:underline"
                    @click="uploadStore.removeHistory(item.id)"
                  >
                    Remover
                  </button>
                </div>
              </td>
            </tr>

            <tr v-if="uploadStore.history.length === 0">
              <td
                colspan="5"
                class="px-[14px] py-[30px] text-center text-[12px] text-slate-500"
              >
                Nenhum arquivo processado ainda.
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <!-- RODAPÉ -->
    <div class="mt-[30px] h-[3px] w-full rounded-full bg-gradient-to-r from-[#2563EB] via-[#0EA5E9] to-[#14B8A6]"></div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useUploadStore } from '../stores/uploadStore'

const uploadStore = useUploadStore()
const fileInput = ref(null)
const router = useRouter()

const processing = ref(false)

function openFile() {
  fileInput.value?.click()
}

async function handleFile(event) {
  const file = event.target.files?.[0]

  if (!file) return

  processing.value = true

  try {
    await uploadStore.validateFile(file)
  } finally {
    processing.value = false
    event.target.value = ''
  }
}

async function handleDrop(event) {
  const file = event.dataTransfer.files?.[0]

  if (!file) return

  processing.value = true

  try {
    await uploadStore.validateFile(file)
  } finally {
    processing.value = false
  }
}

function abrirRelatorio() {
  if (!uploadStore.validationResult) return

  router.push('/app/relatorio')
}

function formatFileSize(size) {
  if (size < 1024) {
    return `${size} B`
  }

  if (size < 1024 * 1024) {
    return `${(size / 1024).toFixed(1)} KB`
  }

  return `${(size / (1024 * 1024)).toFixed(1)} MB`
}
</script>
