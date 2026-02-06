# Chat App with AI Summaries

A **real-time multi-topic group chat application** with AI-powered conversation summaries. Demonstrates full-stack development, distributed messaging, and AI integration.

## Overview

Users can join multiple chat rooms and exchange messages in real time. As conversations grow, the app generates AI summaries (every 10 messages) to help users catch up on longer threads. Messages are persisted for scroll-back history across three topics.

## Architecture

![Architecture diagram](../Architecture.png)

## Tech Stack

| Layer | Technologies |
|-------|--------------|
| **Frontend** | React, Material-UI, SockJS/STOMP (WebSockets), Axios |
| **Backend** | Java 11, Spring Boot, REST APIs |
| **Messaging** | Apache Kafka (pub/sub) |
| **Cache** | Redis (message history, sequence tracking) |
| **AI Summarization** | Python, Llama API |

## Key Features

- **Multi-topic chats** — Switch between 3 chat rooms (Topic 1–3)
- **Real-time delivery** — WebSockets for instant message broadcast
- **Message persistence** — Redis-backed history with scroll-to-load
- **AI summaries** — Llama API summarizes conversation threads for quick context
- **Event-driven design** — Kafka producers/consumers for message handling

## Project Structure

```
Chat-app-with-summaries/
├── chat-server/    # Spring Boot backend (Kafka, Redis, WebSockets)
└── chat-ui/        # React frontend
```

## Prerequisites & Running Locally

1. **Services**: Kafka (with Zookeeper), Redis, Python (for summarization script)
2. **Backend**: `cd chat-server && mvn spring-boot:run`
3. **Frontend**: `cd chat-ui && npm install && npm start`

See `chat-server/Readme.md` for Kafka setup (topic creation, etc.).

## Skills Demonstrated

- Full-stack web development (React + Spring Boot)
- Distributed systems (Kafka, Redis)
- Real-time communication (WebSockets)
- REST API design
- AI/LLM integration for summarization
