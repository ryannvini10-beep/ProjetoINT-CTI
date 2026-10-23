
<template>
  <div class="min-h-screen bg-white text-slate-800">

    <!-- HEADER -->
    <header class="sticky top-0 z-20 bg-[#0F172A] text-white shadow-md">
      <div class="mx-auto flex max-w-6xl items-center justify-between px-6 py-4">

        <div class="flex items-center gap-3">
          <img
            src="/logo.png"
            alt="InsightBase"
            class="h-20 w-20 object-contain"
          />

          <span class="text-sm font-semibold sm:text-base">
            InsightBase
          </span>
        </div>

        <!-- BOTÃO LOGIN INTEGRADO AO VUE ROUTER -->
        <RouterLink
          to="/login"
          class="rounded-full bg-[#2563EB] px-4 py-2 text-sm font-semibold text-white transition hover:bg-[#0EA5E9]"
        >
          Login
        </RouterLink>

      </div>
    </header>

    <!-- CONTEÚDO -->
    <main>

      <!-- HERO -->
      <section id="demanda" class="bg-[#14B8A6]/10">

        <div class="mx-auto grid max-w-6xl items-center gap-14 px-6 py-20 lg:grid-cols-2">

          <!-- TEXTO -->
          <div>

            <h1 class="mt-5 text-3xl font-bold leading-tight text-[#0F172A] sm:text-4xl lg:text-5xl">
              Análise e tratamento de dados comerciais para geração de insights estratégicos
            </h1>

            <p class="mt-5 text-base leading-relaxed text-slate-600 sm:text-lg">
              Analisar, tratar e organizar dados hoje presos em planilhas
              para revelar padrões, apoiar decisões comerciais e transformar
              o Excel em direção estratégica real.
            </p>

            <div class="mt-9 flex flex-wrap gap-8 border-t border-[#0EA5E9]/40 pt-6">

              <div>
                <p class="font-mono text-xs uppercase tracking-widest text-slate-400">
                  Fonte
                </p>
                <p class="mt-1 text-sm font-semibold">
                  Base Excel
                </p>
              </div>

              <div>
                <p class="font-mono text-xs uppercase tracking-widest text-slate-400">
                  Foco
                </p>
                <p class="mt-1 text-sm font-semibold">
                  Dados comerciais
                </p>
              </div>

              <div>
                <p class="font-mono text-xs uppercase tracking-widest text-slate-400">
                  Objetivo
                </p>
                <p class="mt-1 text-sm font-semibold">
                  Insights estratégicos
                </p>
              </div>

            </div>

          </div>

          <!-- FLUXO -->
          <div class="rounded-2xl border border-[#0EA5E9]/30 bg-white p-8 shadow-sm">

            <p class="mb-6 font-mono text-base uppercase tracking-widest text-[#0F172A]">
              O que a solução faz
            </p>

            <!-- ETAPA 1 -->
            <div class="flex gap-4">

              <div class="flex flex-col items-center">
                <div class="flex h-9 w-9 items-center justify-center rounded-full bg-[#2563EB] font-semibold text-white">
                  1
                </div>
                <div class="h-16 w-px bg-[#0EA5E9]/30"></div>
              </div>

              <div>
                <h3 class="text-sm font-semibold text-[#0F172A]">
                  Analisar informações
                </h3>
                <p class="mt-1 text-sm text-slate-500">
                  Identificar padrões, comportamentos e oportunidades comerciais.
                </p>
              </div>

            </div>

            <!-- ETAPA 2 -->
            <div class="flex gap-4">

              <div class="flex flex-col items-center">
                <div class="flex h-9 w-9 items-center justify-center rounded-full bg-[#0EA5E9] font-semibold text-white">
                  2
                </div>
                <div class="h-16 w-px bg-[#0EA5E9]/30"></div>
              </div>

              <div>
                <h3 class="text-sm font-semibold text-[#0F172A]">
                  Tratar os dados
                </h3>
                <p class="mt-1 text-sm text-slate-500">
                  Corrigir inconsistências, duplicidades e informações incompletas.
                </p>
              </div>

            </div>

            <!-- ETAPA 3 -->
            <div class="flex gap-4">

              <div class="flex flex-col items-center">
                <div class="flex h-9 w-9 items-center justify-center rounded-full bg-[#14B8A6] font-semibold text-white">
                  3
                </div>
                <div class="h-16 w-px bg-[#0EA5E9]/30"></div>
              </div>

              <div>
                <h3 class="text-sm font-semibold text-[#0F172A]">
                  Organizar os dados
                </h3>
                <p class="mt-1 text-sm text-slate-500">
                  Centralizar e estruturar as informações comerciais existentes.
                </p>
              </div>

            </div>

            <!-- ETAPA 4 -->
            <div class="flex gap-4">

              <div class="flex flex-col items-center">
                <div class="flex h-9 w-9 items-center justify-center rounded-full bg-[#0F172A] font-semibold text-white">
                  4
                </div>
              </div>

              <div>
                <h3 class="text-sm font-semibold text-[#0F172A]">
                  Gerar insights
                </h3>
                <p class="mt-1 text-sm text-slate-500">
                  Transformar os dados tratados em informações estratégicas.
                </p>
              </div>

            </div>

          </div>

        </div>

      </section>

      <!-- DETALHES -->
      <section id="detalhes" class="bg-[#0F172A] text-white">

        <div class="mx-auto grid max-w-6xl gap-12 px-6 py-16 lg:grid-cols-2">

          <div>

            <h2 class="text-2xl font-bold sm:text-3xl">
              Sobre a base de dados
            </h2>

            <p class="mt-4 leading-relaxed text-white/70">
              A empresa possui uma base em Excel com informações da carteira de clientes e atuação comercial, atualmente utilizada de forma operacional.
            </p>

            <p class="mt-4 leading-relaxed text-white/70">
              A proposta é tratar esses dados e utilizar a análise para orientar decisões futuras, como priorização comercial e direcionamento de esforços por perfil de cliente.
            </p>

            <p class="mt-4 leading-relaxed text-white/70">
              A análise também poderá identificar comportamentos, padrões de contratação e oportunidades de melhoria por segmento.
            </p>

          </div>

          <!-- FICHA -->
          <div class="grid grid-cols-2 gap-5">

            <div class="rounded-lg border border-white/10 bg-white/5 p-4">
              <p class="font-mono text-xs uppercase text-[#0EA5E9]">
                Formato
              </p>
              <p class="mt-2 text-sm font-semibold">Excel</p>
            </div>

            <div class="rounded-lg border border-white/10 bg-white/5 p-4">
              <p class="font-mono text-xs uppercase text-[#0EA5E9]">
                Tipo de dados
              </p>
              <p class="mt-2 text-sm font-semibold">Comerciais</p>
            </div>

            <div class="rounded-lg border border-white/10 bg-white/5 p-4">
              <p class="font-mono text-xs uppercase text-[#0EA5E9]">
                Objetivo
              </p>
              <p class="mt-2 text-sm font-semibold">Análise</p>
            </div>

            <div class="rounded-lg border border-white/10 bg-white/5 p-4">
              <p class="font-mono text-xs uppercase text-[#0EA5E9]">
                Aplicação
              </p>
              <p class="mt-2 text-sm font-semibold">Estratégia comercial</p>
            </div>

            <div class="rounded-lg border border-white/10 bg-white/5 p-4">
              <p class="font-mono text-xs uppercase text-[#0EA5E9]">
                Tratamento
              </p>
              <p class="mt-2 text-sm font-semibold">Limpeza de dados</p>
            </div>

            <div class="rounded-lg border border-white/10 bg-white/5 p-4">
              <p class="font-mono text-xs uppercase text-[#14B8A6]">
                Resultado
              </p>
              <p class="mt-2 text-sm font-semibold">Insights</p>
            </div>

          </div>

        </div>

      </section>

    </main>

    <!-- FOOTER -->
    <footer class="border-t border-slate-200">

      <div class="mx-auto flex max-w-6xl flex-col items-center justify-between gap-3 px-6 py-8 text-sm text-slate-400 sm:flex-row">

        <span>SAGA SENAI de Inovação</span>

        <span>Escola SENAI de Informática · SP</span>

      </div>

    </footer>

  </div>
</template>
