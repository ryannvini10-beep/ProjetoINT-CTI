
<template>
  <section class="p-6 space-y-6">
    <header>
      <h1 class="text-2xl font-bold">
        Relatório de validação
      </h1>

      <p class="mt-2">
        Consulte os resultados da validação das planilhas de clientes.
      </p>
    </header>

    <div
      v-if="!uploadStore.validationResult"
      class="rounded-lg border p-6"
    >
      <p>
        Nenhuma planilha foi validada ainda.
      </p>

      <RouterLink
        to="/app/upload"
        class="mt-4 inline-block underline"
      >
        Ir para upload
      </RouterLink>
    </div>

    <div v-else class="space-y-4">
      <div class="grid grid-cols-1 gap-4 md:grid-cols-3">
        <div class="rounded-lg border p-4">
          <p>Total de registros</p>
          <strong class="text-2xl">
            {{ resultado.totalLinhas }}
          </strong>
        </div>

        <div class="rounded-lg border p-4">
          <p>Registros válidos</p>
          <strong class="text-2xl">
            {{ resultado.totalValidos }}
          </strong>
        </div>

        <div class="rounded-lg border p-4">
          <p>Registros inválidos</p>
          <strong class="text-2xl">
            {{ resultado.totalInvalidos }}
          </strong>
        </div>
      </div>

      <div class="rounded-lg border p-4">
        <h2 class="mb-3 text-lg font-semibold">
          Erros encontrados
        </h2>

        <p v-if="!resultado.erros?.length">
          Nenhum erro encontrado.
        </p>

        <div v-else class="overflow-x-auto">
          <table class="w-full text-left">
            <thead>
              <tr>
                <th class="p-2">Linha</th>
                <th class="p-2">Campo</th>
                <th class="p-2">Mensagem</th>
                <th class="p-2">Valor</th>
              </tr>
            </thead>

            <tbody>
              <tr
                v-for="(erro, index) in resultado.erros"
                :key="`${erro.linha}-${erro.campo}-${index}`"
              >
                <td class="p-2">{{ erro.linha }}</td>
                <td class="p-2">{{ erro.campo }}</td>
                <td class="p-2">{{ erro.mensagem }}</td>
                <td class="p-2">{{ erro.valor }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>

      <div class="rounded-lg border p-4">
        <h2 class="mb-3 text-lg font-semibold">
          Avisos
        </h2>

        <p v-if="!resultado.avisos?.length">
          Nenhum aviso encontrado.
        </p>

        <ul v-else class="list-disc space-y-2 pl-5">
          <li
            v-for="(aviso, index) in resultado.avisos"
            :key="`${aviso.linha}-${index}`"
          >
            Linha {{ aviso.linha }} — {{ aviso.mensagem }}
          </li>
        </ul>
      </div>
    </div>
  </section>
</template>

<script setup>
import { computed } from 'vue'
import { RouterLink } from 'vue-router'
import { useUploadStore } from '../stores/uploadStore'

const uploadStore = useUploadStore()

const resultado = computed(
  () => uploadStore.validationResult ?? {}
)
</script>
