﻿# company-microservices
# 🚀 Job Management Application

Cette application Spring Boot gère les **Jobs**, les **Companies**, et les **Reviews**. Elle suit une architecture microservices et intègre plusieurs outils pour la résilience, la communication asynchrone, et la configuration centralisée.

---

## 📦 Fonctionnalités

- 📂 Gestion des **Jobs** : création, mise à jour, suppression, consultation
- 🏢 Gestion des **Companies** : ajout d'entreprises, détails, relations avec les offres d'emploi
- 📝 Système de **Reviews** pour les entreprises
- 💬 Communication **asynchrone** via RabbitMQ
- 💾 Persistance des données avec **PostgreSQL**
- 🛡️ Résilience aux pannes avec **Resilience4j**
- 🌐 API Gateway via **Spring Cloud Gateway**
- ⚙️ Configuration centralisée avec **Spring Cloud Config Server**
- 🌍 Communication inter-services avec **FeignClient**

---

## 🧱 Architecture

+----------------+ +----------------+ +----------------+ | Job Service | ---> | Company Service| ---> | Review Service | +----------------+ +----------------+ +----------------+ | | | v v v RabbitMQ Queue PostgreSQL DB PostgreSQL DB | | | +-----------> Spring Cloud Gateway <-----------+ | +-------------------+ | Config Server | +-------------------+



---

## ⚙️ Technologies Utilisées

| Composant            | Technologie            |
|---------------------|------------------------|
| Framework principal | Spring Boot            |
| Base de données     | PostgreSQL             |
| Messaging           | RabbitMQ               |
| Résilience          | Resilience4j           |
| API Gateway         | Spring Cloud Gateway   |
| Config centralisé   | Spring Cloud Config    |
| Client HTTP         | OpenFeign              |
| Authentification    | (À définir, ex: Keycloak/JWT) |

---
