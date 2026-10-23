
<template>
  <main class="min-h-screen w-full bg-white">

    <div class="grid min-h-screen grid-cols-1 lg:grid-cols-2">

      <!-- ================================================ -->
      <!-- LADO ESQUERDO -->
      <!-- ================================================ -->

      <section
        class="relative flex min-h-screen flex-col justify-between overflow-hidden bg-[#0F172A] px-8 py-10 text-white sm:px-12 lg:px-[61px]"
      >

        <!-- LOGO -->
        <div class="relative z-10">
          <RouterLink to="/" class="inline-block">
            <h1 class="text-[25px] font-bold tracking-tight">
              InsightBase<span class="text-[#14B8A6]">.</span>
            </h1>
          </RouterLink>
        </div>

        <!-- TEXTO PRINCIPAL -->
        <div class="relative z-10 my-12 max-w-[430px]">

          <p class="mb-4 font-mono text-xs uppercase tracking-[3px] text-[#14B8A6]">
            ANÁLISE INTELIGENTE
          </p>

          <h2 class="text-[35px] font-normal leading-[1.15] tracking-[0.1px]">
            Transforme dados em

            <span class="mt-1 block font-medium text-[#0EA5E9]">
              decisões estratégicas
            </span>
          </h2>

          <p class="mt-5 max-w-[425px] text-[16px] leading-relaxed text-white/80">
            Acesse o painel inteligente para gestão unificada de
            clientes, faturamento, contratos e telemetria gerada via
            Python/Pandas.
          </p>

          <!-- DESTAQUES -->
          <div class="mt-9 space-y-5">

            <div class="flex items-center gap-3">
              <div class="flex h-9 w-9 items-center justify-center rounded-lg bg-[#2563EB]/20 text-[#0EA5E9]">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  class="h-5 w-5"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke="currentColor"
                  stroke-width="1.7"
                >
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    d="M4 19V5m0 14h16M8 15v-4m4 4V7m4 8v-6"
                  />
                </svg>
              </div>

              <span class="text-sm text-white/80">
                Análise e visualização de dados
              </span>
            </div>

            <div class="flex items-center gap-3">
              <div class="flex h-9 w-9 items-center justify-center rounded-lg bg-[#14B8A6]/15 text-[#14B8A6]">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  class="h-5 w-5"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke="currentColor"
                  stroke-width="1.7"
                >
                  <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    d="M9 12l2 2 4-4"
                  />
                  <circle cx="12" cy="12" r="9" />
                </svg>
              </div>

              <span class="text-sm text-white/80">
                Dados organizados e normalizados
              </span>
            </div>

          </div>

        </div>

        <!-- RODAPÉ -->
        <div class="relative z-10">
          <p class="text-[14px] font-semibold text-white">
            InsightBase © 2026
          </p>
        </div>

      </section>

      <!-- ================================================ -->
      <!-- LADO DIREITO -->
      <!-- ================================================ -->

      <section
        class="relative flex min-h-screen items-center justify-center overflow-hidden bg-white px-5 py-10"
      >

        <!-- CARD DE LOGIN -->
        <div
          class="relative z-10 w-full max-w-[350px] rounded-[9px] border border-slate-200 border-t-[3px] border-t-[#14B8A6] bg-white px-6 pb-8 pt-7 shadow-[0_7px_20px_rgba(15,23,42,0.10)]"
        >

          <!-- TÍTULO -->
          <div class="mb-7">

            <div class="mb-4 flex h-11 w-11 items-center justify-center rounded-xl bg-[#2563EB]/10 text-[#2563EB]">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                class="h-6 w-6"
                fill="none"
                viewBox="0 0 24 24"
                stroke="currentColor"
                stroke-width="1.7"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  d="M15 21v-2a4 4 0 0 0-4-4H7a4 4 0 0 0-4 4v2"
                />
                <circle cx="9" cy="7" r="4" />
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  d="M16 11l2 2 4-4"
                />
              </svg>
            </div>

            <h2 class="text-[29px] font-semibold leading-tight text-[#0F172A]">
              Welcome!
            </h2>

            <p class="mt-2 text-[14px] leading-relaxed text-slate-500">
              Faça login para acessar sua conta.
            </p>

          </div>

          <!-- MENSAGEM DE ERRO -->
          <div
            v-if="erro"
            role="alert"
            class="mb-5 rounded-md border border-red-200 bg-red-50 px-3 py-2"
          >
            <p class="text-xs text-red-700">
              {{ erro }}
            </p>
          </div>

          <!-- FORMULÁRIO -->
          <form
            class="space-y-5"
            @submit.prevent="acessarPainel"
          >

            <!-- E-MAIL -->
            <div>

              <label
                for="email"
                class="mb-2 block text-xs font-semibold uppercase tracking-wide text-slate-700"
              >
                E-mail
              </label>

              <input
                id="email"
                v-model.trim="email"
                type="email"
                placeholder="nome.sobrenome@empresa.com"
                autocomplete="email"
                maxlength="254"
                required
                class="h-10 w-full rounded-md border border-slate-300 bg-white px-3 text-sm text-slate-800 outline-none transition placeholder:text-slate-400 focus:border-[#2563EB] focus:ring-2 focus:ring-[#2563EB]/15"
              />

            </div>

            <!-- SENHA -->
            <div>

              <label
                for="senha"
                class="mb-2 block text-xs font-semibold uppercase tracking-wide text-slate-700"
              >
                Senha
              </label>

              <div class="relative">

                <input
                  id="senha"
                  v-model="senha"
                  :type="mostrarSenha ? 'text' : 'password'"
                  placeholder="Digite sua senha"
                  autocomplete="current-password"
                  required
                  class="h-10 w-full rounded-md border border-slate-300 bg-white px-3 pr-11 text-sm text-slate-800 outline-none transition placeholder:text-slate-400 focus:border-[#2563EB] focus:ring-2 focus:ring-[#2563EB]/15"
                />

                <!-- MOSTRAR / OCULTAR SENHA -->
                <button
                  type="button"
                  :aria-label="mostrarSenha ? 'Ocultar senha' : 'Mostrar senha'"
                  class="absolute right-3 top-1/2 -translate-y-1/2 text-slate-500 transition hover:text-[#2563EB]"
                  @click="mostrarSenha = !mostrarSenha"
                >

                  <!-- OLHO ABERTO -->
                  <svg
                    v-if="!mostrarSenha"
                    xmlns="http://www.w3.org/2000/svg"
                    class="h-5 w-5"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke="currentColor"
                    stroke-width="1.7"
                  >
                    <path
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      d="M2 12s3.5-7 10-7 10 7 10 7-3.5 7-10 7S2 12 2 12Z"
                    />
                    <circle cx="12" cy="12" r="3" />
                  </svg>

                  <!-- OLHO FECHADO -->
                  <svg
                    v-else
                    xmlns="http://www.w3.org/2000/svg"
                    class="h-5 w-5"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke="currentColor"
                    stroke-width="1.7"
                  >
                    <path
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      d="M3 3l18 18M10.6 10.6a2 2 0 0 0 2.8 2.8M9.9 5.1A10.9 10.9 0 0 1 12 5c6.5 0 10 7 10 7a13.8 13.8 0 0 1-3.1 3.8M6.2 6.2C3.5 8 2 12 2 12s3.5 7 10 7c1.2 0 2.3-.2 3.3-.6"
                    />
                  </svg>

                </button>

              </div>

            </div>

            <!-- LEMBRAR ACESSO / ESQUECI SENHA -->
            <div class="flex items-center justify-between gap-2">

              <label class="flex cursor-pointer items-center gap-2 text-xs text-slate-600">

                <input
                  v-model="lembrarAcesso"
                  type="checkbox"
                  class="h-4 w-4 cursor-pointer accent-[#2563EB]"
                />

                <span>Lembrar acesso</span>

              </label>

              <button
                type="button"
                class="text-xs text-[#2563EB] transition hover:text-[#0EA5E9] hover:underline"
                @click="esqueciSenha"
              >
                Esqueci minha senha
              </button>

            </div>

            <!-- ACESSAR PAINEL -->
            <button
              type="submit"
              class="h-11 w-full rounded-md bg-[#2563EB] text-sm font-semibold text-white transition hover:bg-[#0EA5E9] active:scale-[0.99]"
            >
              Acessar Painel
            </button>

          </form>

          <!-- CADASTRO -->
          <div class="mt-6 border-t border-slate-100 pt-5 text-center">

            <p class="text-sm text-slate-500">
              Não possui acesso?
            </p>

            <RouterLink
              to="/cadastro"
              class="mt-2 inline-block text-sm font-semibold text-[#14B8A6] transition hover:text-[#0EA5E9] hover:underline"
            >
              Criar uma conta
            </RouterLink>

          </div>

          <!-- VOLTAR À HOME -->
          <div class="mt-4 text-center">

            <RouterLink
              to="/"
              class="text-xs text-slate-400 transition hover:text-[#2563EB]"
            >
              ← Voltar à página inicial
            </RouterLink>

          </div>

        </div>

      </section>

    </div>

  </main>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const email = ref('')
const senha = ref('')
const mostrarSenha = ref(false)
const lembrarAcesso = ref(false)
const erro = ref('')

function acessarPainel() {
  erro.value = ''

  if (!email.value.trim() || !senha.value) {
    erro.value = 'Preencha o e-mail e a senha.'
    return
  }

  // A navegação está conectada ao seu Vue Router.
  // A autenticação real ainda depende do backend.
  router.push('/app/upload')
}

function esqueciSenha() {
  alert(
    'Para recuperar sua senha, entre em contato com o responsável pelo sistema.'
  )
}
</script>
