<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <div class="min-h-screen bg-white">
    <div class="flex min-h-screen">

      <!-- SIDEBAR -->
      <aside
        class="fixed left-0 top-0 z-40 flex h-screen w-[207px] flex-col border-r border-slate-200 bg-white"
      >

        <!-- LOGO -->
        <div class="px-10 pt-[45px]">
          <div class="text-[25px] font-bold tracking-tight text-[#2563EB]">
            InsightBase <span class="text-[#14B8A6]"></span>
          </div>
        </div>

        <!-- NAVEGAÇÃO -->
        <nav class="mt-[50px] px-[29px]">

          <p
            class="mb-[17px] pl-[11px] text-[14px] font-medium uppercase tracking-wide text-slate-400"
          >
            Navegação
          </p>

          <!-- DASHBOARD -->
          <RouterLink
            to="/app/dashboard"
            active-class="bg-[#2563EB] text-white shadow-sm"
            class="flex h-[28px] w-full items-center gap-[10px] rounded-[5px] px-[10px] text-[15px] font-medium text-slate-500 transition hover:bg-blue-50 hover:text-[#2563EB]"
          >
            <span>Dashboard</span>
          </RouterLink>

          <!-- UPLOAD -->
          <RouterLink
            to="/app/upload"
            active-class="bg-[#2563EB] text-white shadow-sm"
            class="mt-[8px] flex h-[28px] w-full items-center gap-[10px] rounded-[5px] px-[10px] text-[15px] font-medium text-slate-500 transition hover:bg-blue-50 hover:text-[#2563EB]"
          >
            <span>Upload</span>
          </RouterLink>

        </nav>

        <!-- USUÁRIO -->
        <div class="mt-auto border-t border-slate-100 px-[29px] pb-[50px] pt-[20px]">

          <div class="flex items-center gap-[9px]">

            <div
              class="flex h-[28px] w-[28px] items-center justify-center rounded-full bg-[#0EA5E9] text-[9px] font-semibold text-white"
            >
              AS
            </div>

            <div class="leading-tight">
              <p class="text-[15px] font-semibold text-slate-800">
                Ryan Vinicius
              </p>

              <p class="mt-[3px] text-[12px] text-slate-500">
                Consultor
              </p>
            </div>

          </div>

          <!-- SAIR -->
          <RouterLink
            to="/login"
            class="mt-[22px] flex h-[23px] w-full items-center gap-[7px] rounded-[4px] bg-slate-100 px-[9px] text-[12px] font-medium text-slate-500 transition hover:bg-slate-200"
          >
            <span>Encerrar a Sessão</span>
          </RouterLink>

        </div>

      </aside>

      <!-- CONTEÚDO DAS PÁGINAS -->
      <main class="ml-[207px] min-h-screen flex-1">
        <RouterView />
      </main>

    </div>
  </div>
</template>