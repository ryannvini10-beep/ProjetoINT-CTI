
<template>
  <div class="min-h-screen bg-[#F3F4F6] px-5 py-7 lg:px-9">
    <!-- CABEÇALHO -->
    <header class="mb-7 flex flex-wrap items-center justify-between gap-4">
      <div>
        <h1 class="text-2xl font-bold tracking-tight text-[#0F172A]">
          Painel de Métricas
        </h1>
        <p class="mt-1 text-sm text-slate-500">
          Visão geral da qualidade e do processamento dos dados
        </p>
      </div>

      <select
        v-model="periodo"
        class="rounded-lg border border-slate-200 bg-white px-4 py-2.5 text-sm text-slate-700 shadow-sm outline-none focus:border-[#2563EB]"
      >
        <option value="7">Últimos 7 dias</option>
        <option value="30">Últimos 30 dias</option>
        <option value="90">Últimos 90 dias</option>
      </select>
    </header>

    <!-- INDICADORES -->
    <section class="grid grid-cols-1 gap-4 sm:grid-cols-2 xl:grid-cols-4">
      <article
        v-for="kpi in indicadores"
        :key="kpi.titulo"
        class="rounded-xl border border-slate-200 bg-white p-5 shadow-sm transition hover:-translate-y-1 hover:shadow-md"
      >
        <div class="flex items-center justify-between gap-2">
          <p class="text-xs font-semibold uppercase tracking-wider text-slate-500">
            {{ kpi.titulo }}
          </p>

          <span
            class="flex h-9 w-9 items-center justify-center rounded-lg"
            :class="kpi.fundo"
          >
            <span class="text-lg font-bold" :class="kpi.cor">
              {{ kpi.icone }}
            </span>
          </span>
        </div>

        <p class="mt-4 text-3xl font-bold tracking-tight text-[#0F172A]">
          {{ kpi.valor }}
        </p>

        <p class="mt-2 text-xs" :class="kpi.cor">
          {{ kpi.descricao }}
        </p>
      </article>
    </section>

    <!-- GRÁFICOS PRINCIPAIS -->
    <section class="mt-6 grid grid-cols-1 gap-5 xl:grid-cols-3">
      <!-- BARRAS -->
      <article class="rounded-xl border border-slate-200 bg-white p-5 shadow-sm xl:col-span-2">
        <div class="mb-5 flex flex-wrap items-start justify-between gap-3">
          <div>
            <h2 class="font-bold text-[#0F172A]">
              Registros analisados
            </h2>
            <p class="mt-1 text-xs text-slate-500">
              Comparação entre registros válidos e inválidos
            </p>
          </div>

          <span class="rounded-full bg-blue-50 px-3 py-1 text-xs font-medium text-[#2563EB]">
            {{ periodo }} dias
          </span>
        </div>

        <div class="h-[300px]">
          <Bar
            :data="dadosRegistros"
            :options="opcoesBarras"
          />
        </div>
      </article>

      <!-- ROSCA -->
      <article class="rounded-xl border border-slate-200 bg-white p-5 shadow-sm">
        <div class="mb-5">
          <h2 class="font-bold text-[#0F172A]">
            Status das validações
          </h2>
          <p class="mt-1 text-xs text-slate-500">
            Distribuição dos resultados
          </p>
        </div>

        <div class="h-[250px]">
          <Doughnut
            :data="dadosStatus"
            :options="opcoesRosca"
          />
        </div>

        <div class="mt-4 grid grid-cols-2 gap-3 text-xs">
          <div class="flex items-center gap-2">
            <span class="h-2.5 w-2.5 rounded-full bg-[#14B8A6]"></span>
            <span class="text-slate-600">Normalizados</span>
          </div>

          <div class="flex items-center gap-2">
            <span class="h-2.5 w-2.5 rounded-full bg-[#F59E0B]"></span>
            <span class="text-slate-600">Com avisos</span>
          </div>

          <div class="flex items-center gap-2">
            <span class="h-2.5 w-2.5 rounded-full bg-[#EF4444]"></span>
            <span class="text-slate-600">Com erros</span>
          </div>
        </div>
      </article>
    </section>

    <!-- EVOLUÇÃO TEMPORAL -->
    <section class="mt-5 grid grid-cols-1 gap-5 xl:grid-cols-3">
      <article class="rounded-xl border border-slate-200 bg-white p-5 shadow-sm xl:col-span-2">
        <div class="mb-5">
          <h2 class="font-bold text-[#0F172A]">
            Evolução dos processamentos
          </h2>
          <p class="mt-1 text-xs text-slate-500">
            Volume de arquivos processados no período
          </p>
        </div>

        <div class="h-[300px]">
          <Line
            :data="dadosEvolucao"
            :options="opcoesLinha"
          />
        </div>
      </article>

      <!-- QUALIDADE -->
      <article class="rounded-xl border border-slate-200 bg-white p-5 shadow-sm">
        <h2 class="font-bold text-[#0F172A]">
          Qualidade dos dados
        </h2>
        <p class="mt-1 text-xs text-slate-500">
          Resumo demonstrativo das validações
        </p>

        <div class="mt-7 space-y-6">
          <div>
            <div class="mb-2 flex justify-between text-sm">
              <span class="text-slate-600">Registros válidos</span>
              <strong class="text-[#14B8A6]">
                {{ qualidade.validos }}%
              </strong>
            </div>

            <div class="h-2.5 overflow-hidden rounded-full bg-slate-100">
              <div
                class="h-full rounded-full bg-[#14B8A6] transition-all duration-500"
                :style="{ width: `${qualidade.validos}%` }"
              ></div>
            </div>
          </div>

          <div>
            <div class="mb-2 flex justify-between text-sm">
              <span class="text-slate-600">Registros inválidos</span>
              <strong class="text-red-500">
                {{ qualidade.invalidos }}%
              </strong>
            </div>

            <div class="h-2.5 overflow-hidden rounded-full bg-slate-100">
              <div
                class="h-full rounded-full bg-red-400 transition-all duration-500"
                :style="{ width: `${qualidade.invalidos}%` }"
              ></div>
            </div>
          </div>

          <div class="rounded-lg bg-blue-50 p-4">
            <p class="text-xs text-slate-500">
              Taxa de aprovação
            </p>
            <p class="mt-1 text-2xl font-bold text-[#2563EB]">
              {{ qualidade.validos }}%
            </p>
            <p class="mt-1 text-xs text-slate-500">
              Base demonstrativa, substituível pelos dados reais.
            </p>
          </div>
        </div>
      </article>
    </section>

    <!-- RODAPÉ -->
    <footer class="mt-7 border-t border-slate-200 pt-4">
      <p class="text-xs text-slate-400">
        InsightBase · Painel analítico de qualidade dos dados
      </p>
    </footer>
  </div>
</template>

<script setup>
import { computed, ref } from 'vue'
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  CategoryScale,
  LinearScale,
  BarElement,
  PointElement,
  LineElement,
  ArcElement,
  Filler
} from 'chart.js'

import { Bar, Doughnut, Line } from 'vue-chartjs'

ChartJS.register(
  Title,
  Tooltip,
  Legend,
  CategoryScale,
  LinearScale,
  BarElement,
  PointElement,
  LineElement,
  ArcElement,
  Filler
)

const periodo = ref('7')

// Dados demonstrativos.
// Depois conectaremos estes valores ao uploadStore.
const basePorPeriodo = {
  7: {
    validos: [28, 35, 24, 42, 31, 48, 39],
    invalidos: [5, 4, 8, 3, 6, 2, 7],
    processamentos: [2, 3, 2, 4, 3, 5, 4],
    status: [65, 15, 20]
  },
  30: {
    validos: [120, 145, 132, 170, 158, 190, 210],
    invalidos: [18, 22, 15, 28, 20, 17, 24],
    processamentos: [8, 10, 9, 12, 11, 14, 13],
    status: [72, 12, 16]
  },
  90: {
    validos: [340, 390, 420, 450, 510, 570, 620],
    invalidos: [55, 48, 62, 50, 45, 52, 43],
    processamentos: [22, 25, 27, 30, 34, 37, 40],
    status: [78, 9, 13]
  }
}

const base = computed(() => basePorPeriodo[periodo.value])

const indicadores = computed(() => {
  const totalValidos = base.value.validos.reduce((a, b) => a + b, 0)
  const totalInvalidos = base.value.invalidos.reduce((a, b) => a + b, 0)
  const total = totalValidos + totalInvalidos

  const taxa = total > 0
    ? (totalValidos / total) * 100
    : 0

  const totalProcessamentos = base.value.processamentos.reduce(
    (a, b) => a + b,
    0
  )

  return [
    {
      titulo: 'Registros analisados',
      valor: total.toLocaleString('pt-BR'),
      descricao: 'Válidos e inválidos',
      icone: '▤',
      cor: 'text-[#2563EB]',
      fundo: 'bg-blue-50'
    },
    {
      titulo: 'Registros válidos',
      valor: totalValidos.toLocaleString('pt-BR'),
      descricao: `${taxa.toFixed(1)}% do total`,
      icone: '✓',
      cor: 'text-[#14B8A6]',
      fundo: 'bg-teal-50'
    },
    {
      titulo: 'Registros inválidos',
      valor: totalInvalidos.toLocaleString('pt-BR'),
      descricao: 'Necessitam de correção',
      icone: '!',
      cor: 'text-red-500',
      fundo: 'bg-red-50'
    },
    {
      titulo: 'Processamentos',
      valor: totalProcessamentos.toLocaleString('pt-BR'),
      descricao: `Últimos ${periodo.value} dias`,
      icone: '↻',
      cor: 'text-[#0EA5E9]',
      fundo: 'bg-sky-50'
    }
  ]
})

const qualidade = computed(() => {
  const totalValidos = base.value.validos.reduce((a, b) => a + b, 0)
  const totalInvalidos = base.value.invalidos.reduce((a, b) => a + b, 0)
  const total = totalValidos + totalInvalidos

  const validos = total > 0
    ? Math.round((totalValidos / total) * 100)
    : 0

  return {
    validos,
    invalidos: 100 - validos
  }
})

const dadosRegistros = computed(() => ({
  labels: ['Período 1', 'Período 2', 'Período 3', 'Período 4', 'Período 5', 'Período 6', 'Período 7'],
  datasets: [
    {
      label: 'Válidos',
      data: base.value.validos,
      backgroundColor: '#14B8A6',
      borderRadius: 5,
      maxBarThickness: 28
    },
    {
      label: 'Inválidos',
      data: base.value.invalidos,
      backgroundColor: '#F87171',
      borderRadius: 5,
      maxBarThickness: 28
    }
  ]
}))

const dadosStatus = computed(() => ({
  labels: ['Normalizados', 'Com avisos', 'Com erros'],
  datasets: [
    {
      data: base.value.status,
      backgroundColor: ['#14B8A6', '#F59E0B', '#EF4444'],
      borderColor: '#FFFFFF',
      borderWidth: 3,
      hoverOffset: 8
    }
  ]
}))

const dadosEvolucao = computed(() => ({
  labels: ['Período 1', 'Período 2', 'Período 3', 'Período 4', 'Período 5', 'Período 6', 'Período 7'],
  datasets: [
    {
      label: 'Arquivos processados',
      data: base.value.processamentos,
      borderColor: '#2563EB',
      backgroundColor: 'rgba(37, 99, 235, 0.10)',
      fill: true,
      tension: 0.35,
      pointRadius: 4,
      pointHoverRadius: 7,
      pointBackgroundColor: '#0EA5E9'
    }
  ]
}))

const opcoesBarras = {
  responsive: true,
  maintainAspectRatio: false,
  interaction: {
    mode: 'index',
    intersect: false
  },
  plugins: {
    legend: {
      position: 'bottom',
      labels: {
        usePointStyle: true,
        boxWidth: 8,
        padding: 20
      }
    },
    tooltip: {
      enabled: true
    }
  },
  scales: {
    x: {
      grid: {
        display: false
      },
      border: {
        display: false
      },
      ticks: {
        color: '#64748B',
        font: {
          size: 10
        }
      }
    },
    y: {
      beginAtZero: true,
      border: {
        display: false,
        dash: [4, 4]
      },
      grid: {
        color: '#E2E8F0'
      },
      ticks: {
        precision: 0,
        color: '#64748B'
      }
    }
  }
}

const opcoesRosca = {
  responsive: true,
  maintainAspectRatio: false,
  cutout: '68%',
  plugins: {
    legend: {
      display: false
    },
    tooltip: {
      enabled: true
    }
  }
}

const opcoesLinha = {
  responsive: true,
  maintainAspectRatio: false,
  interaction: {
    mode: 'index',
    intersect: false
  },
  plugins: {
    legend: {
      display: false
    },
    tooltip: {
      enabled: true
    }
  },
  scales: {
    x: {
      grid: {
        display: false
      },
      border: {
        display: false
      },
      ticks: {
        color: '#64748B',
        font: {
          size: 10
        }
      }
    },
    y: {
      beginAtZero: true,
      border: {
        display: false
      },
      grid: {
        color: '#E2E8F0'
      },
      ticks: {
        precision: 0,
        color: '#64748B'
      }
    }
  }
}
</script>
