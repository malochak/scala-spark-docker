# Scala & Spark Data Processing in Docker

This repository contains an example configuration for local development with Scala & Spark.\
The base configuration includes:
- **Scala** `2.12.18` Project (the versions chosen, because `bitnami/spark` image is buitl with **Spark** `3.5.2` compiled for **Scala** `2.12.18`
- SBT as a built tool
- Spark Master container based on `bitnami/spark:3.5.2` image
- Spark Worker container based on `bitnami/spark:3.5.2` image
- Postgres Container based on `postgres:13-bullseye` image
- MinIO Container `latest` version `minio/minio:latest`
