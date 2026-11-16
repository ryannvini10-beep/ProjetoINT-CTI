"""Configuração de conexão com o banco. Lê as variáveis do arquivo .env (pasta analytics-python)."""
import os                                                      # import: os = lê variáveis de ambiente
from pathlib import Path                                       # import: Path = trabalha com caminhos de arquivos

from dotenv import load_dotenv                                 # import: load_dotenv lê o arquivo .env
from sqlalchemy.engine import URL                              # import: URL monta o endereço do banco

load_dotenv(Path(__file__).resolve().parent / ".env")   # lê o arquivo .env que está na pasta analytics-python


def database_url() -> URL:                                     # função: devolve o endereço de conexão com o banco
    """Monta o endereço do banco com as variáveis DB_HOST, DB_PORT, DB_NAME, DB_USER, DB_PASSWORD e DB_SSLMODE.

    Se o .env não existir, usa os dados do Neon escritos abaixo (o mesmo banco do backend Java).
    """
    return URL.create(                                         # monta o endereço do PostgreSQL
        "postgresql+psycopg",                                  # driver: psycopg (conversa com o PostgreSQL)
        username=os.getenv("DB_USER", "neondb_owner"),         # usuário do Neon
        password=os.getenv("DB_PASSWORD", "npg_j5YScNHmB0ZL"), # senha do Neon
        host=os.getenv("DB_HOST", "ep-wandering-grass-b6xvbwze.c-2.sa-east-1.aws.neon.tech"),  # servidor do Neon
        port=int(os.getenv("DB_PORT", "5432")),                # porta padrão do PostgreSQL
        database=os.getenv("DB_NAME", "neondb"),               # nome do banco no Neon
        query={"sslmode": os.getenv("DB_SSLMODE", "require")}, # sslmode=require: o Neon exige conexão criptografada
    )                                                          # fecha o bloco