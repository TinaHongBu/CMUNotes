# System Design 0 - Client Sever Pattern

The client–server model is a distributed application structure that partitions tasks or workloads between the providers of a resource or service, called servers, and service requesters, called clients.

## The Client Server Pattern

- Clients & Servers are independently deployable executables
- Clients are aware of the presence and location of the server
- The server has no knowledge of the client
- Client initiate the request
- Server respond

## Examples

- email exchange
- web access
- database access

## Design Decisions

- Distributed Applications
- Client and server interact through sending messages through the internet

## Evolution

- Commercial Computing: very expensive in the 50's, application is written specific for a mainframe
- OS was introduced in the 60's
  - Can upgrade hardware without rewriting the application
  - But job is still in batch mode: run it -> fail or success
- Batch Sequential Mainframe + Dumb terminals (no CPU)
- File System -> DBMS
  - Allow concurrent access to data
- Increased Hardware Capabilitys and Reduced Cost
  - Mainframe -> Terminal (with CPU) = Personal computers: 70's (shift of the responsibility)
  - Becomes distributed
  - Multitasking 
    - Able to process more data in a unit of time
- Developing Infrastructure
  - Network Infrastructure: 60's
  - TCP/IP

## New Concern

- Concurrent access to data
- Latency of Requests
  - How to prioritize incoming requests
  - the granularity of request