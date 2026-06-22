# ROS2-Driven Smart Elderly Care Solution 🤖🏥

[![ROS2](https://shields.io)](https://ros.org)
[![License: MIT](https://shields.io)](https://opensource.org)

An advanced, production-ready modular software stack designed for autonomous elderly care monitoring. This system leverages **ROS2 (Humble/Iron)** to fuse 3D mm-Wave Radar data and heterogeneous AIoT sensor streams via a Spatio-Temporal Graph Neural Network (GNN). Developed and successfully piloted in real-world nursing homes, resolving critical false-alarm and latency bottlenecks in legacy hardware.

> **Project Milestone:** Showcased at the 6th China International Import Expo (CIIE, Shanghai) and secured ¥80,000 MoE research funding.

---

## 🌟 Key Features

- **ROS2 Modular Architecture:** Completely decoupled systems using standard ROS2 nodes, pub/sub topics, and custom actions/services, enabling zero-downtime OTA updates.
- **Multimodal Sensor Fusion:** Fuses real-time 3D millimeter-wave radar point clouds and ambient AIoT telemetry streams to monitor human poses and fall detection.
- **Spatio-Temporal GNN Core:** Utilizes custom Graph Neural Networks to capture spatial environments and temporal motion patterns, **slashing false-positive fall alarms from 18% to 3.2%**.
- **Ultra-Low Latency Implementation:** High-concurrency edge optimization ensuring end-to-end night-response latency **within 0.6 seconds** (down from 7 seconds).

---

## 🏗️ System Architecture

```text
       [ 3D mm-Wave Radar ]       [ AIoT Sensor Streams ]

                |                           |
                v                           v
     [ radar_bridge_node ]        [ aiot_receiver_node ]

                |                           |
                +-------------+-------------+
                              | (ROS2 Topics)
                              v
                [ sensor_fusion_pipeline ]
                              |
                              v
              [ spatio_temporal_gnn_node ] (Inference)
                              |
         +--------------------+--------------------+

         | (Fall Detected)                         | (Normal State)
         v                                         v
 [ emergency_action_server ]             [ state_monitor_node ]

         |                                         |
         v                                         v
 [ Cloud API / Nurse Alert ]              [ Telemetry Logging ]
```

---

## 🛠️ Tech Stack & Dependencies

- **OS:** Ubuntu 22.04 LTS
- **Middleware:** ROS2 Humble Hawksbill
- **Languages:** C++17 (Performance-critical node communication), Python 3.10 (GNN model inference)
- **DL Frameworks:** PyTorch, TensorRT (for optimized edge GPU acceleration)
- **Build System:** Colcon, CMake

---

## 🚀 Quick Start (Docker Deployment)

The system is fully containerized with Docker, mounting the host's GPU and ROS2 network layout.

### Prerequisites
- Docker Engine & Docker Compose
- NVIDIA Container Toolkit (for TensorRT GNN acceleration)

### Build and Run
1. Clone the repository:
   ```bash
   git clone https://github.com
   cd ros2-smart-elderly-care
   ```

2. Spin up the containers (includes core pipeline, GNN model, and localized simulator):
   ```bash
   docker-compose up --build
   ```

3. (Optional) Run the local simulator to publish mock radar/AIoT telemetry:
   ```bash
   docker exec -it ros2_elderly_care colcon build --packages-select care_simulator
   ros2 run care_simulator simulation_publisher
   ```

---

## 📊 Performance Benchmark

| Metrics | Legacy System | Our ROS2 Stack |
| :--- | :---: | :---: |
| **False-Positive Fall Alarms** | 18% | **3.2%** |
| **End-to-End Latency** | 7.0 seconds | **0.6 seconds** |
| **System OTA Downtime** | Required Reboot | **Zero Downtime** |

---

## 🔒 Confidentiality & Disclaimer

**Important Notice:** Due to intellectual property regulations and non-disclosure agreements (NDAs) associated with **SoftBank Robotics** and the partnering pilot facilities, the core proprietary GNN weights and production-level device firmware are **omitted** from this public repository. 

This repository serves as a **clean, open-source architectural demo** demonstrating the ROS2 topology, node structures, customized message definitions (`care_msgs`), and the mock data simulator.

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
