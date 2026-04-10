"""
geoms-mcp: MCP server that exposes the GeoMs microservice (geosvr) as MCP tools.

Endpoints proxied:
  - States:        GET /states, GET /states/{id}
  - Cities:        GET /cities, GET /cities/{id}, GET /cities/name/{name}
  - Neighborhoods: GET /neighboors, GET /neighboors/{id},
                   GET /neighboors/zipCode/{zipCode},
                   GET /neighboors/zipCodeDb/{zipCode}
"""

import os
import httpx
from mcp.server.fastmcp import FastMCP

# ---------------------------------------------------------------------------
# Configuration
# ---------------------------------------------------------------------------
GEOSVR_BASE_URL = os.environ.get("GEOSVR_BASE_URL", "http://localhost:8080").rstrip("/")

mcp = FastMCP("geoms-mcp")

# ---------------------------------------------------------------------------
# Helper
# ---------------------------------------------------------------------------

def _get(path: str):
    url = f"{GEOSVR_BASE_URL}{path}"
    with httpx.Client(timeout=10) as client:
        response = client.get(url)
        response.raise_for_status()
        return response.json()


# ---------------------------------------------------------------------------
# States tools
# ---------------------------------------------------------------------------

@mcp.tool()
def list_states() -> list:
    """Return all states."""
    return _get("/states")


@mcp.tool()
def get_state(state_id: int) -> dict:
    """Return a single state by its numeric ID.

    Args:
        state_id: Numeric identifier of the state.
    """
    return _get(f"/states/{state_id}")


# ---------------------------------------------------------------------------
# Cities tools
# ---------------------------------------------------------------------------

@mcp.tool()
def list_cities() -> list:
    """Return all cities."""
    return _get("/cities")


@mcp.tool()
def get_city(city_id: int) -> dict:
    """Return a single city by its numeric ID.

    Args:
        city_id: Numeric identifier of the city.
    """
    return _get(f"/cities/{city_id}")


@mcp.tool()
def find_cities_by_name(name: str) -> list:
    """Search cities whose name matches the given value.

    Args:
        name: City name (or partial name) to search for.
    """
    return _get(f"/cities/name/{name}")


# ---------------------------------------------------------------------------
# Neighborhoods tools
# ---------------------------------------------------------------------------

@mcp.tool()
def list_neighborhoods() -> list:
    """Return all neighborhoods."""
    return _get("/neighboors")


@mcp.tool()
def get_neighborhood(neighborhood_id: int) -> dict:
    """Return a single neighborhood by its numeric ID.

    Args:
        neighborhood_id: Numeric identifier of the neighborhood.
    """
    return _get(f"/neighboors/{neighborhood_id}")


@mcp.tool()
def find_neighborhoods_by_zip_code(zip_code: int) -> list:
    """Search neighborhoods by zip code using the in-memory index.

    Args:
        zip_code: Zip code to search for.
    """
    return _get(f"/neighboors/zipCode/{zip_code}")


@mcp.tool()
def find_neighborhoods_by_zip_code_db(zip_code: int) -> list:
    """Search neighborhoods by zip code using the database (slower, authoritative).

    Args:
        zip_code: Zip code to search for.
    """
    return _get(f"/neighboors/zipCodeDb/{zip_code}")


# ---------------------------------------------------------------------------
# Entry point
# ---------------------------------------------------------------------------

if __name__ == "__main__":
    mcp.run()
