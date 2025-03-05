# Wi-Fi-Packet-Sniffer-using-JNetPcap
This Java-based packet sniffer captures network packets from a Wi-Fi adapter using the JNetPcap library. It lists available network devices, selects the Wi-Fi adapter, and captures packets in real time, saving them to a .pcap file for further analysis.

# Wi-Fi Packet Sniffer using JNetPcap

## Overview
This project is a **Wi-Fi Packet Sniffer** built using **Java** and the **JNetPcap** library. It captures network packets from a Wi-Fi adapter in real time and saves them in a `.pcap` file, which can be analyzed using **Wireshark** or other network analysis tools.

## Features
- Lists all available network devices.
- Automatically detects and selects the Wi-Fi adapter.
- Captures live network packets.
- Saves captured packets to a `.pcap` file.
- Stops capturing after **1000 packets** or when manually terminated.

## Prerequisites
Before running the project, ensure you have the following installed:

### **1. Required Software**
- **Java Development Kit (JDK 8+)** → [Download Here](https://www.oracle.com/java/technologies/javase-downloads.html)
- **Npcap (Windows) / libpcap (Linux/macOS)** → [Download Here](https://npcap.com/)
- **JNetPcap Library** → [Download Here](https://sourceforge.net/projects/jnetpcap/)

### **2. Setup JNetPcap**
- Download and extract the **JNetPcap** library.
- Add `jnetpcap.jar` to your project's classpath.
- Place the native libraries (`.dll` for Windows or `.so` for Linux/macOS) in the appropriate system path.

## Installation & Setup

### **Step 1: Clone the Repository**
```bash
git clone https://github.com/yourusername/WiFi-Packet-Sniffer.git
cd WiFi-Packet-Sniffer

