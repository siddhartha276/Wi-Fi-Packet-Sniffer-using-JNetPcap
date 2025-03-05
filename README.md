# Wi-Fi-Packet-Sniffer-using-JNetPcap
This Java-based packet sniffer captures network packets from a Wi-Fi adapter using the JNetPcap library. It lists available network devices, selects the Wi-Fi adapter, and captures packets in real time, saving them to a .pcap file for further analysis.

## Overview
This project is a simple packet sniffer built using Java and the **JNetPcap** library. It captures network packets from a Wi-Fi adapter and saves them to a `.pcap` file for further analysis using tools like **Wireshark**.

## Features
- Lists all available network adapters.
- Automatically selects a Wi-Fi adapter.
- Captures network packets in real-time.
- Saves captured packets to a `.pcap` file.
- Stops capturing after 1000 packets.

## Prerequisites
- Java Development Kit (JDK 8+)
- JNetPcap Library ([Download Here](https://sourceforge.net/projects/jnetpcap/))
- Npcap (For Windows) / libpcap (For Linux/macOS)
- A working Wi-Fi adapter

## Installation & Setup
1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/WiFi-Packet-Sniffer.git
   cd WiFi-Packet-Sniffer
