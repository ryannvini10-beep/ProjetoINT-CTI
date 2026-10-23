
<template>
  <main class="min-h-screen w-full bg-white">

    <div class="grid min-h-screen grid-cols-1 lg:grid-cols-2">

      <!-- LADO ESQUERDO -->
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
            NOVO USUÁRIO
          </p>

          <h2 class="text-[35px] font-normal leading-[1.15] tracking-[0.1px]">
            Comece a transformar dados em

            <span class="mt-1 block font-medium text-[#0EA5E9]">
              decisões estratégicas
            </span>
          </h2>

          <p class="mt-5 max-w-[400px] text-[16px] leading-relaxed text-white/75">
            Crie sua conta para acessar as ferramentas de análise,
            tratamento e organização de dados comerciais da InsightBase.
          </p>

          <!-- BENEFÍCIOS -->
          <div class="mt-9 space-y-5">

            <div class="flex items-center gap-3">
              <span class="flex h-8 w-8 items-center justify-center rounded-lg bg-[#2563EB]/20 text-[#0EA5E9]">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  class="h-4 w-4"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke="currentColor"
                  stroke-width="2"
                >
                  <path stroke-linecap="round" stroke-linejoin="round" d="M9 12l2 2 4-4" />
                  <circle cx="12" cy="12" r="9" />
                </svg>
              </span>

              <span class="text-sm text-white/80">
                Gestão centralizada de informações
              </span>
            </div>

            <div class="flex items-center gap-3">
              <span class="flex h-8 w-8 items-center justify-center rounded-lg bg-[#14B8A6]/15 text-[#14B8A6]">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  class="h-4 w-4"
                  fill="none"
                  viewBox="0 0 24 24"
                  stroke="currentColor"
                  stroke-width="2"
                >
                  <path stroke-linecap="round" stroke-linejoin="round" d="M4 19V5m0 14h16M8 15v-4m4 4V7m4 8v-6" />
                </svg>
              </span>

              <span class="text-sm text-white/80">
                Dados organizados para gerar insights
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

      <!-- LADO DIREITO -->
      <section class="flex min-h-screen items-center justify-center bg-white px-5 py-10">

        <div class="w-full max-w-[390px]">

          <!-- CABEÇALHO DO FORMULÁRIO -->
          <div class="mb-7">

            <div class="mb-4 flex h-11 w-11 items-center justify-center rounded-xl bg-[#14B8A6]/10 text-[#14B8A6]">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                class="h-6 w-6"
                fill="none"
                viewBox="0 0 24 24"
                stroke="currentColor"
                stroke-width="1.7"
              >
                <path stroke-linecap="round" stroke-linejoin="round" d="M16 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2" />
                <circle cx="10" cy="7" r="4" />
                <path stroke-linecap="round" stroke-linejoin="round" d="M19 8v6m-3-3h6" />
              </svg>
            </div>

            <h2 class="text-[28px] font-semibold tracking-tight text-[#0F172A]">
              Criar conta
            </h2>

            <p class="mt-2 text-[14px] leading-relaxed text-slate-500">
              Preencha os dados abaixo para solicitar seu cadastro na InsightBase.
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
          <form class="space-y-4" @submit.prevent="cadastrar">

            <!-- NOME -->
            <div>
              <label
                for="nome"
                class="mb-1.5 block text-xs font-semibold uppercase tracking-wide text-slate-700"
              >
                Nome completo
              </label>

              <input
                id="nome"
                v-model.trim="nome"
                type="text"
                autocomplete="name"
                placeholder="Digite seu nome completo"
                minlength="3"
                maxlength="100"
                required
                class="h-10 w-full rounded-md border border-slate-300 bg-white px-3 text-sm text-slate-800 outline-none transition placeholder:text-slate-400 focus:border-[#2563EB] focus:ring-2 focus:ring-[#2563EB]/15"
              />
            </div>

            <!-- E-MAIL -->
            <div>
              <label
                for="email"
                class="mb-1.5 block text-xs font-semibold uppercase tracking-wide text-slate-700"
              >
                E-mail
              </label>

              <input
                id="email"
                v-model.trim="email"
                type="email"
                autocomplete="email"
                placeholder="nome@empresa.com"
                maxlength="254"
                required
                class="h-10 w-full rounded-md border border-slate-300 bg-white px-3 text-sm text-slate-800 outline-none transition placeholder:text-slate-400 focus:border-[#2563EB] focus:ring-2 focus:ring-[#2563EB]/15"
              />
            </div>

            <!-- SENHA -->
            <div>
              <label
                for="senha-cadastro"
                class="mb-1.5 block text-xs font-semibold uppercase tracking-wide text-slate-700"
              >
                Senha
              </label>

              <div class="relative">

                <input
                  id="senha-cadastro"
                  v-model="senha"
                  :type="mostrarSenha ? 'text' : 'password'"
                  autocomplete="new-password"
                  placeholder="Mínimo de 8 caracteres"
                  minlength="8"
                  maxlength="128"
                  required
                  class="h-10 w-full rounded-md border border-slate-300 bg-white px-3 pr-11 text-sm text-slate-800 outline-none transition placeholder:text-slate-400 focus:border-[#2563EB] focus:ring-2 focus:ring-[#2563EB]/15"
                />

                <button
                  type="button"
                  :aria-label="mostrarSenha ? 'Ocultar senhas' : 'Mostrar senhas'"
                  class="absolute right-3 top-1/2 -translate-y-1/2 text-slate-500 transition hover:text-[#2563EB]"
                  @click="mostrarSenha = !mostrarSenha"
                >
                  <svg
                    v-if="!mostrarSenha"
                    xmlns="http://www.w3.org/2000/svg"
                    class="h-4 w-4"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke="currentColor"
                    stroke-width="1.7"
                  >
                    <path stroke-linecap="round" stroke-linejoin="round" d="M2 12s3.5-7 10-7 10 7 10 7-3.5 7-10 7S2 12 2 12Z" />
                    <circle cx="12" cy="12" r="3" />
                  </svg>

                  <svg
                    v-else
                    xmlns="http://www.w3.org/2000/svg"
                    class="h-4 w-4"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke="currentColor"
                    stroke-width="1.7"
                  >
                    <path stroke-linecap="round" stroke-linejoin="round" d="M3 3l18 18M10.6 10.6a2 2 0 0 0 2.8 2.8M9.9 5.1A10.9 10.9 0 0 1 12 5c6.5 0 10 7 10 7a13.8 13.8 0 0 1-3.1 3.8M6.2 6.2C3.5 8 2 12 2 12s3.5 7 10 7c1.2 0 2.3-.2 3.3-.6" />
                  </svg>
                </button>

              </div>
            </div>

            <!-- CONFIRMAR SENHA -->
            <div>
              <label
                for="confirmar-senha"
                class="mb-1.5 block text-xs font-semibold uppercase tracking-wide text-slate-700"
              >
                Confirmar senha
              </label>

              <input
                id="confirmar-senha"
                v-model="confirmarSenha"
                :type="mostrarSenha ? 'text' : 'password'"
                autocomplete="new-password"
                placeholder="Digite a senha novamente"
                minlength="8"
                maxlength="128"
                required
                class="h-10 w-full rounded-md border border-slate-300 bg-white px-3 text-sm text-slate-800 outline-none transition placeholder:text-slate-400 focus:border-[#2563EB] focus:ring-2 focus:ring-[#2563EB]/15"
              />
            </div>

            <!-- TERMOS -->
            <div>
              <label class="flex cursor-pointer items-start gap-2 text-xs leading-relaxed text-slate-600">
                <input
                  v-model="aceitouTermos"
                  type="checkbox"
                  required
                  class="mt-0.5 h-4 w-4 shrink-0 accent-[#2563EB]"
                />

                <span>
                  Confirmo que os dados informados estão corretos e concordo com os termos de uso e a política de privacidade do sistema.
                </span>
              </label>
            </div>

            <!-- BOTÃO CADASTRAR -->
            <button
              type="submit"
              class="mt-2 h-11 w-full rounded-md bg-[#2563EB] text-sm font-semibold text-white transition hover:bg-[#0EA5E9] active:scale-[0.99]"
            >
              Criar minha conta
            </button>

          </form>

          <!-- VOLTAR AO LOGIN -->
          <div class="mt-6 text-center">

            <span class="text-sm text-slate-500">
              Já possui uma conta?
            </span>

            <RouterLink
              to="/login"
              class="ml-1 text-sm font-semibold text-[#14B8A6] transition hover:text-[#0EA5E9] hover:underline"
            >
              Fazer login
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

const nome = ref('')
const email = ref('')
const senha = ref('')
const confirmarSenha = ref('')
const mostrarSenha = ref(false)
const aceitouTermos = ref(false)
const erro = ref('')

function cadastrar() {
  erro.value = ''

  if (nome.value.trim().length < 3) {
    erro.value = 'Informe seu nome completo.'
    return
  }

  if (senha.value.length < 8) {
    erro.value = 'A senha deve ter pelo menos 8 caracteres.'
    return
  }

  if (senha.value !== confirmarSenha.value) {
    erro.value = 'As senhas não coincidem. Verifique os campos.'
    return
  }

  if (!aceitouTermos.value) {
    erro.value = 'É necessário aceitar os termos para continuar.'
    return
  }

  // A interface está pronta, mas a criação real da conta
  // depende da integração com uma API/backend.
  alert(
    'Os dados foram validados na tela. Para concluir o cadastro, é necessário conectar o sistema ao serviço de autenticação.'
  )

  // Não salva senha nem cria uma conta falsa no navegador.
  // Após integrar o backend, redirecione ao login quando
  // o servidor confirmar o cadastro.
}
</script>
