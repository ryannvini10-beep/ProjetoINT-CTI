<template>
  <div class="min-h-screen bg-gray-100 p-6">
    <div class="max-w-7xl mx-auto space-y-6">
      
      <!-- Cabeçalho -->
      <div class="flex flex-col md:flex-row md:items-center md:justify-between gap-4">
        <div>
          <h1 class="text-2xl font-bold text-gray-800">Painel de Métricas</h1>
          <p class="text-sm text-gray-500">Visão geral do desempenho do sistema</p>
        </div>
        
        <!-- Filtro Rápido -->
        <div class="flex items-center space-x-2">
          <select class="bg-white border border-gray-300 rounded-lg px-3 py-2 text-sm text-gray-700 focus:outline-none focus:ring-2 focus:ring-indigo-500">
            <option>Últimos 7 dias</option>
            <option>Últimos 30 dias</option>
            <option>Este ano</option>
          </select>
        </div>
      </div>

      <!-- Cards de Métricas Rápidas (KPIs) -->
      <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4">
        <div class="bg-white p-5 rounded-xl shadow-sm border border-gray-200">
          <p class="text-xs font-medium text-gray-500 uppercase tracking-wider">Receita Total</p>
          <p class="text-2xl font-bold text-gray-800 mt-1">R$ 121.000</p>
          <span class="text-xs font-semibold text-emerald-600">↑ +12.5% vs mês anterior</span>
        </div>

        <div class="bg-white p-5 rounded-xl shadow-sm border border-gray-200">
          <p class="text-xs font-medium text-gray-500 uppercase tracking-wider">Novos Usuários</p>
          <p class="text-2xl font-bold text-gray-800 mt-1">1.842</p>
          <span class="text-xs font-semibold text-emerald-600">↑ +8.1% vs mês anterior</span>
        </div>

        <div class="bg-white p-5 rounded-xl shadow-sm border border-gray-200">
          <p class="text-xs font-medium text-gray-500 uppercase tracking-wider">Taxa de Conversão</p>
          <p class="text-2xl font-bold text-gray-800 mt-1">3.2%</p>
          <span class="text-xs font-semibold text-rose-600">↓ -0.4% vs mês anterior</span>
        </div>

        <div class="bg-white p-5 rounded-xl shadow-sm border border-gray-200">
          <p class="text-xs font-medium text-gray-500 uppercase tracking-wider">Sessões Ativas</p>
          <p class="text-2xl font-bold text-gray-800 mt-1">432</p>
          <span class="text-xs font-semibold text-gray-500">Agora no site</span>
        </div>
      </div>

      <!-- Seção Principal de Gráficos -->
      <div class="grid grid-cols-1 lg:grid-cols-3 gap-6">
        
        <!-- Gráfico de Vendas (Ocupa 2 Colunas) -->
        <div class="lg:col-span-2 bg-white p-6 rounded-xl shadow-sm border border-gray-200">
          <h2 class="text-lg font-bold text-gray-800 mb-4">Vendas Mensais</h2>
          <div class="h-72">
            <Bar :data="barData" :options="chartOptions" />
          </div>
        </div>

        <!-- Gráfico de Dispositivos (1 Coluna) -->
        <div class="bg-white p-6 rounded-xl shadow-sm border border-gray-200">
          <h2 class="text-lg font-bold text-gray-800 mb-4">Acessos por Dispositivo</h2>
          <div class="h-72 flex items-center justify-center">
            <Doughnut :data="doughnutData" :options="chartOptions" />
          </div>
        </div>

      </div>

      <!-- Gráfico de Linha em Largura Total -->
      <div class="bg-white p-6 rounded-xl shadow-sm border border-gray-200">
        <h2 class="text-lg font-bold text-gray-800 mb-4">Tráfego da Semana</h2>
        <div class="h-72">
          <Line :data="lineData" :options="chartOptions" />
        </div>
      </div>

    </div>
  </div>
</template>