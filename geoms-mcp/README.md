# geoms-mcp

MCP server that exposes the **GeoMs** microservice (`geosvr`) as [Model Context Protocol](https://modelcontextprotocol.io/) tools, so any MCP-compatible AI assistant can query geographic data (states, cities, neighborhoods) via natural language.

## Tools exposed

| Tool | Description |
|---|---|
| `list_states` | Return all states |
| `get_state(state_id)` | Return a state by ID |
| `list_cities` | Return all cities |
| `get_city(city_id)` | Return a city by ID |
| `find_cities_by_name(name)` | Search cities by name |
| `list_neighborhoods` | Return all neighborhoods |
| `get_neighborhood(neighborhood_id)` | Return a neighborhood by ID |
| `find_neighborhoods_by_zip_code(zip_code)` | Search neighborhoods by zip code (in-memory) |
| `find_neighborhoods_by_zip_code_db(zip_code)` | Search neighborhoods by zip code (database) |

## Requirements

- Python 3.10+
- A running instance of `geosvr`

## Setup

```bash
# 1. Clone the repo
git clone https://github.com/dbracamontes/geoms-mcp
cd geoms-mcp

# 2. Create and activate a virtual environment
python -m venv .venv
source .venv/bin/activate   # Windows: .venv\Scripts\activate

# 3. Install dependencies
pip install -r requirements.txt

# 4. Configure the geosvr URL
cp .env.example .env
# Edit .env and set GEOSVR_BASE_URL to your geosvr address

# 5. Run the MCP server
python server.py
```

## Docker

```bash
docker build -t geoms-mcp .
docker run -e GEOSVR_BASE_URL=http://geosvr:8080 -p 8000:8000 geoms-mcp
```

## Environment variables

| Variable | Default | Description |
|---|---|---|
| `GEOSVR_BASE_URL` | `http://localhost:8080` | Base URL of the `geosvr` REST service |

## Connecting to Claude Desktop

Add the following to your `claude_desktop_config.json`:

```json
{
  "mcpServers": {
    "geoms": {
      "command": "python",
      "args": ["/path/to/geoms-mcp/server.py"],
      "env": {
        "GEOSVR_BASE_URL": "http://localhost:8080"
      }
    }
  }
}
```

## Architecture

```
Claude / AI assistant
        │  MCP protocol (stdio / SSE)
        ▼
   geoms-mcp  (FastMCP server)
        │  HTTP REST
        ▼
     geosvr   (Spring Boot — MySQL / Elasticsearch)
```
