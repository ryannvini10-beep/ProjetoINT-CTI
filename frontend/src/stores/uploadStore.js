
import { defineStore } from 'pinia'
import * as XLSX from 'xlsx'

const MAX_SIZE = 20 * 1024 * 1024

const EXPECTED_COLUMNS = [
  'codigo_cliente',
  'nome_cliente',
  'consultor',
  'segmento',
  'nivel_cliente',
  'faturamento_anual',
  'servicos_contratados',
  'data_contratacao',
  'cidade',
  'uf'
]

const REQUIRED_FIELDS = [
  'codigo_cliente',
  'nome_cliente',
  'consultor',
  'segmento',
  'nivel_cliente',
  'faturamento_anual',
  'servicos_contratados',
  'data_contratacao'
]

const ALLOWED_SEGMENTS = [
  'Indústria',
  'Comércio',
  'Serviços',
  'Saúde',
  'Tecnologia',
  'Educação'
]

// Catálogo inicial: confirme se está completo.
const ALLOWED_SERVICES = [
  'Internet Dedicada',
  'Link Empresarial',
  'Firewall',
  'Backup',
  'MPLS',
  'Cloud'
]

const ALLOWED_LEVELS = ['A', 'B', 'C']

const ALLOWED_UFS = [
  'AC', 'AL', 'AP', 'AM', 'BA', 'CE', 'DF', 'ES',
  'GO', 'MA', 'MT', 'MS', 'MG', 'PA', 'PB', 'PR',
  'PE', 'PI', 'RJ', 'RN', 'RS', 'RO', 'RR', 'SC',
  'SP', 'SE', 'TO'
]

function isEmpty(value) {
  return value === null ||
    value === undefined ||
    String(value).trim() === ''
}

function normalizeText(value) {
  return String(value ?? '').trim()
}

function normalizeColumn(value) {
  return normalizeText(value)
    .normalize('NFD')
    .replace(/[\u0300-\u036f]/g, '')
    .toLowerCase()
    .replace(/\s+/g, '_')
}

function normalizeForComparison(value) {
  return normalizeText(value).toLocaleLowerCase('pt-BR')
}

function parseNumber(value) {
  if (typeof value === 'number') {
    return Number.isFinite(value) ? value : null
  }

  let text = String(value ?? '')
    .trim()
    .replace(/R\$/gi, '')
    .replace(/\s/g, '')

  if (!text) return null

  if (text.includes(',') && text.includes('.')) {
    text = text.replace(/\./g, '').replace(',', '.')
  } else if (text.includes(',')) {
    text = text.replace(',', '.')
  }

  if (!/^-?\d+(\.\d+)?$/.test(text)) {
    return null
  }

  const number = Number(text)
  return Number.isFinite(number) ? number : null
}

function isValidDate(value) {
  if (value instanceof Date) {
    return !Number.isNaN(value.getTime())
  }

  if (typeof value === 'number') {
    const date = XLSX.SSF.parse_date_code(value)

    return Boolean(
      date &&
      date.y >= 1900 &&
      date.m >= 1 &&
      date.m <= 12 &&
      date.d >= 1 &&
      date.d <= 31
    )
  }

  const text = normalizeText(value)

  if (!text) return false

  // DD/MM/AAAA
  const br = text.match(/^(\d{2})\/(\d{2})\/(\d{4})$/)

  if (br) {
    const day = Number(br[1])
    const month = Number(br[2])
    const year = Number(br[3])
    const date = new Date(year, month - 1, day)

    return date.getFullYear() === year &&
      date.getMonth() === month - 1 &&
      date.getDate() === day
  }

  // AAAA-MM-DD
  const iso = text.match(/^(\d{4})-(\d{2})-(\d{2})$/)

  if (iso) {
    const year = Number(iso[1])
    const month = Number(iso[2])
    const day = Number(iso[3])
    const date = new Date(year, month - 1, day)

    return date.getFullYear() === year &&
      date.getMonth() === month - 1 &&
      date.getDate() === day
  }

  return false
}

function addIssue(
  list,
  linha,
  campo,
  codigo,
  mensagem,
  valor = ''
) {
  list.push({
    linha,
    campo,
    codigo,
    mensagem,
    valor: valor == null ? '' : String(valor)
  })
}

function readRows(buffer, extension) {
  const workbook = XLSX.read(buffer, {
    type: 'array',
    cellDates: true
  })

  if (!workbook.SheetNames.length) {
    throw new Error('O arquivo não possui nenhuma planilha.')
  }

  let sheetName = workbook.SheetNames[0]

  if (
    extension === 'xlsx' &&
    workbook.SheetNames.includes('upload_clientes')
  ) {
    sheetName = 'upload_clientes'
  }

  const worksheet = workbook.Sheets[sheetName]

  return XLSX.utils.sheet_to_json(worksheet, {
    header: 1,
    defval: '',
    raw: true,
    blankrows: false
  })
}

function createSchemaError(
  mensagem,
  campo = 'planilha',
  errors = null,
  totalLinhas = 0
) {
  const schemaErrors = errors ?? [{
    linha: 1,
    campo,
    codigo: 'ERRO_SCHEMA',
    mensagem,
    valor: ''
  }]

  return {
    status: 'ERRO_SCHEMA',
    totalLinhas,
    totalValidos: 0,
    totalInvalidos: 0,
    totalErros: schemaErrors.length,
    totalAvisos: 0,
    erros: schemaErrors,
    avisos: [],
    registrosValidos: [],
    registrosInvalidos: [],
    preview: []
  }
}

function validateRows(rows) {
  const errors = []
  const warnings = []

  if (!rows.length || !rows[0]?.length) {
    return createSchemaError(
      'A planilha está vazia ou não possui cabeçalhos.'
    )
  }

  const headers = rows[0].map(normalizeColumn)

  const duplicateHeaders = [
    ...new Set(
      headers.filter(
        (header, index) => headers.indexOf(header) !== index
      )
    )
  ]

  const missingColumns = EXPECTED_COLUMNS.filter(
    column => !headers.includes(column)
  )

  const unexpectedColumns = headers.filter(
    column => !EXPECTED_COLUMNS.includes(column)
  )

  duplicateHeaders.forEach(header => {
    addIssue(
      errors,
      1,
      header,
      'COLUNA_DUPLICADA',
      'Existe uma coluna duplicada no cabeçalho.'
    )
  })

  missingColumns.forEach(column => {
    addIssue(
      errors,
      1,
      column,
      'COLUNA_AUSENTE',
      'Coluna obrigatória do modelo ausente.'
    )
  })

  unexpectedColumns.forEach(column => {
    addIssue(
      errors,
      1,
      column || 'cabeçalho',
      'COLUNA_NAO_PREVISTA',
      'Coluna não prevista no modelo.'
    )
  })

  if (
    duplicateHeaders.length ||
    missingColumns.length ||
    unexpectedColumns.length
  ) {
    return createSchemaError(
      'A estrutura da planilha não corresponde ao modelo.',
      'planilha',
      errors,
      Math.max(rows.length - 1, 0)
    )
  }

  const columnIndex = Object.fromEntries(
    headers.map((header, index) => [header, index])
  )

  const records = rows
    .slice(1)
    .map((row, index) => {
      const record = {}

      EXPECTED_COLUMNS.forEach(column => {
        record[column] = row[columnIndex[column]] ?? ''
      })

      record.__linha = index + 2
      return record
    })
    .filter(record =>
      EXPECTED_COLUMNS.some(column => !isEmpty(record[column]))
    )

  // Duplicidades geram avisos, não erros.
  const codeIndexes = new Map()

  records.forEach(record => {
    const code = normalizeText(
      record.codigo_cliente
    ).toUpperCase()

    if (!code) return

    if (!codeIndexes.has(code)) {
      codeIndexes.set(code, [])
    }

    codeIndexes.get(code).push(record.__linha)
  })

  codeIndexes.forEach((lines, code) => {
    if (lines.length > 1) {
      lines.forEach(line => {
        addIssue(
          warnings,
          line,
          'codigo_cliente',
          'AVISO_DUPLICIDADE',
          `Código duplicado: ${code}. Linhas envolvidas: ${lines.join(', ')}.`,
          code
        )
      })
    }
  })

  // Validar cada registro.
  records.forEach(record => {
    const line = record.__linha

    REQUIRED_FIELDS.forEach(field => {
      if (isEmpty(record[field])) {
        addIssue(
          errors,
          line,
          field,
          'CAMPO_OBRIGATORIO',
          'Campo obrigatório não preenchido.',
          record[field]
        )
      }
    })

    record.codigo_cliente = normalizeText(
      record.codigo_cliente
    ).toUpperCase()

    ;[
      'nome_cliente',
      'consultor',
      'segmento',
      'nivel_cliente',
      'servicos_contratados',
      'cidade',
      'uf'
    ].forEach(field => {
      if (typeof record[field] === 'string') {
        record[field] = record[field].trim()
      }
    })

    // Faturamento.
    const revenue = parseNumber(record.faturamento_anual)

    if (!isEmpty(record.faturamento_anual)) {
      if (revenue === null) {
        addIssue(
          errors,
          line,
          'faturamento_anual',
          'FATURAMENTO_INVALIDO',
          'O faturamento deve ser numérico.',
          record.faturamento_anual
        )
      } else if (revenue < 0) {
        addIssue(
          errors,
          line,
          'faturamento_anual',
          'FATURAMENTO_NEGATIVO',
          'O faturamento não pode ser negativo.',
          record.faturamento_anual
        )
      } else {
        record.faturamento_anual = revenue
      }
    }

    // Nível: A, B ou C.
    if (!isEmpty(record.nivel_cliente)) {
      record.nivel_cliente =
        record.nivel_cliente.toUpperCase()

      if (!ALLOWED_LEVELS.includes(record.nivel_cliente)) {
        addIssue(
          errors,
          line,
          'nivel_cliente',
          'NIVEL_INVALIDO',
          'Nível inválido. Os valores permitidos são A, B e C.',
          record.nivel_cliente
        )
      }
    }

    // Segmento oficial.
    if (!isEmpty(record.segmento)) {
      const segment = normalizeForComparison(
        record.segmento
      )

      const officialSegment = ALLOWED_SEGMENTS.find(
        item =>
          normalizeForComparison(item) === segment
      )

      if (!officialSegment) {
        addIssue(
          errors,
          line,
          'segmento',
          'SEGMENTO_INVALIDO',
          `Segmento fora do catálogo oficial: ${record.segmento}.`,
          record.segmento
        )
      } else {
        record.segmento = officialSegment
      }
    }

    // Serviços separados por ponto e vírgula.
    if (!isEmpty(record.servicos_contratados)) {
      const rawServices = String(
        record.servicos_contratados
      )

      const services = rawServices
        .split(';')
        .map(service => service.trim())

      if (services.some(service => service === '')) {
        addIssue(
          errors,
          line,
          'servicos_contratados',
          'FORMATO_SERVICOS_INVALIDO',
          'Existem itens vazios entre os separadores.',
          rawServices
        )
      }

      const validServices = []
      const seenServices = new Set()

      services.filter(Boolean).forEach(service => {
        const officialService = ALLOWED_SERVICES.find(
          item =>
            normalizeForComparison(item) ===
            normalizeForComparison(service)
        )

        if (!officialService) {
          addIssue(
            errors,
            line,
            'servicos_contratados',
            'SERVICO_NAO_RECONHECIDO',
            `Serviço fora do catálogo: ${service}.`,
            service
          )
          return
        }

        const key = normalizeForComparison(
          officialService
        )

        if (!seenServices.has(key)) {
          validServices.push(officialService)
          seenServices.add(key)
        }
      })

      record.servicos_contratados =
        validServices.join(';')
    }

    // Data de contratação.
    if (
      !isEmpty(record.data_contratacao) &&
      !isValidDate(record.data_contratacao)
    ) {
      addIssue(
        errors,
        line,
        'data_contratacao',
        'DATA_INVALIDA',
        'A data deve ser válida no formato DD/MM/AAAA ou AAAA-MM-DD.',
        record.data_contratacao
      )
    }

    // UF opcional, mas validada quando preenchida.
    if (!isEmpty(record.uf)) {
      record.uf = record.uf.toUpperCase()

      if (!ALLOWED_UFS.includes(record.uf)) {
        addIssue(
          errors,
          line,
          'uf',
          'UF_INVALIDA',
          'Informe uma sigla válida de estado brasileiro.',
          record.uf
        )
      }
    }
  })

  const linesWithErrors = new Set(
    errors
      .filter(issue => issue.linha > 1)
      .map(issue => issue.linha)
  )

  const toPublicRecord = record => {
    const { __linha, ...data } = record

    return {
      ...data,
      linha: __linha
    }
  }

  const validRecords = records
    .filter(record =>
      !linesWithErrors.has(record.__linha)
    )
    .map(toPublicRecord)

  const invalidRecords = records
    .filter(record =>
      linesWithErrors.has(record.__linha)
    )
    .map(record => ({
      ...toPublicRecord(record),
      erros: errors.filter(
        issue => issue.linha === record.__linha
      )
    }))

  const status = errors.length
    ? 'ERRO_REGISTROS'
    : warnings.length
      ? 'VALIDADO_COM_AVISOS'
      : 'NORMALIZADO'

  return {
    status,
    totalLinhas: records.length,
    totalValidos: validRecords.length,
    totalInvalidos: invalidRecords.length,
    totalErros: errors.length,
    totalAvisos: warnings.length,
    erros: errors,
    avisos: warnings,
    registrosValidos: validRecords,
    registrosInvalidos: invalidRecords,
    preview: records.slice(0, 100).map(toPublicRecord)
  }
}

export const useUploadStore = defineStore('upload', {
  state: () => ({
    selectedFile: null,
    error: '',
    history: [],
    validationResult: null,
    maxSize: MAX_SIZE,
    allowedExtensions: ['xlsx', 'csv']
  }),

  getters: {
    hasFile: state => state.selectedFile !== null,

    selectedFileName: state =>
      state.selectedFile?.name || '',

    selectedFileSize: state =>
      state.selectedFile?.size || 0,

    totalFiles: state => state.history.length,

    processingFiles: state =>
      state.history.filter(
        file => file.status === 'PROCESSANDO'
      ),

    normalizedFiles: state =>
      state.history.filter(file =>
        ['NORMALIZADO', 'VALIDADO_COM_AVISOS']
          .includes(file.status)
      ),

    errorFiles: state =>
      state.history.filter(file =>
        ['ERRO_SCHEMA', 'ERRO_REGISTROS']
          .includes(file.status)
      )
  },

  actions: {
    async validateFile(file) {
      this.error = ''
      this.validationResult = null
      this.selectedFile = null

      if (!file) {
        this.error = 'Nenhum arquivo foi selecionado.'
        return false
      }

      const extension = file.name
        .split('.')
        .pop()
        .toLowerCase()

      if (!this.allowedExtensions.includes(extension)) {
        this.error = 'Selecione um arquivo .XLSX ou .CSV.'
        return false
      }

      if (file.size > this.maxSize) {
        this.error = 'O arquivo ultrapassa o limite de 20 MB.'
        return false
      }

      if (file.size === 0) {
        this.error = 'O arquivo está vazio.'
        return false
      }

      const entry = {
        id: `${Date.now()}-${Math.random()}`,
        name: file.name,
        date: new Date().toLocaleString('pt-BR'),
        rows: 0,
        errors: 0,
        warnings: 0,
        validRows: 0,
        invalidRows: 0,
        status: 'PROCESSANDO'
      }

      this.selectedFile = file
      this.history.unshift(entry)

      try {
        const buffer = await file.arrayBuffer()
        const rows = readRows(buffer, extension)
        const result = validateRows(rows)

        this.validationResult = {
          ...result,
          arquivo: file.name
        }

        // Atualiza o objeto reativo do histórico.
        const historyEntry = this.history.find(
          item => item.id === entry.id
        )

        if (historyEntry) {
          historyEntry.rows = result.totalLinhas
          historyEntry.errors = result.totalErros
          historyEntry.warnings = result.totalAvisos
          historyEntry.validRows = result.totalValidos
          historyEntry.invalidRows = result.totalInvalidos
          historyEntry.status = result.status
        }

        // Avisos não impedem o processamento.
        return (
          result.status !== 'ERRO_SCHEMA' &&
          result.totalInvalidos === 0
        )
      } catch (error) {
        console.error(
          'Erro ao validar a planilha:',
          error
        )

        const historyEntry = this.history.find(
          item => item.id === entry.id
        )

        if (historyEntry) {
          historyEntry.status = 'ERRO_SCHEMA'
          historyEntry.errors = 1
          historyEntry.rows = 0
        }

        this.validationResult = {
          ...createSchemaError(
            'Não foi possível ler o arquivo. Confira se ele está íntegro.'
          ),
          arquivo: file.name
        }

        this.error =
          'Não foi possível ler o arquivo selecionado.'

        return false
      }
    },

    removeHistory(id) {
      this.history = this.history.filter(
        item => item.id !== id
      )
    },

    clearError() {
      this.error = ''
    },

    clearSelectedFile() {
      this.selectedFile = null
      this.validationResult = null
    },

    clearHistory() {
      this.history = []
    }
  }
})
